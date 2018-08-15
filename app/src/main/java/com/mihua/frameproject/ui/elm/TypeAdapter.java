package com.mihua.frameproject.ui.elm;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mihua.frameproject.R;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    public int selectTypeId;
    public ShoppingCartActivity activity;
    public ArrayList<GoodsItem> dataList;

    public TypeAdapter(ShoppingCartActivity activity, ArrayList<GoodsItem> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsItem item = dataList.get(position);

        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if(dataList==null){
            return 0;
        }
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCount,type;
        private GoodsItem item;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            type = (TextView) itemView.findViewById(R.id.type);
            itemView.setOnClickListener(this);
        }

        public void bindData(GoodsItem item){
            this.item = item;
            type.setText(item.typeName);
            int count = activity.getSelectedGroupCountByTypeId(item.typeId);
            tvCount.setText(String.valueOf(count));
            if(count<1){
                tvCount.setVisibility(View.GONE);
            }else{
                tvCount.setVisibility(View.VISIBLE);
            }
            if(item.typeId==selectTypeId){
                itemView.setBackgroundColor(Color.WHITE);
            }else{
                itemView.setBackgroundColor(Color.TRANSPARENT);
            }

        }

        @Override
        public void onClick(View v) {
            activity.onTypeClicked(item.typeId);
        }
    }
}
