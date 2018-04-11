package com.jun.commonlib.imageLoader.Listener

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

interface ImageLoadListener {

    fun onLoadSuccess(resource: Bitmap);

    fun onLoadFail();

    fun onLoadCleared(placeholder: Drawable?);
}