@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.Checkbox
import com.hristogochev.tray.gtk.util.opcr

/**
 * Creates a new checkbox and attaches it to its parent
 */
fun BaseMenu.checkbox(
    text: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    tooltip: String? = null,
    onToggle: (Boolean) -> Unit = {},
    op: Checkbox.() -> Unit = {}
): Checkbox {
    val checkbox = Checkbox().apply {
        this.text = text
        this.checked = checked
        this.enabled = enabled
        this.tooltip = tooltip
        this.onToggle = onToggle
    }
    return opcr(this, checkbox, op)
}