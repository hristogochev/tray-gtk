package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Callback
import com.sun.jna.Pointer

internal interface FuncCallback : Callback {
    fun callback(data: Pointer?): Int
}
