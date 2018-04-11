@file:Suppress("NOTHING_TO_INLINE")

package com.jun.commonlib.utils

import android.content.Context
import android.content.Intent
import android.text.TextUtils

object ActivityUtils {
    fun isActivityExists(context: Context, pkg: String, cls: String): Boolean {
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls)) return false
        val intent = Intent()
        intent.setClassName(pkg, cls);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size == 0);
    }
}