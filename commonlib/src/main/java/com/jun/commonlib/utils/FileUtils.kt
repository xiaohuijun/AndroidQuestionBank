package com.jun.commonlib.utils

import android.graphics.BitmapFactory
import android.os.Environment
import android.text.TextUtils
import java.io.File
import java.math.BigDecimal

object FileUtils {
    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    fun getFolderSize(file: File?): Long {
        var size: Long = 0
        try {
            val fileList = file?.listFiles()
            for (aFileList in fileList!!) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList)
                } else {
                    size = size + aFileList.length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return size
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    fun getFormatSize(size: Double): String {
        val kiloByte: Double = size / 1024
        if (kiloByte < 1) {
            return "${size}Byte"
        }

        val megaByte: Double = kiloByte / 1024
        if (megaByte < 1) {
            val result1 = BigDecimal(kiloByte.toString())
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
        }

        val gigaByte: Double = megaByte / 1024
        if (gigaByte < 1) {
            val result2 = BigDecimal(megaByte.toString())
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
        }

        val teraBytes: Double = gigaByte / 1024
        if (teraBytes < 1) {
            val result3 = BigDecimal(gigaByte.toString())
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
        }
        val result4 = BigDecimal(teraBytes)

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }

    fun getPicType(pathName: String): String {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(pathName, options)
        var type = options.outMimeType
        if (!TextUtils.isEmpty(type)) {
            try {
                type = type.substring(6, type.length)
                if ("gif".equals(type)) {
                    return ".gif"
                }
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        }
        return ".jpg"
    }

    fun isSDCardExsit(): Boolean {
        val state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    fun getSDCardPath(): String {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}