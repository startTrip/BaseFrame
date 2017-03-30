package com.mihua.code.http.okhttp;


import com.mihua.code.http.okhttp.listener.DisposeDataHandle;
import com.mihua.code.http.okhttp.listener.DisposeDataListener;
import com.mihua.code.http.okhttp.request.CommonRequest;
import com.mihua.code.http.okhttp.request.RequestParams;

import okhttp3.Request;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/15
 */
public class OkHttpRequest implements IOkHttpRequestApi {

    private static OkHttpRequest sHttpRequest;

    private OkHttpRequest() {

    }
    public static synchronized OkHttpRequest getInstance(){
        if(sHttpRequest==null){
            sHttpRequest = new OkHttpRequest();
        }
        return sHttpRequest;
    }

    @Override
    public void get(RequestParams request, String url, DisposeDataListener disposeDataListener) {
        Request okHttpRequest = CommonRequest.createGetRequest(url, request);
        CommonOkHttpClient.get(okHttpRequest, new DisposeDataHandle(disposeDataListener));
    }

    @Override
    public void get(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz) {
        Request okHttpRequest = CommonRequest.createGetRequest(url, request);
        CommonOkHttpClient.get(okHttpRequest, new DisposeDataHandle(disposeDataListener,clazz));
    }

    @Override
    public void post(RequestParams request, String url, DisposeDataListener disposeDataListener) {
        Request okHttpRequest = CommonRequest.createPostRequest(url, request);
        CommonOkHttpClient.post(okHttpRequest, new DisposeDataHandle(disposeDataListener));
    }

    @Override
    public void post(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz) {
        Request okHttpRequest = CommonRequest.createPostRequest(url, request);
        CommonOkHttpClient.post(okHttpRequest, new DisposeDataHandle(disposeDataListener,clazz));
    }


}
