@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.Separator
import com.hristogochev.tray.gtk.util.opcr

/**
 * Creates a new separator and attaches it to its parent
 */
fun BaseMenu.separator(op: Separator.() -> Unit = {}) = opcr(this, Separator(), op)