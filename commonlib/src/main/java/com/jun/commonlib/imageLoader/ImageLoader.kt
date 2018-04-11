package com.jun.commonlib.imageLoader

import android.content.Context
import android.widget.ImageView
import com.jun.commonlib.imageLoader.Listener.ImageLoadListener
import com.jun.commonlib.imageLoader.Listener.ImageSaveListener
import com.jun.commonlib.imageLoader.Listener.ProgressLoadListener
import com.jun.commonlib.imageLoader.Listener.SourceReadyListener
import com.jun.commonlib.imageLoader.config.ImageLoaderConfiguration
import com.jun.commonlib.imageLoader.glide.GlideImageLoaderStrategy

object ImageLoader {
    private var mStrategy: BaseImageLoaderStrategy = GlideImageLoaderStrategy();

    fun displayImage(url: String, imageView: ImageView) {
        mStrategy.displayImage(url, imageView);
    }


    fun displayImageWithoutCache(url: String, imageView: ImageView) {
        mStrategy.displayImageWithoutCache(url, imageView);
    }

    fun loadBitmap(context: Context, url: String, imageLoaderConfiguration: ImageLoaderConfiguration, imageLoadListener: ImageLoadListener) {
        mStrategy.loadBitmap(context, url, imageLoaderConfiguration, imageLoadListener);
    }

    fun displayImage(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        mStrategy.displayImage(url, imageView, imageLoaderConfiguration);
    }

    fun displayImageSacleType(ctx: Context, url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        mStrategy.displayImageSacleType(ctx, url, imageView, imageLoaderConfiguration);
    }

    fun displayImageNoScaleType(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        mStrategy.displayImageNoScaleType(url, imageView, imageLoaderConfiguration);
    }

    fun displayImageNoScaleTypeAndPlaceHolder(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        mStrategy.displayImageNoScaleTypeAndPlaceHolder(url, imageView, imageLoaderConfiguration);
    }

    fun displayGifImage(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        mStrategy.displayGifImage(url, imageView, imageLoaderConfiguration);
    }


    fun displayImageWithProgress(url: String, imageView: ImageView, listener: ProgressLoadListener) {
        mStrategy.displayImageWithProgress(url, imageView, listener);
    }

    fun displayGifWithPrepareCall(url: String, imageView: ImageView, listener: SourceReadyListener) {
        mStrategy.displayGifWithPrepareCall(url, imageView, listener);
    }

    fun displayImageWithPrepareCall(url: String, imageView: ImageView, listener: SourceReadyListener, imageLoaderConfiguration: ImageLoaderConfiguration) {
        mStrategy.displayImageWithPrepareCall(url, imageView, imageLoaderConfiguration, listener);
    }

    fun displayImageRound(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration, roundSize: Int) {
        mStrategy.displayImageRound(url, imageView, imageLoaderConfiguration, roundSize);
    }

    /**
     * 策略模式的注入操作
     *
     * @param strategy
     */
    fun setLoadImgStrategy(strategy: BaseImageLoaderStrategy) {
        mStrategy = strategy;
    }


    /**
     * 清除图片磁盘缓存
     */
    fun clearImageDiskCache(context: Context) {
        mStrategy.clearImageDiskCache(context);
    }

    /**
     * 清除图片内存缓存
     */
    fun clearImageMemoryCache(context: Context) {
        mStrategy.clearImageMemoryCache(context);
    }

    /**
     * 根据不同的内存状态，来响应不同的内存释放策略
     *
     * @param context
     * @param level
     */
    fun trimMemory(context: Context, level: Int) {
        mStrategy.trimMemory(context, level);
    }

    /**
     * 清除图片所有缓存
     */
    fun clearImageAllCache(context: Context) {
        clearImageDiskCache(context.getApplicationContext());
        clearImageMemoryCache(context.getApplicationContext());
    }

    /**
     * 获取缓存大小
     *
     * @return CacheSize
     */
    fun getCacheSize(context: Context): String {
        return mStrategy.getCacheSize(context);
    }

    fun saveImage(context: Context, url: String, savePath: String, saveFileName: String, listener: ImageSaveListener) {
        mStrategy.saveImage(context, url, savePath, saveFileName, listener);
    }


    fun downloadOnlyImage(ctx: Context, url: String, width: Int, height: Int) {
        mStrategy.downloadOnlyImage(ctx, url, width, height);
    }

    fun preloadImage(ctx: Context, url: String) {
        mStrategy.preloadImage(ctx, url);
    }
}