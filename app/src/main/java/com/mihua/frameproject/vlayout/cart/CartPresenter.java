package com.mihua.frameproject.vlayout.cart;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mihua.code.base.mvp.BasePresenterIml;
import com.mihua.frameproject.vlayout.cart.bean.ShoppingCartBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/13
 */
public class CartPresenter extends BasePresenterIml<CartContract.View> implements CartContract.Presenter{


    @Override
    public void getCartData(Context context) {

        BufferedReader bufferedReader = null ;
        InputStream open = null;
        StringBuilder builder = new StringBuilder();
        try {
            open = context.getResources().getAssets().open("cart_data.json");

            bufferedReader = new BufferedReader(new InputStreamReader(open));
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                builder.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (open != null) {
                    open.close();
                }
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String string = builder.toString();
        try {
            JSONObject jsonObject = new JSONObject(string);
            JSONArray jsonArray= jsonObject.optJSONArray("pageEntity");
            String string1 = String.valueOf(jsonArray);
            if (!TextUtils.isEmpty(string1)) {
                List<ShoppingCartBean> list = new Gson().fromJson(string1, new TypeToken<List<ShoppingCartBean>>(){}.getType());
                mView.setCartData(list);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
