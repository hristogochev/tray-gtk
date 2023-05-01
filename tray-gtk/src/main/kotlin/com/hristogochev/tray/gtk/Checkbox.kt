@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Checkbox
import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.SubMenu
import com.hristogochev.tray.gtk.util.opcr


/**
 * Creates a new menu checkbox and attaches it to its parent
 */
fun Menu.checkbox(
    text: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    tooltip: String? = null,
    op: Checkbox.() -> Unit = {}
): Checkbox {
    val checkbox = Checkbox().apply {
        this.text = text
        this.checked = checked
        this.enabled = enabled
        this.tooltip = tooltip
    }
    return opcr(this, checkbox, op)
}

/**
 * Creates a new submenu checkbox and attaches it to its parent
 */
fun SubMenu.checkbox(
    text: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    tooltip: String? = null,
    op: Checkbox.() -> Unit = {}
): Checkbox {
    val checkbox = Checkbox().apply {
        this.text = text
        this.checked = checked
        this.enabled = enabled
        this.tooltip = tooltip
    }
    return opcr(this, checkbox, op)
}