package com.hristogochev.tray.gtk.jna

import com.hristogochev.tray.gtk.jna.lib.Glib
import com.hristogochev.tray.gtk.jna.lib.Gtk3
import com.hristogochev.tray.gtk.jna.structs.FuncCallback
import com.hristogochev.tray.gtk.jna.structs.GMainContext
import com.hristogochev.tray.gtk.jna.structs.GMainLoop
import com.sun.jna.Pointer
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

// Took great inspiration from https://github.com/dorkbox/JNA/blob/master/src/dorkbox/jna/linux/GtkEventDispatch.java
internal object Gtk3Dispatcher {

    private var mainLoop: GMainLoop? = null
    private var mainContext: GMainContext? = null

    private lateinit var thread: Thread

    private val callbacks = LinkedList<FuncCallback>()

    private val currentlyDispatching = ThreadLocal.withInitial { false }

    private var running: Boolean = false

    @Synchronized
    fun start(): Boolean {
        if (running) return true
        running = true

        val startupLatch = CountDownLatch(1)

        thread = object : Thread() {
            override fun run() {
                val orig = Glib.g_log_set_default_handler(Glib.nullLogFunc, null)
                if (!Gtk3.gtk_init_check(0)) {
                    throw RuntimeException("Error starting gtk dispatcher")
                }

                mainLoop = Gtk3.g_main_loop_new(null, false)
                mainContext = Gtk3.g_main_loop_get_context(mainLoop)

                startupLatch.countDown()
                Gtk3.g_main_loop_run(mainLoop)

                if (orig != null) {
                    Glib.g_log_set_default_handler(orig, null)
                }
            }
        }.apply {
            isDaemon = false
            name = "GTK Native Event Loop"
        }
        thread.start()

        return try {
            if (!startupLatch.await(10, TimeUnit.SECONDS)) {
                System.err.println("Error: Waited for startup took longer than expected")
                running = false
                false
            } else {
                true
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
            running = false
            false
        }
    }


    @Synchronized
    fun waitAllDispatches() {
        val blockUntilStarted = CountDownLatch(1)

        dispatch { blockUntilStarted.countDown() }

        try {
            if (!blockUntilStarted.await(10, TimeUnit.SECONDS)) {
                System.err.println("WARNING: Waited for longer than expected")
            }

            while (true) {
                Thread.sleep(100)
                var breakOut = false
                synchronized(callbacks) {
                    if (callbacks.isEmpty()) {
                        breakOut = true
                    }
                }
                if (breakOut) break
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


    @Synchronized
    fun dispatchAndWait(timeoutSeconds: Long = 2, runnable: Runnable) {
        val isDispatch = currentlyDispatching.get()
        if (isDispatch) {
            runnable.run()
            return
        }
        val countDownLatch = CountDownLatch(1)
        dispatch {
            runCatching {
                runnable.run()
            }.onFailure {
                System.err.println("Error during GTK run loop: $it")
            }
            countDownLatch.countDown()
        }

        val waitedSuccessfully = runCatching {
            countDownLatch.await(timeoutSeconds, TimeUnit.SECONDS)
        }.onFailure {
            System.err.println("Error while waiting for dispatches to complete: $it")
        }.getOrElse { false }

        if (!waitedSuccessfully) {
            throw RuntimeException("The GTK3Dispatcher event completion took longer than $timeoutSeconds seconds.")
        }
    }

    @Synchronized
    fun dispatch(runnable: Runnable) {
        if (currentlyDispatching.get()) {
            runnable.run()
            return
        }

        val callback = object : FuncCallback {
            override fun callback(data: Pointer?): Int {
                currentlyDispatching.set(true)
                try {
                    runnable.run()
                } finally {
                    currentlyDispatching.set(false)
                }
                synchronized(callbacks) {
                    callbacks.removeFirst()
                }
                return FALSE
            }
        }

        synchronized(callbacks) {
            callbacks.offer(callback)
        }

        Gtk3.g_main_context_invoke(mainContext, callback, null)
    }


    @Synchronized
    fun proxyClick(callback: () -> Unit) {
        currentlyDispatching.set(true)
        runCatching {
            callback()
        }.onFailure {
            System.err.println("Error during GTK click callback: $it")
        }
        currentlyDispatching.set(false)
    }


    @Synchronized
    fun stop() {
        if (!running) return
        running = false
        dispatchAndWait {
            Gtk3.g_main_loop_quit(mainLoop)
        }
    }
}