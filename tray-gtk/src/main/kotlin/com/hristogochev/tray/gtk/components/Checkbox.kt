package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.lib.GObject
import com.hristogochev.tray.gtk.jna.lib.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher
import com.hristogochev.tray.gtk.jna.TRUE
import com.sun.jna.Pointer
import com.hristogochev.tray.gtk.jna.structs.GCallback

/**
 * Native GTK tray checkbox
 */
class Checkbox : MenuEntry() {

    private var onClick: () -> Unit = {
        checked = !checked
        onToggle(checked)
    }

    var onToggle: (Boolean) -> Unit = {}

    var checked: Boolean = false
        set(value) {
            field = value
            setGtkChecked(value)
        }

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


    private var callback: GCallback? = null
    private var signalId: Long = 0

    init {
        Gtk3Dispatcher.dispatchAndWait {
            this.pointer = Gtk3.gtk_check_menu_item_new_with_mnemonic("")!!

            this.callback = object : GCallback {
                override fun callback(instance: Pointer?, data: Pointer?): Int {
                    Gtk3Dispatcher.proxyClick(onClick)
                    return TRUE
                }
            }
            this.signalId = GObject.g_signal_connect_object(pointer, "activate", callback, null, 0)

            GObject.g_signal_handler_block(pointer, signalId)
            Gtk3.gtk_check_menu_item_set_active(pointer, false)
            GObject.g_signal_handler_unblock(pointer, signalId)
        }
    }

    private fun setGtkChecked(checked: Boolean) {
        Gtk3Dispatcher.dispatch {
            GObject.g_signal_handler_block(pointer, signalId)
            Gtk3.gtk_check_menu_item_set_active(pointer, checked)
            GObject.g_signal_handler_unblock(pointer, signalId)
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

    /**
     * Sets an action that executes whenever the checkbox is toggled
     */
    fun action(action: (Boolean) -> Unit) {
        onToggle = action
    }

    override fun destroy() {
        callback = null
        super.destroy()
    }
}