package com.mihua.code.http.okhttp;


import com.mihua.code.http.okhttp.listener.DisposeDataListener;
import com.mihua.code.http.okhttp.request.RequestParams;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/3/15
 */
public interface IOkHttpRequestApi {

        void get(RequestParams request, String url, DisposeDataListener disposeDataListener);

        void get(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz);

        void post(RequestParams request, String url, DisposeDataListener disposeDataListener);

        void post(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz);
}
