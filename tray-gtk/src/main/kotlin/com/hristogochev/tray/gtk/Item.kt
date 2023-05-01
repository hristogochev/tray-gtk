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
    op: Item.() -> Unit = {}
) {
    item(text, enabled, tooltip, imagePath = null, op)
}

/**
 * Creates a new menu item and attaches it to its parent
 */
fun Menu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    imagePath: String? = null,
    op: Item.() -> Unit = {}
): Item {
    val item = Item().apply {
        this.text = text
        this.enabled = enabled
        this.imagePath = imagePath
        this.tooltip = tooltip
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
    op: Item.() -> Unit = {}
): Item {
    val item = Item().apply {
        this.text = text
        this.enabled = enabled
        this.image = image
        this.tooltip = tooltip
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
    op: Item.() -> Unit = {}
): Item {
    return item(text, enabled, tooltip, imagePath = null, op)
}

/**
 * Creates a new submenu item and attaches it to its parent
 */
fun SubMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    imagePath: String? = null,
    op: Item.() -> Unit = {}
): Item {
    val item = Item().apply {
        this.text = text
        this.enabled = enabled
        this.imagePath = imagePath
        this.tooltip = tooltip
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
    op: Item.() -> Unit = {}
): Item {
    val item = Item().apply {
        this.text = text
        this.enabled = enabled
        this.image = image
        this.tooltip = tooltip
    }
    return opcr(this, item, op)
}

