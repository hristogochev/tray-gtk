@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.SubMenu
import com.hristogochev.tray.gtk.util.opcr
import java.awt.image.BufferedImage

/**
 * Creates a new menu submenu and attaches it to its parent
 */
fun Menu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    op: SubMenu.() -> Unit = {}
): SubMenu {
    return submenu(text, enabled, tooltip, imagePath = null, op)
}

/**
 * Creates a new menu submenu and attaches it to its parent
 */
fun Menu.submenu(
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
 * Creates a new menu submenu and attaches it to its parent
 */
fun Menu.submenu(
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



/**
 * Creates a new submenu submenu and attaches it to its parent
 */
fun SubMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    tooltip: String? = null,
    op: SubMenu.() -> Unit = {}
): SubMenu {
    return submenu(text, enabled, tooltip, imagePath = null, op)
}

/**
 * Creates a new submenu submenu and attaches it to its parent
 */
fun SubMenu.submenu(
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
fun SubMenu.submenu(
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
