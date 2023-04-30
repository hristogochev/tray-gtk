package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.jna.TRUE
import com.hristogochev.tray.gtk.jna.structs.GCallback
import com.sun.jna.Pointer


/**
 * Native GTK tray item
 */
class Item : MenuEntry() {

    private var image: Pointer? = null

    var onClick: () -> Unit = {}

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

    private lateinit var callback: GCallback

    init {
        Gtk3Dispatcher.dispatchAndWait {
            this.pointer = Gtk3.gtk_image_menu_item_new_with_mnemonic("")!!
            this.callback = object : GCallback {
                override fun callback(instance: Pointer?, data: Pointer?): Int {
                    Gtk3Dispatcher.proxyClick(onClick)
                    return TRUE
                }
            }
            GObject.g_signal_connect_object(pointer, "activate", callback, null, 0)
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

    /**
     * Sets an action that executes whenever the item is clicked
     */
    fun action(action: () -> Unit) {
        onClick = action
    }

    override fun destroy() {
        super.destroy()
        Gtk3Dispatcher.dispatchAndWait {
            if (image != null) {
                Gtk3.gtk_container_remove(pointer, image)
                image = null
            }
        }
    }
}