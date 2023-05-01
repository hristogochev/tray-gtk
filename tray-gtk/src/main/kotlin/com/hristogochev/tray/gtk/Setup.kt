@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.jna.GtkLoader

/**
 * Loads all necessary GTK3 libraries
 */
fun loadGtk(): Boolean {
    return GtkLoader.load()
}

/**
 * Starts the GTK dispatcher if it's not already running
 */
fun startGtkDispatcher(): Boolean {
    if (!Gtk3Dispatcher.start()) return false
    Gtk3Dispatcher.waitAllDispatches()
    return true
}

/**
 * Stops the GTK dispatchers
 */
fun stopGtkDispatcher() {
    Gtk3Dispatcher.stop()
}
