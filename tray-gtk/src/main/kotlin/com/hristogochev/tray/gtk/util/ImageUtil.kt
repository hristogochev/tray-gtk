package com.hristogochev.tray.gtk.util

import com.hristogochev.tray.gtk.jna.lib.GdkPixBuf
import com.sun.jna.Memory
import com.sun.jna.Pointer
import java.awt.image.BufferedImage


fun BufferedImage.toPixBufPointer(): Pair<Memory, Pointer>? {
    val imageSize = height * width

    val data = Memory(imageSize.toLong() * 4)

    var bytePos: Long = 0

    for (i in 0 until imageSize) {

        val y = i / width
        val x = i % width

        val rgba = getRGB(x, y)

        val pos = i + bytePos

        val r = ((rgba shr 16) and 255).toByte()
        val g = ((rgba shr 8) and 255).toByte()
        val b = (rgba and 0xFF).toByte()
        val a = ((rgba shr 24) and 255).toByte()

        data.setByte(pos + 0, r)
        data.setByte(pos + 1, g)
        data.setByte(pos + 2, b)
        data.setByte(pos + 3, a)

        bytePos += 3
    }

    val colorSpace = 0
    val hasAlpha = true
    val bitsPerSample = 8
    val width = width
    val height = height
    val rowStride = width * 4
    val destroyFn = null
    val destroyFnData = null

    val pointer = GdkPixBuf.gdk_pixbuf_new_from_data(
        data,
        colorSpace,
        hasAlpha,
        bitsPerSample,
        width,
        height,
        rowStride,
        destroyFn,
        destroyFnData
    )
    return if (pointer != null) Pair(data, pointer) else null
}

