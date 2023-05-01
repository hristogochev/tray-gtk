package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.jna.TRUE
import com.hristogochev.tray.gtk.jna.structs.GCallback
import com.hristogochev.tray.gtk.util.toPixBufPointer
import com.sun.jna.Memory
import com.sun.jna.Pointer
import java.awt.image.BufferedImage

/**
 * Native GTK tray item
 */
class Item : MenuEntry() {

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

    var image: BufferedImage? = null
        set(value) {
            field = value
            setGtkImage(value)
        }


    private var callback: GCallback? = null

    private var imagePointer: Pointer? = null

    private var imagePixBuf: Pair<Memory, Pointer>? = null


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

    /**
     * Sets an action that executes whenever the item is clicked
     */
    fun action(action: () -> Unit) {
        onClick = action
    }

    override fun destroy() {
        Gtk3Dispatcher.dispatchAndWait {
            if (imagePointer != null) Gtk3.gtk_container_remove(pointer, imagePointer)
            imagePixBuf = null
            imagePointer = null
            callback = null
            super.destroy()
        }
    }
}