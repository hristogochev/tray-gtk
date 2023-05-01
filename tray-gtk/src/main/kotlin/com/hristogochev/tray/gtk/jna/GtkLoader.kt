package com.hristogochev.tray.gtk.jna

object GtkLoader {
    private var isLoaded = false

    fun load(): Boolean {
        if (isLoaded) return true
        isLoaded = Glib.load() && GObject.load() && Gtk3.load() && GdkPixBuf.load()
        return isLoaded
    }
}