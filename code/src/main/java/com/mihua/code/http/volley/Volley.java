package com.mihua.code.http.volley;


import com.mihua.code.http.volley.listener.IHttpListener;
import com.mihua.code.http.volley.listener.IJsonListener;
import com.mihua.code.http.volley.listener.JsonDealListener;
import com.mihua.code.http.volley.request.HttpTask;

import java.util.concurrent.FutureTask;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/17
 */
public class Volley{

    private static Volley mVolley;

    private Volley(){
    }

    public static Volley getInstance(){
        if(mVolley==null){
            synchronized (Volley.class){
                if(mVolley==null){
                    mVolley = new Volley();
                }
            }
        }
        return  mVolley;
    }

    public <T,M> void sendRequest(String url, T requestInfo, Class<M> response, IJsonListener<M> iJsonListener){

        IHttpListener iHttpListener = new JsonDealListener<>(iJsonListener,response);
        HttpTask httpTask = new HttpTask(url,requestInfo,iHttpListener);
        ThreadPoolManager.getInstance().excute(new FutureTask(httpTask,null));

    }
}
