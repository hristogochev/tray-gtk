@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.Separator
import com.hristogochev.tray.gtk.components.SubMenu
import com.hristogochev.tray.gtk.util.opcr

/**
 * Creates a new menu separator and attaches it to its parent
 */
fun Menu.separator(op: Separator.() -> Unit = {}) = opcr(this, Separator(), op)

/**
 * Creates a new submenu separator and attaches it to its parent
 */
fun SubMenu.separator(op: Separator.() -> Unit = {}) = opcr(this, Separator(), op)