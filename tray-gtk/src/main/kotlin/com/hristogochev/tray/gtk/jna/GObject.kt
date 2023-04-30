@file:Suppress("FunctionName", "LocalVariableName")

package com.hristogochev.tray.gtk.jna

import com.sun.jna.Callback
import com.sun.jna.Pointer


internal object GObject {
    fun load() = runCatching {
        JNA.register(gobjectLibName, GObject::class.java)
        true
    }.onFailure {
        System.err.println("Failed to bind to GObject: $it")
    }.getOrElse { false }


    external fun g_object_ref(`object`: Pointer?)
    external fun g_object_unref(`object`: Pointer?)
    external fun g_object_force_floating(`object`: Pointer?)
    external fun g_object_ref_sink(`object`: Pointer?)
    external fun g_signal_connect_object(
        instance: Pointer?,
        detailed_signal: String?,
        c_handler: Callback?,
        `object`: Pointer?,
        connect_flags: Int
    ): Long

    external fun g_signal_handler_block(instance: Pointer?, handlerId: Long)
    external fun g_signal_handler_unblock(instance: Pointer?, handlerId: Long)
}
