package com.mihua.code.http.okhttp;


import com.mihua.code.http.okhttp.listener.DisposeDataHandle;
import com.mihua.code.http.okhttp.listener.DisposeDataListener;
import com.mihua.code.http.okhttp.request.CommonRequest;
import com.mihua.code.http.okhttp.request.RequestParams;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/15
 */


public class HttpRequest implements IRequestApi {

    private static HttpRequest sHttpHttpRequest;

    private HttpRequest() {

    }
    public static synchronized HttpRequest getInstance(){
        if(sHttpHttpRequest ==null){
            sHttpHttpRequest = new HttpRequest();
        }
        return sHttpHttpRequest;
    }

    @Override
    public void getOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener) {

        okhttp3.Request okHttpRequest = CommonRequest.createGetRequest(url, request);
        CommonOkHttpClient.get(okHttpRequest, new DisposeDataHandle(disposeDataListener));
    }

    @Override
    public void getOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz) {

        okhttp3.Request okHttpRequest = CommonRequest.createGetRequest(url, request);
        CommonOkHttpClient.get(okHttpRequest, new DisposeDataHandle(disposeDataListener,clazz));
    }

    @Override
    public void postOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener) {
        okhttp3.Request okHttpRequest = CommonRequest.createPostRequest(url, request);
        CommonOkHttpClient.post(okHttpRequest, new DisposeDataHandle(disposeDataListener));
    }

    @Override
    public void postOkHttp(RequestParams request, String url, DisposeDataListener disposeDataListener, Class<?> clazz) {
        okhttp3.Request okHttpRequest = CommonRequest.createPostRequest(url, request);
        CommonOkHttpClient.post(okHttpRequest, new DisposeDataHandle(disposeDataListener,clazz));
    }


}
