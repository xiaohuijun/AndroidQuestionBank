package com.jun.commonlib.imageLoader.glide

import com.jun.commonlib.imageLoader.Listener.ProgressLoadListener
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.lang.ref.WeakReference


object ProgressInterceptor {
    private var listenersMap = mutableMapOf<String, WeakReference<ProgressLoadListener>>()
    private lateinit var okHttpClient: OkHttpClient;

    fun getOkHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val request = chain.request();
                            val response = chain.proceed(request);
                            return response.newBuilder()
                                    .body(ProgressResponseBody(request.url().toString(), response.body()!!, listenersMap.get(request.url().toString())?.get()))
                                    .build()
                        }
                    })
                    .build()
        }
        return okHttpClient;
    }

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