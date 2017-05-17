package com.mihua.frameproject.vlayout.home;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/5
 */
public interface HomeApi {

    @GET("index.php?act=wnj_show_store&op=index&store_id=126")
    Observable<ResponseBody> getData();

}
