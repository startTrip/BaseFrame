package com.mihua.frameproject.vlayout.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.cart.bean.ShoppingCartBean;
import com.mihua.frameproject.vlayout.cart.util.ShoppingCartUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: FrameProject
 * Author: wm
 * Data:   2017/4/13
 */
public class CartExpandableListAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ArrayList<ShoppingCartBean> mList;

    public CartExpandableListAdapter(Context context, ArrayList<ShoppingCartBean> list) {

        mContext = context;
        if (list != null) {
            mList = new ArrayList<>();
        }else {
            mList = list;
        }
    }

    // 得到组的数量
    @Override
    public int getGroupCount() {
        return mList.size();
    }

    // 得到组中元素的数量
    @Override
    public int getChildrenCount(int groupPosition) {
        if(mList.size()==0){
            return 0;
        }
        ArrayList<ShoppingCartBean.Goods> goods = mList.get(groupPosition).getGoods();
        return goods ==null?0:goods.size();
    }

    // 得到组元素
    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    // 得到组中的元素
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getGoods().get(childPosition);
    }

    // 得到 组id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    // 得到组中元素id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        StoreViewHolder storeViewHolder;
        if (convertView == null) {
            storeViewHolder = new StoreViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_store_item, parent, false);
            storeViewHolder.ivCheckStore = (ImageView) convertView.findViewById(R.id.ivCheckStore);
            storeViewHolder.tvStore = (TextView) convertView.findViewById(R.id.tvShopName);
            storeViewHolder.tvEdit = (TextView) convertView.findViewById(R.id.tvEdit);
            convertView.setTag(storeViewHolder);
        }else {
            storeViewHolder = (StoreViewHolder) convertView.getTag();
        }
        // 设置店铺名
        storeViewHolder.tvStore.setText(mList.get(groupPosition).getMerchantName());
        // 设置选中状态
        ShoppingCartUtils.setCheckImage(mList.get(groupPosition).isGroupSelected(),storeViewHolder.ivCheckStore);
        // 判断是否是编辑状态
        boolean isEditing = mList.get(groupPosition).isEditing();
        if (isEditing) {
            storeViewHolder.tvEdit.setText("完成");
        } else {
            storeViewHolder.tvEdit.setText("编辑");
        }
        // 设置Tag 判断点击的是哪一个
        storeViewHolder.ivCheckStore.setTag(groupPosition);
        storeViewHolder.tvEdit.setTag(groupPosition);
        storeViewHolder.tvStore.setTag(groupPosition);

        // 店铺点击事件的监听
        storeViewHolder.tvStore.setOnClickListener(listener);
        storeViewHolder.tvEdit.setOnClickListener(listener);
        storeViewHolder.ivCheckStore.setOnClickListener(listener);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GoodsViewHolder goodsViewHolder;
        if (convertView == null) {
            goodsViewHolder = new GoodsViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_good_item, parent, false);
            goodsViewHolder.ivCheckGoods = (ImageView) convertView.findViewById(R.id.ivCheckGood);
            goodsViewHolder.ivGoods= (ImageView) convertView.findViewById(R.id.ivGoods);
            goodsViewHolder.tvPrice= (TextView) convertView.findViewById(R.id.tvPrice);
            convertView.setTag(goodsViewHolder);
        }else {
            goodsViewHolder = (GoodsViewHolder) convertView.getTag();
        }
        // 设置选中图片
        ShoppingCartBean.Goods goods = mList.get(groupPosition).getGoods().get(childPosition);

        ShoppingCartUtils.setCheckImage(goods.isChildSelected(),goodsViewHolder.ivCheckGoods);
        goodsViewHolder.tvPrice.setText(goods.getPrice());

        // 设置tag
        goodsViewHolder.ivCheckGoods.setTag(groupPosition+","+childPosition);
        goodsViewHolder.ivCheckGoods.setOnClickListener(listener);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return false;
    }

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 点击店铺选择按钮
                case R.id.ivCheckStore:
                    int groupPosition = Integer.parseInt(String.valueOf(v.getTag()));
                    boolean isSelectedAll = ShoppingCartUtils.selectGroup(mList, groupPosition);
                    notifyDataSetChanged();
                    break;
                // 点击跳转到店铺界面
                case R.id.tvShopName:
                    // 得到 店铺的 groupPosition
                    int groupPosition1 = Integer.parseInt(String.valueOf(v.getTag()));
                    break;
                case R.id.tvEdit:
                    break;

                case R.id.ivCheckGood: // 点击商品 选中状态 按钮
                    String tag = String.valueOf(v.getTag());
                    if(tag.contains(",")){
                        String[] split = tag.split(",");
                        int groupPosition2 = Integer.parseInt(split[0]);
                        int childPosition2 = Integer.parseInt(split[1]);
                        ShoppingCartUtils.selectOne(mList,groupPosition2,childPosition2);
                        notifyDataSetChanged();

                    }

                    break;
            }
        }
    };

    public void setData(List<ShoppingCartBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public static class StoreViewHolder{
        TextView tvStore;
        TextView tvEdit;
        ImageView ivCheckStore;
    }

    public static class GoodsViewHolder{
        ImageView ivGoods;
        ImageView ivCheckGoods;
        TextView tvPrice;
    }
}
