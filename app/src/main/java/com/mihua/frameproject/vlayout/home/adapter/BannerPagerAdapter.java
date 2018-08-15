package com.mihua.frameproject.vlayout.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mihua.frameproject.vlayout.bean.SlideBean;

import java.util.ArrayList;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/5
 */

public class BannerPagerAdapter extends PagerAdapter {

    ArrayList<SlideBean> mList;
    Context mContext;

    public BannerPagerAdapter(ArrayList<SlideBean> list, Context context) {
        mList= list;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        SlideBean slideBean = mList.get(position);
        if (slideBean != null) {
            String imgUrl = slideBean.getImgUrl();
            if (!TextUtils.isEmpty(imgUrl)) {
                Glide.with(mContext).load(imgUrl).centerCrop().into(imageView);
            }
        }
        container.addView(imageView);
        return  imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
