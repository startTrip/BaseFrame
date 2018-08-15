package com.mihua.code.http.volley.listener;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/16
 */
public class JsonDealListener<M> implements IHttpListener {

    private Handler mDeliveryHandler;
    private IJsonListener mIJsonListener;
    private Class<M> responseClass;

    public JsonDealListener(IJsonListener IJsonListener,Class<M> tClass) {
        mIJsonListener = IJsonListener;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
        responseClass = tClass;
    }

    @Override
    public void onSuccess(InputStream inputStream) {

        final String json = getContent(inputStream);

        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mIJsonListener != null) {
                        if(responseClass!=null){
                            final M response = new Gson().fromJson(json, responseClass);
                        mIJsonListener.onSuccess(response);
                    }else {
                        mIJsonListener.onSuccess(responseClass);
                    }
                }
            }
        });
    }

    // 通过字节流去得到字符串
    private String getContent(InputStream inputStream) {

        BufferedReader bufferedReader =null;
        String line = null;
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
            return  stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error",e.getMessage());
        }finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onFailure() {

    }
}

