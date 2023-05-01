@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.SubMenu
import com.hristogochev.tray.gtk.util.opcr
import java.awt.image.BufferedImage


/**
 * Creates a new submenu submenu and attaches it to its parent
 */
fun BaseMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    op: SubMenu.() -> Unit = {}
) = submenu(text, enabled, tooltip, imagePath = null, op)


/**
 * Creates a new submenu submenu and attaches it to its parent
 */
fun BaseMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    imagePath: String? = null,
    op: SubMenu.() -> Unit = {}
): SubMenu {
    val subMenu = SubMenu().apply {
        this.text = text
        this.enabled = enabled
        this.imagePath = imagePath
        this.tooltip = tooltip
    }
    return opcr(this, subMenu, op)
}

/**
 * Creates a new submenu submenu and attaches it to its parent
 */
fun BaseMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    image: BufferedImage? = null,
    op: SubMenu.() -> Unit = {}
): SubMenu {
    val subMenu = SubMenu().apply {
        this.text = text
        this.enabled = enabled
        this.image = image
        this.tooltip = tooltip
    }
    return opcr(this, subMenu, op)
}
