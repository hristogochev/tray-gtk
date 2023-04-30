@file:Suppress("unused")

package com.hristogochev.tray.gtk

import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Glib
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.components.*

/**
 * Loads all necessary GTK3 libraries
 */
fun loadGtk(): Boolean {
    return Glib.load() && GObject.load() && Gtk3.load()
}

/**
 * Starts the GTK dispatcher if it's not already running
 */
fun startGtkDispatcher() {
    Gtk3Dispatcher.start()
    Gtk3Dispatcher.waitAllDispatches()
}

/**
 * Stops the GTK dispatchers
 */
fun stopGtkDispatcher() {
    Gtk3Dispatcher.stop()
}


// Have to save a reference to all created tray icons, so they don't get garbage collected
private val trayIcons = mutableListOf<TrayIcon>()

/**
 * Creates a new tray icon and attaches it to the system tray
 */
fun trayIcon(
    imagePath: String? = null,
    visible: Boolean,
    title: String? = null,
    tooltip: String? = null,
    menu: Menu? = null, op: TrayIcon.() -> Unit = {}
): TrayIcon {
    val icon = TrayIcon().apply {
        this.imagePath = imagePath
        this.visible = visible
        this.title = title
        this.tooltip = tooltip
        this.menu = menu
    }
    op(icon)
    trayIcons.add(icon)
    return icon
}

/**
 * Creates a new menu and attaches it to its parent tray icon
 */
fun TrayIcon.menu(op: Menu.() -> Unit = {}): Menu {
    val menu = Menu()
    op(menu)
    menu.show()
    this.menu = menu
    return menu
}

/**
 * Creates a new menu separator and attaches it to its parent
 */
fun Menu.separator(op: Separator.() -> Unit = {}) = opcr(this, Separator(), op)

/**
 * Creates a new menu item and attaches it to its parent
 */
fun Menu.item(
    text: String? = null,
    enabled: Boolean = true,
    imagePath: String? = null,
    tooltip: String? = null,
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
 * Creates a new menu submenu and attaches it to its parent
 */
fun Menu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    imagePath: String? = null,
    tooltip: String? = null, op: SubMenu.() -> Unit = {}
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
 * Creates a new submenu separator and attaches it to its parent
 */
fun SubMenu.separator(op: Separator.() -> Unit = {}) = opcr(this, Separator(), op)

/**
 * Creates a new submenu item and attaches it to its parent
 */
fun SubMenu.item(
    text: String? = null,
    enabled: Boolean = true,
    imagePath: String? = null,
    tooltip: String? = null, op: Item.() -> Unit = {}
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
 * Creates a new submenu checkbox and attaches it to its parent
 */
fun SubMenu.checkbox(
    text: String? = null,
    checked: Boolean = false,
    enabled: Boolean = true,
    tooltip: String? = null, op: Checkbox.() -> Unit = {}
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
 * Creates a new submenu submenu and attaches it to its parent
 */
fun SubMenu.submenu(
    text: String? = null,
    enabled: Boolean = true,
    imagePath: String? = null,
    tooltip: String? = null, op: SubMenu.() -> Unit = {}
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
 * Executes an action on a menu entry and then attaches it to a parent
 */
private inline fun <T : MenuEntry> opcr(parent: Menu, entry: T, op: T.() -> Unit = {}) = entry.apply {
    op(this)
    parent.attach(entry)
}

/**
 * Executes an action on a submenu entry and then attaches it to a parent
 */
private inline fun <T : MenuEntry> opcr(parent: SubMenu, entry: T, op: T.() -> Unit = {}) = entry.apply {
    op(this)
    parent.attach(entry)
}