package com.jun.commonlib.imageLoader.glide

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.jun.commonlib.R
import com.jun.commonlib.imageLoader.BaseImageLoaderStrategy
import com.jun.commonlib.imageLoader.Listener.ImageLoadListener
import com.jun.commonlib.imageLoader.Listener.ImageSaveListener
import com.jun.commonlib.imageLoader.Listener.ProgressLoadListener
import com.jun.commonlib.imageLoader.Listener.SourceReadyListener
import com.jun.commonlib.imageLoader.config.ImageLoaderConfiguration
import com.jun.commonlib.utils.FileUtils
import jp.wasabeef.glide.transformations.BlurTransformation
import java.io.*

class GlideImageLoaderStrategy : BaseImageLoaderStrategy {
    private val DEFAULT_PLACE_HOLDER = R.drawable.ic_default_pic_bg
    private val DEFAULT_ERROR = R.drawable.ic_default_pic_bg
    override fun displayImage(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions()
                        .error(imageView.drawable)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(imageView)
    }

    override fun displayImageWithoutCache(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
                .asDrawable()
                .transition(GenericTransitionOptions.with(R.anim.load_img_anim))
                .apply(RequestOptions().skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(url)
                .into(imageView)
    }

    override fun displayImage(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        loadNormal(imageView.context, url, imageView, imageLoaderConfiguration)
    }

    fun loadNormal(context: Context, url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        val isNeedBlur = imageLoaderConfiguration.isBlur
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val placeholder = if (imageLoaderConfiguration.placeHolder == 0) DEFAULT_PLACE_HOLDER else imageLoaderConfiguration.placeHolder
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error
        val resizeWidth = if (imageLoaderConfiguration.resizeWidth == -1) Target.SIZE_ORIGINAL else imageLoaderConfiguration.resizeWidth
        val resizeHeight = if (imageLoaderConfiguration.resizeHeight == -1) Target.SIZE_ORIGINAL else imageLoaderConfiguration.resizeHeight

        val requestOptions = RequestOptions()
                .centerCrop()
                .placeholder(if (isShowPlaceHolder) imageView.getContext().getResources().getDrawable(placeholder) else imageView.getDrawable())
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(resizeWidth, resizeHeight)
        if (isNeedBlur) {
            requestOptions.transform(BlurTransformation(imageLoaderConfiguration.blurProgress, imageLoaderConfiguration.blurScale))
        }
        Glide.with(imageView.context)
                .load(url)
                .transition(GenericTransitionOptions.with(R.anim.load_img_anim))
                .apply(requestOptions)
                .into(imageView)
    }

    override fun loadBitmap(context: Context, url: String, imageLoaderConfiguration: ImageLoaderConfiguration, imageLoadListener: ImageLoadListener) {
        val resizeWidth = if (imageLoaderConfiguration.resizeWidth == -1) Target.SIZE_ORIGINAL else imageLoaderConfiguration.resizeWidth
        val resizeHeight = if (imageLoaderConfiguration.resizeHeight == -1) Target.SIZE_ORIGINAL else imageLoaderConfiguration.resizeHeight
        Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object : SimpleTarget<Bitmap>(resizeWidth, resizeHeight) {
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        imageLoadListener.onLoadFail()
                    }

                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imageLoadListener.onLoadSuccess(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        imageLoadListener.onLoadCleared(placeholder)
                    }
                })
    }

    override fun displayGifImage(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val placeholder = if (imageLoaderConfiguration.placeHolder == 0) DEFAULT_PLACE_HOLDER else imageLoaderConfiguration.placeHolder
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error
        Glide.with(imageView.context)
                .asGif()
                .load(url)
                .apply(RequestOptions()
                        .placeholder(if (isShowPlaceHolder) imageView.getContext().getResources().getDrawable(placeholder) else imageView.getDrawable())
                        .dontAnimate()
                        .error(error)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: GifDrawable?, model: Any?, target: Target<GifDrawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(imageView)
    }

    override fun displayImageWithProgress(url: String, imageView: ImageView, listener: ProgressLoadListener) {
        ProgressInterceptor.addProgressListener(url, object : ProgressLoadListener {
            override fun update(bytesRead: Long, contentLength: Long) {
                listener.update(bytesRead, contentLength)
            }

            override fun onException() {}
            override fun onResourceReady(resource: Drawable?) {}
        })

        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions().skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        listener.onException()
                        ProgressInterceptor.removeProgressListener(url)
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        listener.onResourceReady(resource)
                        ProgressInterceptor.removeProgressListener(url)
                        return false
                    }
                })
                .into(imageView)
    }

    override fun displayImageWithPrepareCall(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration, listener: SourceReadyListener) {
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val placeholder = if (imageLoaderConfiguration.placeHolder == 0) DEFAULT_PLACE_HOLDER else imageLoaderConfiguration.placeHolder
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error
        Glide.with(imageView.context)
                .asDrawable()
                .load(url)
                .apply(RequestOptions()
                        .placeholder(if (isShowPlaceHolder) imageView.getContext().getResources().getDrawable(placeholder) else imageView.getDrawable())
                        .error(error)
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        listener.onException()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        listener.onResourceReady(resource, resource?.intrinsicWidth, resource?.intrinsicHeight)
                        return false
                    }
                }).into(imageView)
    }

    override fun displayGifWithPrepareCall(url: String, imageView: ImageView, listener: SourceReadyListener) {
        Glide.with(imageView.context)
                .asGif()
                .load(url)
                .apply(RequestOptions()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
                        listener.onException()
                        return false
                    }

                    override fun onResourceReady(resource: GifDrawable?, model: Any?, target: Target<GifDrawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        listener.onResourceReady(resource, resource?.intrinsicWidth, resource?.intrinsicHeight)
                        return false
                    }
                })
                .into(imageView)
    }

    override fun displayImageRound(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration, roundSize: Int) {
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val placeholder = if (imageLoaderConfiguration.placeHolder == 0) DEFAULT_PLACE_HOLDER else imageLoaderConfiguration.placeHolder
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error

        Glide.with(imageView.context)
                .load(url)
                .transition(GenericTransitionOptions.with(R.anim.load_img_anim))
                .apply(RequestOptions()
                        .transform(RoundedCorners(roundSize))
                        .placeholder(if (isShowPlaceHolder) imageView.context.getResources().getDrawable(placeholder) else imageView.drawable)
                        .error(error)
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(imageView)
    }

    override fun clearImageDiskCache(context: Context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Thread(object : Runnable {
                    override fun run() {
                        Glide.get(context.getApplicationContext()).clearDiskCache()
                    }
                }).start()
            } else {
                Glide.get(context.getApplicationContext()).clearDiskCache()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun clearImageMemoryCache(context: Context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context.getApplicationContext()).clearMemory()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun trimMemory(context: Context, level: Int) {
        Glide.get(context).trimMemory(level)
    }

    override fun getCacheSize(context: Context): String {
        try {
            return FileUtils.getFormatSize(FileUtils.getFolderSize(Glide.getPhotoCacheDir(context.getApplicationContext())).toDouble())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    override fun saveImage(context: Context, url: String, savePath: String, saveFileName: String, listener: ImageSaveListener) {
        if (!FileUtils.isSDCardExsit() || url.isEmpty()) {
            listener.onSaveFail()
            return
        }
        var fromStream: InputStream? = null
        var toStream: OutputStream? = null
        try {
            val cacheFile: File? = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get()
            if (cacheFile == null || !cacheFile.exists()) {
                listener.onSaveFail()
                return
            }
            val dir = File(savePath)
            if (!dir.exists()) {
                dir.mkdir()
            }
            val file = File(dir, saveFileName + FileUtils.getPicType(cacheFile.getAbsolutePath()))

            fromStream = FileInputStream(cacheFile)
            toStream = FileOutputStream(file)
            val length = ByteArray(1024)
            var count: Int = fromStream.read(length)
            while (count > 0) {
                toStream.write(length, 0, count)
                count = fromStream.read(length)
            }
            //用广播通知相册进行更新相册
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val uri: Uri = Uri.fromFile(file)
            intent.setData(uri)
            context.sendBroadcast(intent)
            listener.onSaveSuccess()
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onSaveFail()
        } finally {
            try {
                fromStream?.close()
                toStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
                fromStream = null
                toStream = null
            }
        }
    }

    override fun displayImageNoScaleType(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val placeholder = if (imageLoaderConfiguration.placeHolder == 0) DEFAULT_PLACE_HOLDER else imageLoaderConfiguration.placeHolder
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error

        Glide.with(imageView.context)
                .load(url)
                .transition(GenericTransitionOptions.with(R.anim.load_img_anim))
                .apply(RequestOptions()
                        .placeholder(if (isShowPlaceHolder) imageView.context.getResources().getDrawable(placeholder) else imageView.drawable)
                        .error(error)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(imageView)
    }

    override fun displayImageSacleType(ctx: Context, url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val placeholder = if (imageLoaderConfiguration.placeHolder == 0) DEFAULT_PLACE_HOLDER else imageLoaderConfiguration.placeHolder
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error
        val scaleType = imageLoaderConfiguration.sacleType

        val requestOptions = RequestOptions()
                .placeholder(if (isShowPlaceHolder) ctx.resources.getDrawable(placeholder) else imageView.drawable)
                .error(if (isShowPlaceHolder) ctx.resources.getDrawable(error) else imageView.drawable)
                .diskCacheStrategy(DiskCacheStrategy.DATA)

        if (scaleType == ImageLoaderConfiguration.SACLE_CENTER_CROP) {
            requestOptions.centerCrop()
        }
        if (scaleType == ImageLoaderConfiguration.SACLE_FIT_CENTER) {
            requestOptions.fitCenter()
        }
        Glide.with(imageView.context)
                .load(url)
                .transition(GenericTransitionOptions.with(R.anim.load_img_anim))
                .apply(requestOptions)
                .into(imageView)
    }

    override fun downloadOnlyImage(ctx: Context, url: String, width: Int, height: Int) {
        //预加载图片到磁盘
        try {
            Glide.with(ctx)
                    .load(url)
                    .downloadOnly(width, height)
                    .get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun preloadImage(ctx: Context, url: String) {
        try {
            Glide.with(ctx)
                    .load(url)
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
                    .preload()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun displayImageNoScaleTypeAndPlaceHolder(url: String, imageView: ImageView, imageLoaderConfiguration: ImageLoaderConfiguration) {
        val isShowPlaceHolder: Boolean = imageLoaderConfiguration.isShowPlace
        val error = if (imageLoaderConfiguration.error == 0) DEFAULT_ERROR else imageLoaderConfiguration.error
        Glide.with(imageView.context)
                .load(url)
                .transition(GenericTransitionOptions.with(R.anim.load_img_anim))
                .apply(RequestOptions()
                        .placeholder(imageView.drawable)
                        .error(if (isShowPlaceHolder) imageView.context.resources.getDrawable(error) else imageView.drawable)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(imageView)
    }
}