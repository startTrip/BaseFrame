package com.mihua.code.http.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/1/12
 */

/**
 * 管理整个请求，支持 Okhttp 设置拦截器，
 得到一个模块的网络请求接口的实现类
 */
public class RetrofitManger {

    /*
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 20;
    private static OkHttpClient sOkHttpClient = null;

    private static RetrofitManger mInstance;

    private RetrofitManger(){
        initOkhttp();

    }

    public static RetrofitManger getInstance(){
        if(mInstance == null){
            synchronized (RetrofitManger.class){
                if(mInstance==null){
                    mInstance =  new RetrofitManger();
                }
            }
        }
        return mInstance;
    }

    // 初始化 OkHttpClient
    private void initOkhttp() {
        if(sOkHttpClient ==null){
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            // 缓存拦截器

            // 设置超时
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

//            // 设置缓存
//            builder.cache(cache);
            // 允许被重定向
            builder.followRedirects(true);
            // 错误重连
            builder.retryOnConnectionFailure(true);

            builder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();

//                    HttpUrl.Builder authorizedUrlBuilder = request.url()
//                            .newBuilder()
//                            //添加统一参数 如手机唯一标识符,token等
//                            .addQueryParameter("key1","value1")
//                            .addQueryParameter("key2", "value2");
//
                    Request newRequest = request.newBuilder()
                            // TODO: 对所有请求添加请求头
//                            .header("header1", "value").addHeader("type", "type1")
                            .method(request.method(), request.body())
//                            .url(authorizedUrlBuilder.build())
                            .build();

                    return  chain.proceed(newRequest);
                }
            });
            sOkHttpClient = builder.build();
        }
    }

    // 由 retrofit 得到一个网络请求接口的实现类
    public <T> T getApiService(String baseUrl, Class<T> clz) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

}
