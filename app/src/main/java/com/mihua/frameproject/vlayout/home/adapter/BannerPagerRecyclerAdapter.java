package com.mihua.frameproject.vlayout.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.bean.SlideBean;

import java.util.ArrayList;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/11
 */
public class BannerPagerRecyclerAdapter extends RecyclablePagerAdapter<ImageAdapter.ViewHolder>{

    ArrayList<SlideBean> mList;
    Context mContext;
    public BannerPagerRecyclerAdapter(ImageAdapter adapter, RecyclerView.RecycledViewPool pool, Context context,ArrayList<SlideBean> list) {
        super(adapter, pool);
        mList = list;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder viewHolder, int position) {

        viewHolder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.image);
        SlideBean slideBean = mList.get(position);
        if (slideBean != null) {
            String imgUrl = slideBean.getImgUrl();
            if (!TextUtils.isEmpty(imgUrl)) {
                Glide.with(mContext).load(imgUrl).into(imageView);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
