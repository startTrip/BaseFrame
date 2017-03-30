package com.mihua.code.http.volley.request;


import com.mihua.code.http.volley.listener.IHttpListener;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/16
 */
public interface IHttpService {

    void setUrl(String url);

    void execute();

    void setHttpCallBack(IHttpListener iHttpListener);

    void setParam(byte[] b);
}
