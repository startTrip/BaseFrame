package com.mihua.frameproject.vlayout.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.bean.HomeClassifyTitle;

import java.util.ArrayList;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/11
 */
public class ClassifyAdapter extends DelegateAdapter.Adapter<ClassifyAdapter.ViewHolder> {

    ArrayList<HomeClassifyTitle> mList;
    Context mContext;
    LayoutHelper mLayoutHelper;

    public ClassifyAdapter(Context context, LayoutHelper layoutHelper,ArrayList<HomeClassifyTitle> slideBeen) {
        mList = slideBeen;
        mContext = context;
        mLayoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.classify_img, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        HomeClassifyTitle homeClassifyTitle = mList.get(position);
        int img_path = homeClassifyTitle.getStore_img();
        String store_name = homeClassifyTitle.getStore_name();
        holder.store_name.setText(store_name);
        holder.store_img.setImageResource(img_path);
//
//        holder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position","你点击了分类"+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView store_img;
        TextView store_name;

        public ViewHolder(View itemView) {
            super(itemView);
            store_img = (ImageView) itemView.findViewById(R.id.store_img);
            store_name = (TextView) itemView.findViewById(R.id.store_name);
        }
    }

}
