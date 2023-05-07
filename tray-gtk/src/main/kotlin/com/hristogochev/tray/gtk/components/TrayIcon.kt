package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.jna.structs.GEventCallback
import com.hristogochev.tray.gtk.jna.structs.GdkEventButton
import com.hristogochev.tray.gtk.util.toPixBufPointer
import com.sun.jna.Memory
import com.sun.jna.Pointer
import java.awt.image.BufferedImage


/**
 * Native GTK tray icon
 */
class TrayIcon {
    private var pointer: Pointer? = null
    private var callback: GEventCallback? = null
    private var imagePixBuf: Pair<Memory, Pointer>? = null

    var title: String? = null
        set(value) {
            field = value
            setGtkTitle(value)
        }
    var imagePath: String? = null
        set(value) {
            field = value
            setGtkImageFromPath(value)
        }
    var image: BufferedImage? = null
        set(value) {
            field = value
            setGtkImage(value)
        }
    var visible: Boolean = false
        set(value) {
            field = value
            setGtkVisible(value)
        }

    var tooltip: String? = null
        set(value) {
            field = value
            setGtkTooltip(value)
        }

    var menu: Menu? = null

    init {
        Gtk3Dispatcher.dispatch {
            val pointer = Gtk3.gtk_status_icon_new()
            this.pointer = pointer

            this.callback = object : GEventCallback {
                override fun callback(instance: Pointer?, event: GdkEventButton?) {
                    if (event?.type == 4) {
                        Gtk3.gtk_menu_popup(
                            menu?.menuPointer, null, null, Gtk3.gtk_status_icon_position_menu,
                            pointer, 0, event.time
                        )
                    }
                }
            }
            GObject.g_signal_connect_object(pointer, "button_press_event", callback, null, 0)
        }

        Gtk3Dispatcher.waitAllDispatches()
    }

    private fun setGtkTitle(title: String?) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_status_icon_set_title(pointer, title)
        }
    }

    private fun setGtkImage(image: BufferedImage?) {
        Gtk3Dispatcher.dispatch {
            imagePixBuf = image?.toPixBufPointer()
            Gtk3.gtk_status_icon_set_from_pixbuf(pointer, imagePixBuf?.second)
        }
    }

    private fun setGtkImageFromPath(imagePath: String?) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_status_icon_set_from_file(pointer, imagePath)
        }
    }

    private fun setGtkVisible(visible: Boolean) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_status_icon_set_visible(pointer, visible)
        }
    }

    private fun setGtkTooltip(tooltip: String?) {
        Gtk3Dispatcher.dispatch {
            Gtk3.gtk_status_icon_set_tooltip_text(pointer, tooltip)
        }
    }

    fun destroy() {
        menu?.destroy()
        callback = null
        Gtk3.gtk_status_icon_set_visible(pointer, false)
        imagePixBuf = null
        GObject.g_object_unref(pointer)
        pointer = null
    }
}

