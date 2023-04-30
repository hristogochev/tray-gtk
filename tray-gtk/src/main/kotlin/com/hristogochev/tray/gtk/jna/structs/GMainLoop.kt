@file:Suppress("unused")

package com.hristogochev.tray.gtk.jna.structs

import com.sun.jna.Pointer

internal class GMainLoop : GObjectType {
    constructor()
    constructor(p: Pointer?) : super(p)
}
