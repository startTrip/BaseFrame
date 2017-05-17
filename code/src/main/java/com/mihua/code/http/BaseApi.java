package com.mihua.code.http;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/10
 */
public interface BaseApi {


    // get请求  url 表示请求路径
    @GET("{url}")
    Observable<RequestBody> get(
            @Path("url") String url);

    // get请求  url 表示请求路径
    @GET("{url}")
    <T> Observable<T> get(
            @Path("url") String url,Class<T> response);


    // post请求  url 表示请求类型
    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> post(
            @Path("url") String url,
            @FieldMap Map<String, String> maps);


    /**
     *  post 请求，返回 解析后的class
     * @param url     post 请求的 url
     * @param maps    post 请求参数
     * @param response  返回结果class
     * @param <T>  泛型
     * @return   observable
     */
    @FormUrlEncoded
    @POST("{url}")
    <T> Observable<T> post(
            @Path("url") String url,
            @FieldMap Map<String, String> maps,
            Class<T> response);


    /**
     *  用于上传 json 数据,返回 responseBody
     * @param url   url
     * @param json  请求体 RequestBody
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> uploadJson(
            @Path("url") String url,
            @Body RequestBody json
    );


    /**
     * 上传 图片
     * @param url 路径
     * @param requestBody 请求体
     * @return
     */
    @POST()
    Observable<ResponseBody> uploadImg(
            @Url String url,
            @Body RequestBody requestBody
    );


    /**
     *  用于上传 json 数据,返回 解析后的class
     * @param url   url
     * @param json  请求体 RequestBody
     * @param response  返回结果
     * @return     observable
     */
    @POST("{url}")
    <T> Observable<T> uploadJson(
            @Path("url") String url,
            @Body RequestBody json,Class<T> response
    );


    /**
     *  上传单个文件，无文件描述，返回responseBody
     * @param url  url
     * @param file  MultiPartBody.Part
     * @return
     */
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFile(
            @Url String url,
            @Part MultipartBody.Part file);

    /**
     *  上传单个文件，无文件描述，返回responseBody
     * @param url  url
     * @param file  MultiPartBody.Part
     * @return
     */
    @Multipart
    @POST()
    <T> Observable<T> uploadFile(
            @Url String url,
            @Part MultipartBody.Part file,
            Class<T> response
    );


    /**
     *  上传单个文件 ，包含文件描述，无响应类
     * @param url  url
     * @param description 文件描述 RequestBody
     * @param file  文件 有多个部分组成
     * @return
     */
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFile(
            @Url String url,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);


    /**
     *  上传单个文件，有响应类
     * @param url  url
     * @param description 文件描述 RequestBody
     * @param file  文件 有多个部分组成
     * @param response  响应类
     * @return
     */
    @Multipart
    @POST()
    <T> Observable<T> uploadFile(
            @Url String url,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file,
            Class<T> response
    );

    /**
     *  上传多个文件，无响应类
     * @param url  url
     * @param map  多个文件的集合
     * @return
     */
    @Multipart
    @POST()
     Observable<ResponseBody> uploadMoreFile(
            @Url String url,
            @PartMap Map<String,RequestBody> map
    );

    /**
     *  上传多个文件，有响应类
     * @param url  url
     * @param map  多个文件的集合
     * @param response  响应类
     * @return
     */
    @Multipart
    @POST()
    <T> Observable<T> uploadMoreFile(
            @Url String url,
            @PartMap Map<String,RequestBody> map,
            Class<T> response
    );

}
