package com.jun.commonlib.imageLoader.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.jun.commonlib.config.BaseConstants
import java.io.InputStream


@GlideModule
class MGlideModule : AppGlideModule {
    constructor() : super()

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setMemoryCache(LruResourceCache(BaseConstants.MAXMEMORY_CACHESIZE))
        builder.setBitmapPool(LruBitmapPool(BaseConstants.MAXMEMORY_CACHESIZE))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, BaseConstants.SD_PATH_PIC, BaseConstants.DISC_CACHESIZE))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(ProgressInterceptor.getOkHttpClient()))
    }
}
