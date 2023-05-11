@file:Suppress("LocalVariableName", "FunctionName")

package com.hristogochev.tray.gtk.jna.lib

import com.hristogochev.tray.gtk.jna.JNA
import com.hristogochev.tray.gtk.jna.glibLibName
import com.sun.jna.Callback
import com.sun.jna.Pointer


internal object Glib {
    fun load() = runCatching {
        JNA.register(glibLibName, Glib::class.java)
        true
    }.onFailure {
        System.err.println("Failed to bind to Glib: $it")
    }.getOrElse { false }


    val nullLogFunc: GLogFunc = object : GLogFunc {
        override fun callback(log_domain: String?, log_level: Int, message: String?, data: Pointer?) {

        }
    }

    external fun g_log_set_default_handler(log_func: GLogFunc?, user_data: Pointer?): GLogFunc?
    interface GLogFunc : Callback {
        fun callback(log_domain: String?, log_level: Int, message: String?, data: Pointer?)
    }
}
