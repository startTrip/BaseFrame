package com.mihua.code.http.volley.listener;

import java.io.InputStream;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/16
 */
public interface IHttpListener {

    void onSuccess(InputStream inputStream);

    void onFailure();

}
