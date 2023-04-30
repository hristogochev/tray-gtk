package com.hristogochev.tray.gtk.jna

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.NativeLibrary

internal object JNA {
    fun register(libraryName: String, clazz: Class<*>): NativeLibrary {
        val options: MutableMap<String, Any?> = HashMap()

        options[Library.OPTION_CLASSLOADER] = clazz.classLoader

        val library = NativeLibrary.getInstance(libraryName, options)

        Native.register(clazz, library)
        return library
    }
}
