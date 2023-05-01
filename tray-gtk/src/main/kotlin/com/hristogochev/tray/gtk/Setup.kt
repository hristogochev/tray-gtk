@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.jna.*
import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Glib
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher

/**
 * Loads all necessary GTK3 libraries
 */
fun loadGtk(): Boolean {
    return Glib.load() && GObject.load() && Gtk3.load() && GdkPixBuf.load()
}

/**
 * Starts the GTK dispatcher if it's not already running
 */
fun startGtkDispatcher() {
    Gtk3Dispatcher.start()
    Gtk3Dispatcher.waitAllDispatches()
}

/**
 * Stops the GTK dispatchers
 */
fun stopGtkDispatcher() {
    Gtk3Dispatcher.stop()
}
