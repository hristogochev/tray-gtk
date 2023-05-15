@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.Checkbox

/**
 * Creates a new checkbox and attaches it to its parent
 */
inline fun BaseMenu.checkbox(
    text: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    tooltip: String? = null,
    noinline onToggle: (Boolean) -> Unit = {},
    op: Checkbox.() -> Unit = {}
) = Checkbox().apply {
    this.text = text
    this.checked = checked
    this.enabled = enabled
    this.tooltip = tooltip
    this.onToggle = onToggle
    op(this)
    this@checkbox.attach(this)
}