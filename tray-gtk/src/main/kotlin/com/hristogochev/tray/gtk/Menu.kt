@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.TrayIcon

/**
 * Creates a new menu and attaches it to its parent tray icon
 */
inline fun TrayIcon.menu(op: Menu.() -> Unit = {}) = Menu().apply {
    op(this)
    show()
    this@menu.menu = this
}