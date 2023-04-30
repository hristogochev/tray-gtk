package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Callback
import com.sun.jna.Pointer

internal interface GEventCallback : Callback {
    fun callback(instance: Pointer?, event: GdkEventButton?)
}
