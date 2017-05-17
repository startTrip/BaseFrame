package com.mihua.frameproject.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.mihua.code.base.mvp.RxPresenter;
import com.mihua.code.http.BaseApi;
import com.mihua.code.http.HttpRequestFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/10
 */
public class RequestPresenter extends RxPresenter<RequestContact.View> implements RequestContact.Presenter {

    public static final String REQUEST_BASE_URL ="http://192.168.6.47:8080/HttpRequest/";


    @Override
    public void showGet() {

    }

    // Post请求
    @Override
    public void showPost() {

        HashMap<String,String> map = new HashMap<>();

        map.put("username","mihua");
        map.put("password","123456");
        Subscription subscribe = HttpRequestFactory.getRetrofitRequest().getApiService(REQUEST_BASE_URL,BaseApi.class)
                .post("login",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.d("response",string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        String string = throwable.getMessage().toString();

                    }
                });
        addSubscribe(subscribe);
    }

    // Json 数据上传
    public void showJsonUpload() {

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("act","上传json");
        hashMap.put("json","测试");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json,charset=utf-8"),new Gson().toJson(hashMap));
        Subscription subscribe =  HttpRequestFactory.getRetrofitRequest().getApiService(REQUEST_BASE_URL,BaseApi.class)
                .uploadJson("uploadJson", requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {

                    }
                });
        addSubscribe(subscribe);
    }

    // 使用retrofit 图片上传
    @Override
    public void showImgUpload() {

        // 文件的路径
        String mPath = Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"test2.jpg";
        String url = "uploadImg";
        File file = new File(mPath);
        Log.d("response",mPath);

        if(!file.exists()){
            Log.d("response","file is not exists");
            return;
        }

        Bitmap bitmap = BitmapFactory.decodeFile(mPath);
        int byteCount = bitmap.getByteCount();
        Log.d("response",byteCount+"");

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);

        Subscription subscribe = HttpRequestFactory.getRetrofitRequest().getApiService(REQUEST_BASE_URL,BaseApi.class)
                .uploadImg(url, requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.d("response",string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        String string = throwable.toString();
                        Log.d("response",string);
                    }
                });
        addSubscribe(subscribe);
    }

    // 使用retrofit 实现文件上传
    @Override
    public void showFileUpload() {
        // 文件的路径  360MobileSafe_7.7.0.1033.apk
        String mPath = Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"360MobileSafe_7.7.0.1033.apk";
        String url = "uploadFile";

        File file = new File(mPath);

        if (!file.exists()) {
            Log.d("response","file is not exists!");
            return;
        }

        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part part = MultipartBody.Part.createFormData("mPhoto",file.getName(),requestBody);

        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        Subscription subscribe =  HttpRequestFactory.getRetrofitRequest().getApiService(REQUEST_BASE_URL,BaseApi.class)
                .uploadFile(url,description, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        mView.hideProgressDialog();
                        String string = null;
                        try {
                            string = responseBody.string().toString();
                            Log.d("response",string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hideProgressDialog();
                        throwable.printStackTrace();
                        String string = throwable.toString();
                        Log.d("response",string);
                    }
                });
        addSubscribe(subscribe);
    }

}
