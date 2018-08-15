package com.mihua.code.http;

import com.mihua.code.http.okhttp.HttpRequest;
import com.mihua.code.http.okhttp.IRequestApi;
import com.mihua.code.http.retrofit.RetrofitManger;
import com.mihua.code.http.volley.Volley;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/15
 */
public class HttpRequestFactory{

    // OkHttp请求
    public static IRequestApi getOkHttpRequest(){

        return HttpRequest.getInstance();

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
