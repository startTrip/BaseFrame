package com.mihua.frameproject.vlayout.bean;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/11
 */
public class HomeClassifyTitle {

    private String store_name;

    private int store_img;

    public HomeClassifyTitle(String store_name, int store_img) {
        this.store_name = store_name;
        this.store_img = store_img;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getStore_img() {
        return store_img;
    }

    public void setStore_img(int store_img) {
        this.store_img = store_img;
    }
}
