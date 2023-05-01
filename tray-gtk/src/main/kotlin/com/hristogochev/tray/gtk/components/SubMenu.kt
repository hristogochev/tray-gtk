package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.util.toPixBufPointer
import com.sun.jna.Memory
import com.sun.jna.Pointer
import java.awt.image.BufferedImage


/**
 * Native GTK tray submenu
 */
class SubMenu : MenuEntry(), BaseMenu {

    private val attachedEntries = mutableListOf<MenuEntry>()

    private var menuPointer: Pointer? = null

    private var imagePointer: Pointer? = null

    private var imagePixBuf: Pair<Memory, Pointer>? = null


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

    var image: BufferedImage? = null
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

    override fun attach(entry: MenuEntry) {
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
            if (imagePointer != null) Gtk3.gtk_container_remove(pointer, imagePointer)

            imagePointer = if (imagePath != null) Gtk3.gtk_image_new_from_file(imagePath) else null

            if (imagePointer != null) Gtk3.gtk_image_menu_item_set_image(pointer, imagePointer)

            Gtk3.gtk_image_menu_item_set_always_show_image(pointer, true)
        }
    }

    private fun setGtkImage(image: BufferedImage?) {
        Gtk3Dispatcher.dispatch {
            if (imagePointer != null) Gtk3.gtk_container_remove(pointer, imagePointer)

            imagePointer = if (image != null) {
                imagePixBuf = image.toPixBufPointer()
                Gtk3.gtk_image_new_from_pixbuf(imagePixBuf?.second)
            } else null

            if (imagePointer != null) Gtk3.gtk_image_menu_item_set_image(pointer, imagePointer)

            Gtk3.gtk_image_menu_item_set_always_show_image(pointer, true)
        }
    }


    override fun destroy() {
        Gtk3Dispatcher.dispatchAndWait {
            if (imagePointer != null) Gtk3.gtk_container_remove(pointer, imagePointer)
            imagePointer = null
            imagePixBuf = null

            for (entry in attachedEntries) {
                Gtk3.gtk_container_remove(menuPointer, entry.pointer)
                entry.destroy()
            }
            attachedEntries.clear()

            Gtk3.gtk_menu_item_set_submenu(pointer, null)

            Gtk3.gtk_widget_destroy(menuPointer)

            menuPointer = null

            super.destroy()
        }
    }
}


