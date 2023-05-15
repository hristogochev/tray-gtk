@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.components.Menu
import com.hristogochev.tray.gtk.components.TrayIcon
import java.awt.image.BufferedImage

// Have to save a reference to all created tray icons, so they don't get garbage collected
val trayIcons = mutableListOf<TrayIcon>()

/**
 * Creates a new tray icon and attaches it to the system tray
 */
inline fun trayIcon(
    imagePath: String,
    visible: Boolean = true,
    title: String? = null,
    tooltip: String? = null,
    menu: Menu? = null, op: TrayIcon.() -> Unit = {}
) = TrayIcon().apply {
    this.imagePath = imagePath
    this.visible = visible
    this.title = title
    this.tooltip = tooltip
    this.menu = menu
    op(this)
    trayIcons.add(this)
}

/**
 * Creates a new tray icon and attaches it to the system tray
 */
inline fun trayIcon(
    image: BufferedImage,
    visible: Boolean = true,
    title: String? = null,
    tooltip: String? = null,
    menu: Menu? = null, op: TrayIcon.() -> Unit = {}
) = TrayIcon().apply {
    this.image = image
    this.visible = visible
    this.title = title
    this.tooltip = tooltip
    this.menu = menu
    op(this)
    trayIcons.add(this)
}

