package com.mihua.code.http.okhttp;


import com.mihua.code.http.okhttp.listener.DisposeDataListener;
import com.mihua.code.http.okhttp.request.RequestParams;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/3/15
 */
public interface IRequestApi {


//        void getRetrofit();
//
//        void postRetrofit();

        void getOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener);

        void getOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz);

        void postOkHttp (RequestParams request, String url, DisposeDataListener disposeDataListener);

        void postOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz);

}
