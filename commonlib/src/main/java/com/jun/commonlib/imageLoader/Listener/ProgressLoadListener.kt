package com.jun.commonlib.imageLoader.Listener

import android.graphics.drawable.Drawable

interface ProgressLoadListener {
    fun update(bytesRead: Long, contentLength: Long)

    fun onException()

    fun onResourceReady(resource: Drawable?)
}