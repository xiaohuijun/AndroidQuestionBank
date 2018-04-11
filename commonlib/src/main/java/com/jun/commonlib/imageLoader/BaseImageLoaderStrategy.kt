package com.jun.commonlib.imageLoader

import android.content.Context
import android.widget.ImageView
import com.jun.commonlib.imageLoader.Listener.ImageLoadListener
import com.jun.commonlib.imageLoader.Listener.ImageSaveListener
import com.jun.commonlib.imageLoader.Listener.ProgressLoadListener
import com.jun.commonlib.imageLoader.Listener.SourceReadyListener
import com.jun.commonlib.imageLoader.config.ImageLoaderConfiguration

interface BaseImageLoaderStrategy {
    fun displayImage(url: String, imageView: ImageView);

    fun displayImageWithoutCache(uri: String, imageView: ImageView);

    fun displayImage(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration);

    fun loadBitmap(context: Context, url: String, imageLoaderConfiguration: ImageLoaderConfiguration, imageLoadListener: ImageLoadListener);

    fun displayGifImage(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration);

    fun displayImageWithProgress(url: String, imageView: ImageView, listener: ProgressLoadListener);

    fun displayImageWithPrepareCall(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration, listener: SourceReadyListener);

    fun displayGifWithPrepareCall(url: String, imageView: ImageView, listener: SourceReadyListener);

    fun displayImageRound(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration, roundSize: Int);

    //清除硬盘缓存
    fun clearImageDiskCache(context: Context);

    //清除内存缓存
    fun clearImageMemoryCache(context: Context);

    //根据不同的内存状态，来响应不同的内存释放策略
    fun trimMemory(context: Context, level: Int);

    //获取缓存大小
    fun getCacheSize(context: Context): String;

    fun saveImage(context: Context, url: String, savePath: String, saveFileName: String, listener: ImageSaveListener);

    fun displayImageNoScaleType(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration);

    fun displayImageSacleType(ctx: Context, url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration);

    fun downloadOnlyImage(ctx: Context, url: String, width: Int, height: Int);

    fun preloadImage(ctx: Context, url: String);

    fun displayImageNoScaleTypeAndPlaceHolder(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration);
}