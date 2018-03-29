package com.jun.commonui.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.jun.commonui.R;

import java.util.Map;

public class ProgressBarWebView extends LinearLayout {
    private ProgressBar mProgressBar;
    private WVJBWebView mWebView;
    private int mProgressBarHight = 6;
    private int mProgressBarDrawableId = R.drawable.ui_webview_progress_bar_states;

    private boolean isShowProgressBar = true;
    private ProgressChangedListener progressChangedListener;

    public ProgressBarWebView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressBarWebView(Context context, boolean isShowProgressBar, int progressBarHight) {
        super(context);
        this.isShowProgressBar = isShowProgressBar;
        this.mProgressBarHight = progressBarHight;
        init(context, null);
    }

    public ProgressBarWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressBarWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public ProgressBarWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        if (attrs != null) {
            TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.ProgressBarWebView);
            mProgressBarHight = mTypedArray.getInteger(R.styleable.ProgressBarWebView_progressBarHight, 6);
            isShowProgressBar = mTypedArray.getBoolean(R.styleable.ProgressBarWebView_isShowProgressBar, true);
            mProgressBarDrawableId = mTypedArray.getInteger(R.styleable.ProgressBarWebView_progressBarDrawableId, R.drawable.ui_webview_progress_bar_states);
        }

        // 初始化进度条
        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        }
        mProgressBar.setVisibility(isShowProgressBar ? VISIBLE : GONE);
        mProgressBar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mProgressBarHight));
        Drawable drawable = context.getResources().getDrawable(mProgressBarDrawableId);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);

        // 初始化webview
        if (mWebView == null) {
            mWebView = new WVJBWebView(context);
        }
        addView(mWebView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressChangedListener != null)
                    progressChangedListener.onProgressChanged(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(GONE);
                } else {
                    if (mProgressBar.getVisibility() == GONE && isShowProgressBar)
                        mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public void setProgressChangedListener(ProgressChangedListener progressChangedListener) {
        this.progressChangedListener = progressChangedListener;
    }

    public ProgressBar getmProgressBar() {
        return mProgressBar;
    }

    public WVJBWebView getmWebView() {
        return mWebView;
    }

    /**
     * Loads the given URL.
     *
     * @param url the URL of the resource to load
     */
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    /**
     * Loads the given URL with the specified additional HTTP headers.
     *
     * @param url                   the URL of the resource to load
     * @param additionalHttpHeaders the additional headers to be used in the
     *                              HTTP request for this URL, specified as a map from name to
     *                              value. Note that if this map contains any of the headers
     *                              that are set by default by this WebView, such as those
     *                              controlling caching, accept types or the User-Agent, their
     *                              values may be overriden by this WebView's defaults.
     */
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        mWebView.loadUrl(url, additionalHttpHeaders);
    }

    public void loadData(String data, String mimeType, String encoding) {
        mWebView.loadData(data, mimeType, encoding);
    }

    public void load(String baseUrl, String data,
                     String mimeType, String encoding, String historyUrl) {
        mWebView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    public void setWebViewClient(WebViewClient client) {
        mWebView.setWebViewClient(client);
    }

    public void setWebChromeClient(WebChromeClient chromeClient) {
        mWebView.setWebChromeClient(chromeClient);
    }

    /**
     * 注册本地java方法，以供js端调用
     *
     * @param handlerName 方法名称
     * @param handler     回调接口
     */
    public void registerHandler(String handlerName, WVJBWebView.WVJBHandler handler) {
        if (handler == null) return;
        mWebView.registerHandler(handlerName, handler);
    }

    /**
     * 调用js端已经注册好的方法
     *
     * @param handlerName      方法名称
     * @param data             本地端传递给js端的参数，json字符串
     * @param responseCallback 回调接口
     */
    public void callHandler(String handlerName, Object data, WVJBWebView.WVJBResponseCallback<Object> responseCallback) {
        if (responseCallback == null) return;
        mWebView.callHandler(handlerName, data, responseCallback);
    }

    public interface ProgressChangedListener {
        void onProgressChanged(int progress);
    }
}