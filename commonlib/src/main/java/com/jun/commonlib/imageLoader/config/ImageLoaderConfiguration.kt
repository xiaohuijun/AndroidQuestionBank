package com.jun.commonlib.imageLoader.config

import com.jun.commonlib.R

class ImageLoaderConfiguration {
    var placeHolder: Int = 0 //设置资源加载过程中的占位
    var error: Int = 0 //设置load失败时显示
    var isShowPlace: Boolean = false
    var isBlur: Boolean = false
    /**
     * 图片缩放比例,默认“1”。
     */
    var blurScale = 1
    /**
     * 设置模糊度(在0.0到25.0之间)，默认”25";
     */
    var blurProgress: Int = 0

    var resizeWidth = -1
    var resizeHeight = -1

    var sacleType = SACLE_CENTER_CROP//缩放模式

    companion object {
        val SACLE_CENTER_CROP = 1001
        val SACLE_FIT_CENTER = 1002
    }

    constructor(builder: Builder) {
        this.placeHolder = builder.placeHolder
        this.error = builder.error
        this.isShowPlace = builder.isShowPlace
        this.isBlur = builder.isBlur
        this.blurScale = builder.blurScale
        this.blurProgress = builder.blurProgress
        this.sacleType = builder.sacleType
    }


    inner class Builder {
        var placeHolder: Int = R.drawable.ic_default_pic_bg
        var error: Int = R.drawable.ic_default_pic_bg
        var isShowPlace: Boolean = false

        var isBlur: Boolean = false
        var blurScale = 1
        var blurProgress: Int = 0

        var sacleType = SACLE_CENTER_CROP

        var resizeWidth = -1
        var resizeHeight = -1

        fun placeHolder(placeHolder: Int): Builder {
            this.placeHolder = placeHolder
            return this
        }

        fun setError(error: Int): Builder {
            this.error = error
            return this
        }

        fun setShowPlace(showPlace: Boolean): Builder {
            isShowPlace = showPlace
            return this
        }

        fun setBlur(blur: Boolean): Builder {
            isBlur = blur
            return this
        }

        fun setBlurScale(blurScale: Int): Builder {
            this.blurScale = blurScale
            return this
        }


        fun setSacleType(sacleType: Int): Builder {
            this.sacleType = sacleType
            return this
        }

        fun setBlurProgress(blurProgress: Int): Builder {
            this.blurProgress = blurProgress
            return this
        }

        fun setResizeWidth(resizeWidth: Int): Builder {
            this.resizeWidth = resizeWidth
            return this
        }

        fun setResizeHeight(resizeHeight: Int): Builder {
            this.resizeHeight = resizeHeight
            return this
        }

        fun build(): ImageLoaderConfiguration {
            return ImageLoaderConfiguration(this)
        }
    }

    override fun toString(): String {
        return "ImageLoaderConfiguration(placeHolder=$placeHolder, error=$error, isShowPlace=$isShowPlace, isBlur=$isBlur, blurScale=$blurScale, blurProgress=$blurProgress, resizeWidth=$resizeWidth, resizeHeight=$resizeHeight, sacleType=$sacleType)"
    }
}