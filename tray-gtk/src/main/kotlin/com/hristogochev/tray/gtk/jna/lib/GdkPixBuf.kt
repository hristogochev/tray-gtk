@file:Suppress("FunctionName","LocalVariableName", "SpellCheckingInspection","unused")

package com.hristogochev.tray.gtk.jna.lib

import com.hristogochev.tray.gtk.jna.JNA
import com.hristogochev.tray.gtk.jna.gdkPixBufLibName
import com.sun.jna.Memory
import com.sun.jna.Pointer


object GdkPixBuf {
    fun load() = runCatching {
        JNA.register(gdkPixBufLibName, GdkPixBuf::class.java)
        true
    }.onFailure {
        System.err.println("Failed to bind to GdkPixBuf: $it")
    }.getOrElse { false }


    external fun gdk_pixbuf_new_from_data(
        data: Memory,
        colorspace: Int,
        has_alpha: Boolean,
        bits_per_sample: Int,
        width: Int,
        height: Int,
        rowstride: Int,
        destroy_fn: Pointer?,
        destroy_fn_data: Pointer?
    ): Pointer?
}