package com.hristogochev.tray.gtk.util

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.MenuEntry

/**
 * Executes an action on a submenu entry and then attaches it to a parent
 */
internal inline fun <T : MenuEntry> opcr(parent: BaseMenu, entry: T, op: T.() -> Unit = {}) = entry.apply {
    op(this)
    parent.attach(entry)
}