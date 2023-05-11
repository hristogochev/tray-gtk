package com.hristogochev.tray.gtk.components

import com.sun.jna.Pointer
import com.hristogochev.tray.gtk.jna.lib.GObject
import com.hristogochev.tray.gtk.jna.lib.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher


/**
 * Native GTK tray menu
 */
class Menu : BaseMenu {

    private val attachedEntries = mutableListOf<MenuEntry>()

    var menuPointer: Pointer? = null

    init {
        Gtk3Dispatcher.dispatchAndWait {
            menuPointer = Gtk3.gtk_menu_new()
        }
    }

    override fun attach(entry: MenuEntry) {
        attachedEntries.add(entry)
        Gtk3Dispatcher.dispatchAndWait {
            Gtk3.gtk_menu_shell_append(menuPointer, entry.pointer)
            GObject.g_object_ref_sink(entry.pointer)
        }
    }

    fun show() {
        Gtk3Dispatcher.dispatchAndWait {
            Gtk3.gtk_widget_show_all(menuPointer)
        }
    }

    fun destroy() {
        Gtk3Dispatcher.dispatchAndWait {
            for (entry in attachedEntries) {
                Gtk3.gtk_container_remove(menuPointer, entry.pointer)
                entry.destroy()
            }
            attachedEntries.clear()
            Gtk3.gtk_widget_destroy(menuPointer)
            menuPointer = null
        }
    }
}






