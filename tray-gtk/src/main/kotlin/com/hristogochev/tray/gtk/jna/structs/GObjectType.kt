@file:Suppress("unused")

package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Pointer
import com.sun.jna.PointerType
import com.hristogochev.tray.gtk.jna.lib.GObject.g_object_ref
import com.hristogochev.tray.gtk.jna.lib.GObject.g_object_unref

internal open class GObjectType : PointerType {
    constructor()
    constructor(p: Pointer?) : super(p)

    fun ref() {
        g_object_ref(pointer)
    }

    fun unref() {
        g_object_unref(pointer)
    }

    protected open fun finalize() {
    }
}
