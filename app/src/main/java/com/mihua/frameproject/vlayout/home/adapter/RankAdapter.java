package com.mihua.frameproject.vlayout.home.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.mihua.frameproject.R;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/11
 */
public class RankAdapter extends DelegateAdapter.Adapter<RankAdapter.ViewHolder> {

    public Context mContext;
    public int mCount;
    public LayoutHelper mLayoutHelper;
    public String mTitle;

    public RankAdapter(Context context, int count, LayoutHelper layoutHelper,String title) {
        mContext = context;
        mCount = count;
        mLayoutHelper = layoutHelper;
        mTitle = title;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_rank_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));
        TextView textView = (TextView) holder.itemView.findViewById(R.id.rank_text);
        textView.setText(mTitle);
        RecyclerView recyclerView = (RecyclerView) holder.itemView.findViewById(R.id.rank_recycler);
        LinearLayoutManager virtualLayoutManager = new LinearLayoutManager(mContext);
        virtualLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(virtualLayoutManager);
        if(TextUtils.equals("排行榜",mTitle)){
            recyclerView.setAdapter(new RankImageAdapter(mContext,15));
        }else {
            recyclerView.setAdapter(new RankImageAdapter(mContext,10));
        }

    }


    @Override
    public int getItemCount() {
        return mCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
