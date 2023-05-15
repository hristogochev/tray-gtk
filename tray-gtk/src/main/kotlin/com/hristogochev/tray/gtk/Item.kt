@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.Item
import java.awt.image.BufferedImage


/**
 * Creates a new item and attaches it to its parent
 */
inline fun BaseMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    noinline onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
) = item(text, enabled, tooltip, imagePath = null, onClick, op)

/**
 * Creates a new item and attaches it to its parent
 */
inline fun BaseMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    imagePath: String? = null,
    noinline onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
) = Item().apply {
    this.text = text
    this.enabled = enabled
    this.tooltip = tooltip
    this.imagePath = imagePath
    this.onClick = onClick
    op(this)
    this@item.attach(this)
}

/**
 * Creates a new item and attaches it to its parent
 */
inline fun BaseMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    image: BufferedImage? = null,
    noinline onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
) = Item().apply {
    this.text = text
    this.enabled = enabled
    this.tooltip = tooltip
    this.image = image
    this.onClick = onClick
    op(this)
    this@item.attach(this)
}

