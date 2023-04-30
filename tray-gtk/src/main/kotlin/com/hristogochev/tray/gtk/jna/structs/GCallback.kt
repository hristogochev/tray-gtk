package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Callback
import com.sun.jna.Pointer

internal interface GCallback : Callback {
    fun callback(instance: Pointer?, data: Pointer?): Int
}
