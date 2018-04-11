package com.jun.commonlib.imageLoader.Listener

import android.graphics.drawable.Drawable

interface SourceReadyListener {
    fun onResourceReady(resource: Drawable?, width: Int?, height: Int?)

    fun onException()
}