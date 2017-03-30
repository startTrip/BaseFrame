package com.mihua.code.http.volley.request;

import com.google.gson.Gson;
import com.mihua.code.http.volley.listener.IHttpListener;

import java.io.UnsupportedEncodingException;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/17
 */
public class HttpTask implements Runnable {

    public IHttpService mIHttpService;

    public <T> HttpTask(String url, T t, IHttpListener iHttpListener){

        mIHttpService = new JsonHttpService();
        mIHttpService.setUrl(url);
        mIHttpService.setHttpCallBack(iHttpListener);

        String json = new Gson().toJson(t);
        try {
            byte[] bytes = json.getBytes("UTF-8");
            mIHttpService.setParam(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        mIHttpService.execute();
    }
}
