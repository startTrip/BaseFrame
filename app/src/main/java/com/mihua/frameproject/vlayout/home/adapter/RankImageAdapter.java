package com.mihua.frameproject.vlayout.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.mihua.frameproject.R;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/12
 */
public class RankImageAdapter extends RecyclerView.Adapter<RankImageAdapter.ViewHolder> {

    public Context mContext;
    public int mCount;

    public RankImageAdapter(Context context,int count) {

        mContext = context;
        mCount = count;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.image,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mCount<15){
            holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT));
        }else {

            holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
