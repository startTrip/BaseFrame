package com.mihua.frameproject.vlayout.home;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mihua.code.base.mvp.RxPresenter;
import com.mihua.code.http.HttpRequestFactory;
import com.mihua.frameproject.vlayout.bean.HomeClassifyTitle;
import com.mihua.frameproject.vlayout.bean.SlideBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/3/31
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter{

    private Gson mGson;

    public HomePresenter() {

        mGson = new Gson();
    }

    @Override
    public void getHomeData() {
        String baseUrl = "http://shop.trqq.com/mobile/";

        Subscription subscribe = HttpRequestFactory.getRetrofitRequest().getApiService(baseUrl, HomeApi.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        String string = null;
                        try {
                            string = responseBody.string().toString();
                            // 解析json数据
                            parseJsonData(string);

                            mView.notifyData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("String",throwable.getMessage().toString());
                    }
                });
        addSubscribe(subscribe);
    }

    // 解析数据
    private void parseJsonData(String s) {
        if (s != null) {
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(s);
                JSONObject data = jsonObject.optJSONObject("datas");
                // 解析 Banner 图数据
                parseBannerData(data);
//                // 解析分类数据
//                parseClassifyData(data);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    // 解析分类的数据
    private void parseClassifyData(JSONObject data) {

        JSONObject goodslist_info = data.optJSONObject("goods_list_info");
        String classList = goodslist_info.optString("top_goods_class_list");
        List<HomeClassifyTitle> homeClassifyTitles = mGson.fromJson(classList, new TypeToken<List<HomeClassifyTitle>>(){}.getType());
//        if (homeClassifyTitles!=null) {
//            mView.hasClassify(true);
//            mView.setClassifyData(homeClassifyTitles);
//        }else {
//            mView.hasClassify(false);
//        }
    }

    // 解析banner 图的数据
    private void parseBannerData(JSONObject data) {

        ArrayList<SlideBean> list = new ArrayList<>();
        JSONObject goodslist_info = data
                .optJSONObject("goods_list_info");
        JSONObject goodsstore = goodslist_info
                .optJSONObject("goods_store");

        JSONObject mb_sliders = goodsstore
                .optJSONObject("mb_sliders");
        Iterator<String> keys = mb_sliders.keys();
        while (keys.hasNext()) {
            String string = mb_sliders.optString(keys.next());
            SlideBean slideBean = mGson.fromJson(string, SlideBean.class);
            list.add(slideBean);
        }
        if(list.isEmpty()){
            mView.hasBanner(false);
        }else {
            mView.hasBanner(true);
            mView.setBannerData(list);
        }
    }
}
