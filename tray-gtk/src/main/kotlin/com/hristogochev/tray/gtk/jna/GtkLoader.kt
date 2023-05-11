package com.hristogochev.tray.gtk.jna

import com.hristogochev.tray.gtk.jna.lib.GObject
import com.hristogochev.tray.gtk.jna.lib.GdkPixBuf
import com.hristogochev.tray.gtk.jna.lib.Glib
import com.hristogochev.tray.gtk.jna.lib.Gtk3

object GtkLoader {
    private var isLoaded = false

    fun load(): Boolean {
        if (isLoaded) return true
        isLoaded = Glib.load() && GObject.load() && Gtk3.load() && GdkPixBuf.load()
        return isLoaded
    }
}