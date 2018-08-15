package com.mihua.frameproject.vlayout.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.bean.SlideBean;
import com.tmall.ultraviewpager.IUltraIndicatorBuilder;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/5
 */
public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.ViewHolder>{

    ArrayList<SlideBean> mList;
    Context mContext;
    RecyclerView.RecycledViewPool mRecyclerViewPool;
    LayoutHelper mLayoutHelper;
    int mCount;
    public BannerAdapter(Context context, LayoutHelper layoutHelper, int count, RecyclerView.RecycledViewPool pool, ArrayList<SlideBean> slideBeen) {
        mList = slideBeen;
        mContext = context;
        mLayoutHelper = layoutHelper;
        mRecyclerViewPool = pool;
        mCount = count;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_viewpager, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(holder.itemView instanceof UltraViewPager){

            setViewPager(holder,position);

        }
    }

    // 设置 UltraViewPager
    private void setViewPager(ViewHolder holder, int position) {

//        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500));
        UltraViewPager ultraViewPager = (UltraViewPager) holder.itemView.findViewById(R.id.view_pager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

//            ((ViewPager) holder.itemView).setAdapter(new BannerPagerRecyclerAdapter(new ImageAdapter(mContext,mLayoutHelper,1),mRecyclerViewPool,mContext,mList));
        ultraViewPager.setAdapter(new BannerPagerAdapter(mList,mContext));
        ultraViewPager.initIndicator();
        IUltraIndicatorBuilder indicator = ultraViewPager.getIndicator();
        indicator.setFocusColor(Color.RED).setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, mContext.getResources().getDisplayMetrics()));
        indicator.setOrientation(UltraViewPager.Orientation.HORIZONTAL);
        indicator.setGravity(Gravity.BOTTOM |Gravity.CENTER);
        indicator.setMargin(8,6,6,20);
        indicator.build();

        ultraViewPager.setInfiniteLoop(true);
        ultraViewPager.setAutoScroll(4000);
    }

    @Override
    public int getItemCount(){
        return mCount;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
