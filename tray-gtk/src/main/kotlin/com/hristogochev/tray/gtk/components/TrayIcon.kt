@file:Suppress("unused")

package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.GObject
import com.hristogochev.tray.gtk.jna.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.sun.jna.Pointer
import com.hristogochev.tray.gtk.jna.structs.GEventCallback
import com.hristogochev.tray.gtk.jna.structs.GdkEventButton


/**
 * Native GTK tray icon
 */
class TrayIcon {
    @Volatile
    private var pointer: Pointer? = null
    private var callback: GEventCallback? = null

    var title: String? = null
        set(value) {
            field = value
            setGtkTitle(value)
        }
    var imagePath: String? = null
        set(value) {
            field = value
            setGtkImagePath(value)
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

    private fun setGtkImagePath(imagePath: String?) {
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
        Gtk3.gtk_status_icon_set_visible(pointer, false)
        GObject.g_object_unref(pointer)
        pointer = null
        callback = null
    }
}

