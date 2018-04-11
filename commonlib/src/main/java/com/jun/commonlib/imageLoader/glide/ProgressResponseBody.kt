package com.jun.commonlib.imageLoader.glide

import com.jun.commonlib.imageLoader.Listener.ProgressLoadListener
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

class ProgressResponseBody : ResponseBody {
    private lateinit var imageUrl: String
    private lateinit var responseBody: ResponseBody
    private var progressListener: ProgressLoadListener?
    private lateinit var bufferedSource: BufferedSource;

    constructor(url: String, responseBody: ResponseBody, progressListener: ProgressLoadListener?) {
        this.imageUrl = url;
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType();
    }

    override fun contentLength(): Long {
        return responseBody.contentLength();
    }

    override fun source(): BufferedSource {

        bufferedSource = Okio.buffer(source(responseBody.source()));
        return bufferedSource;
    }

    fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            var totalBytesRead: Long = 0
            override fun read(sink: Buffer?, byteCount: Long): Long {
                val bytesRead: Long = super.read(sink, byteCount)
                totalBytesRead = totalBytesRead + if (bytesRead == -1L) 0 else bytesRead
                progressListener?.update(totalBytesRead, contentLength())
                return bytesRead;
            }
        };
    }
}