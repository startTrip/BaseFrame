package com.mihua.code.http.volley.listener;

/**
 * Project: TestProject
 * Author: wm
 * Data:   2017/3/16
 */
public interface IJsonListener<M> {

    void onSuccess(M response);

    void onError();

}
