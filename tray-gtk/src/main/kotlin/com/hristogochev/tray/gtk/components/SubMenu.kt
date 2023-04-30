package com.hristogochev.tray.gtk.components

import com.sun.jna.Pointer
import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher


/**
 * Native GTK tray submenu
 */
class SubMenu : MenuEntry() {

    private val attachedEntries = mutableListOf<MenuEntry>()

    private lateinit var menuPointer: Pointer

    private var image: Pointer? = null

    var text: String? = null
        set(value) {
            field = value
            setGtkText(value)
        }

    var enabled: Boolean = false
        set(value) {
            field = value
            setGtkEnabled(value)
        }

    var tooltip: String? = null
        set(value) {
            field = value
            setGtkTooltip(value)
        }

    var imagePath: String? = null
        set(value) {
            field = value
            setGtkImage(value)
        }

    init {
        Gtk3Dispatcher.dispatchAndWait {
            this.pointer = Gtk3.gtk_image_menu_item_new_with_mnemonic("")!!
            this.menuPointer = Gtk3.gtk_menu_new()!!
            Gtk3.gtk_menu_item_set_submenu(pointer, menuPointer)
        }
    }

    fun attach(entry: MenuEntry) {
        attachedEntries.add(entry)
        Gtk3Dispatcher.dispatchAndWait {
            Gtk3.gtk_menu_shell_append(menuPointer, entry.pointer)
            GObject.g_object_ref_sink(entry.pointer)
        }
    }

    private fun setGtkText(text: String?) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_menu_item_set_label(pointer, text)
        }
    }

    private fun setGtkEnabled(enabled: Boolean) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_widget_set_sensitive(pointer, enabled)
        }
    }

    private fun setGtkTooltip(tooltip: String?) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_widget_set_tooltip_text(pointer, tooltip)
        }
    }

    private fun setGtkImage(imagePath: String?) {
        Gtk3Dispatcher.dispatch {
            if (image != null) Gtk3.gtk_container_remove(pointer, image)

            image = if (imagePath != null) Gtk3.gtk_image_new_from_file(imagePath) else null

            if (image != null) Gtk3.gtk_image_menu_item_set_image(pointer, image)

            Gtk3.gtk_image_menu_item_set_always_show_image(pointer, true)
        }
    }

    override fun destroy() {
        super.destroy()
        Gtk3Dispatcher.dispatchAndWait {
            if (image != null) {
                Gtk3.gtk_container_remove(pointer, image)
                image = null
            }
            for (entry in attachedEntries) {
                entry.destroy()
                Gtk3.gtk_container_remove(menuPointer, entry.pointer)
            }
            Gtk3.gtk_menu_item_set_submenu(pointer, null)
            Gtk3.gtk_widget_destroy(menuPointer)
        }
    }
}


