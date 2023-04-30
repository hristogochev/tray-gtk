@file:Suppress("unused", "PropertyName")

package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Pointer
import com.sun.jna.Structure

internal class GdkEventButton : Structure() {
    @JvmField
    var type = 0

    @JvmField
    var window: Pointer? = null

    @JvmField
    var send_event = 0

    @JvmField
    var time = 0

    @JvmField
    var x = 0.0

    @JvmField
    var y = 0.0

    @JvmField
    var axes: Pointer? = null

    @JvmField
    var state = 0

    @JvmField
    var button = 0

    @JvmField
    var device: Pointer? = null

    @JvmField
    var x_root = 0.0

    @JvmField
    var y_root = 0.0
    override fun getFieldOrder(): List<String> {
        return mutableListOf(
            "type",
            "window",
            "send_event",
            "time",
            "x",
            "y",
            "axes",
            "state",
            "button",
            "device",
            "x_root",
            "y_root"
        )
    }
}
