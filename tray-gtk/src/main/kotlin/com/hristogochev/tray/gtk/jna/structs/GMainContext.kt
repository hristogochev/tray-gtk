@file:Suppress("unused")

package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Pointer

internal class GMainContext : GObjectType {
    constructor()
    constructor(p: Pointer?) : super(p)
}
