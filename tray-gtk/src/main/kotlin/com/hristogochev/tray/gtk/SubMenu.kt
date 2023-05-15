@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.SubMenu
import java.awt.image.BufferedImage


/**
 * Creates a new submenu submenu and attaches it to its parent
 */
inline fun BaseMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    op: SubMenu.() -> Unit = {}
) = submenu(text, enabled, tooltip, imagePath = null, op)


/**
 * Creates a new submenu submenu and attaches it to its parent
 */
inline fun BaseMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    imagePath: String? = null,
    op: SubMenu.() -> Unit = {}
) = SubMenu().apply {
    this.text = text
    this.enabled = enabled
    this.imagePath = imagePath
    this.tooltip = tooltip
    op(this)
    this@submenu.attach(this)
}

/**
 * Creates a new submenu submenu and attaches it to its parent
 */
inline fun BaseMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    image: BufferedImage? = null,
    op: SubMenu.() -> Unit = {}
) = SubMenu().apply {
    this.text = text
    this.enabled = enabled
    this.image = image
    this.tooltip = tooltip
    op(this)
    this@submenu.attach(this)
}
