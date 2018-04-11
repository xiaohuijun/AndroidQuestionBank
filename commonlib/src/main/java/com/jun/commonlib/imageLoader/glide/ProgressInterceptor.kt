package com.jun.commonlib.imageLoader.glide

import com.jun.commonlib.imageLoader.Listener.ProgressLoadListener
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.ref.WeakReference


class ProgressInterceptor :Interceptor{

    companion object {
         val listenersMap = mutableMapOf<String, WeakReference<ProgressLoadListener>>()

        /**
         * 使用URL做key 保存在map中
         * @param key
         * @param progressListener
         */
        fun addProgressListener(key: String, progressListener: ProgressLoadListener) {
            if (key.isEmpty()) return;
            if (!listenersMap.containsKey(key)) {
                listenersMap.put(key, WeakReference<ProgressLoadListener>(progressListener));
            }
        }

        fun removeProgressListener(key: String) {
            if (key.isEmpty() || !listenersMap.containsKey(key)) return;
            listenersMap.remove(key);
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        val response = chain.proceed(request);
        val url = request.url().toString();
        val body = response.body();
        val newResponse = response.newBuilder().body(ProgressResponseBody(url, body!!)).build();
        return newResponse;
    }
}