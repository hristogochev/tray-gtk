@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.BaseMenu
import com.hristogochev.tray.gtk.components.Item
import com.hristogochev.tray.gtk.util.opcr
import java.awt.image.BufferedImage


/**
 * Creates a new item and attaches it to its parent
 */
fun BaseMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
) = item(text, enabled, tooltip, imagePath = null, onClick, op)

/**
 * Creates a new item and attaches it to its parent
 */
fun BaseMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    imagePath: String? = null,
    onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
): Item {
    val item = Item().apply {
        this.text = text
        this.enabled = enabled
        this.tooltip = tooltip
        this.imagePath = imagePath
        this.onClick = onClick
    }
    return opcr(this, item, op)
}

/**
 * Creates a new item and attaches it to its parent
 */
fun BaseMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    image: BufferedImage? = null,
    onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
): Item {
    val item = Item().apply {
        this.text = text
        this.enabled = enabled
        this.tooltip = tooltip
        this.image = image
        this.onClick = onClick
    }
    return opcr(this, item, op)
}

