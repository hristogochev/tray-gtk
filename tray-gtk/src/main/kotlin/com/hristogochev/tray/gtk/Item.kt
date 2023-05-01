@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Item
import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.SubMenu
import com.hristogochev.tray.gtk.util.opcr
import java.awt.image.BufferedImage

/**
 * Creates a new menu item and attaches it to its parent
 */
fun Menu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
) = item(text, enabled, tooltip, imagePath = null, onClick, op)

/**
 * Creates a new menu item and attaches it to its parent
 */
fun Menu.item(
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
 * Creates a new menu item and attaches it to its parent
 */
fun Menu.item(
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

/**
 * Creates a new submenu item and attaches it to its parent
 */
fun SubMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    onClick: () -> Unit = {},
    op: Item.() -> Unit = {}
)= item(text, enabled, tooltip, imagePath = null, onClick, op)

/**
 * Creates a new submenu item and attaches it to its parent
 */
fun SubMenu.item(
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
 * Creates a new submenu item and attaches it to its parent
 */
fun SubMenu.item(
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

