package com.jun.commonlib.config

import android.os.Environment
import java.io.File

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2017/03/13
 * desc  : The constants of time.
</pre> *
 */
object BaseConstants {
    val MAXMEMORY: Long = (Runtime.getRuntime().maxMemory() / 1024L).toLong();
    val MAXMEMORY_CACHESIZE: Long = MAXMEMORY / 8
    val SD_PATH_PIC: String = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pic"
    val DISC_CACHESIZE: Long = 52428800
}
