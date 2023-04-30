package com.hristogochev.tray.gtk.components

import com.sun.jna.Pointer
import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher


/**
 * Shared GTK menu or submenu entry
 */
abstract class MenuEntry {
    lateinit var pointer: Pointer

    open fun destroy() {
        Gtk3Dispatcher.dispatchAndWait {
            GObject.g_object_force_floating(pointer)
        }
    }
}
