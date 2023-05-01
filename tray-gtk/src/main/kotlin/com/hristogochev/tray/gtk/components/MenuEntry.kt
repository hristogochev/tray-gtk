package com.hristogochev.tray.gtk.components

import com.sun.jna.Pointer


/**
 * Shared GTK menu or submenu entry
 */
abstract class MenuEntry {
    var pointer: Pointer? = null

    open fun destroy() {
        pointer = null
    }
}
