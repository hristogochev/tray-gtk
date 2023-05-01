@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.TrayIcon

/**
 * Creates a new menu and attaches it to its parent tray icon
 */
fun TrayIcon.menu(op: Menu.() -> Unit = {}): Menu {
    val menu = Menu()
    op(menu)
    menu.show()
    this.menu = menu
    return menu
}