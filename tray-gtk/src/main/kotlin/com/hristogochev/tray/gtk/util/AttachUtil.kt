package com.hristogochev.tray.gtk.util

import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.MenuEntry
import com.hristogochev.tray.gtk.components.SubMenu

/**
 * Executes an action on a menu entry and then attaches it to a parent
 */
internal inline fun <T : MenuEntry> opcr(parent: Menu, entry: T, op: T.() -> Unit = {}) = entry.apply {
    op(this)
    parent.attach(entry)
}

/**
 * Executes an action on a submenu entry and then attaches it to a parent
 */
internal inline fun <T : MenuEntry> opcr(parent: SubMenu, entry: T, op: T.() -> Unit = {}) = entry.apply {
    op(this)
    parent.attach(entry)
}