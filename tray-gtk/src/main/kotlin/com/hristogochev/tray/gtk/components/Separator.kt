package com.hristogochev.tray.gtk.components

import com.hristogochev.tray.gtk.jna.lib.Gtk3
import com.hristogochev.tray.gtk.jna.Gtk3Dispatcher


/**
 * Native GTK tray separator
 */
class Separator : MenuEntry() {
    init{
        Gtk3Dispatcher.dispatchAndWait{
            pointer = Gtk3.gtk_separator_menu_item_new()!!
        }
    }
}