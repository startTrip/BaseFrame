package com.mihua.code.http;

import com.mihua.code.http.okhttp.IOkHttpRequestApi;
import com.mihua.code.http.okhttp.OkHttpRequest;
import com.mihua.code.http.retrofit.RetrofitManger;
import com.mihua.code.http.volley.Volley;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/15
 */
public class HttpRequestFactory{

    // OkHttp请求
    public static IOkHttpRequestApi getOkHttpRequest(){

        return OkHttpRequest.getInstance();

    }

    // retrofitManager
    public static RetrofitManger getRetrofitRequest(){

        RetrofitManger retrofitManger = RetrofitManger.getInstance();
        return retrofitManger;
    }

    public static Volley getVolleyRequest(){
        Volley volley = Volley.getInstance();
        return volley;
    }
}
