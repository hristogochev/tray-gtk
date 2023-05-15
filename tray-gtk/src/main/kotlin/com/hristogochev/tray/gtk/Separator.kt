@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.Separator

/**
 * Creates a new separator and attaches it to its parent
 */
inline fun BaseMenu.separator(op: Separator.() -> Unit = {}) = Separator().apply {
    op(this)
    this@separator.attach(this)
}