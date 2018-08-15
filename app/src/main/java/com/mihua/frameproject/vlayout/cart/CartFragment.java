package com.mihua.frameproject.vlayout.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.mihua.code.base.mvp.BaseLazyFragment;
import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.cart.bean.ShoppingCartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CartFragment extends BaseLazyFragment<CartContract.Presenter> implements CartContract.View {


    @BindView(R.id.expandableListView)
    ExpandableListView mExpandableListView;
    @BindView(R.id.ivSelectAll)
    ImageView mIvSelectAll;
    private ArrayList<ShoppingCartBean> mList;
    private CartExpandableListAdapter mAdapter;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_cart;
    }

    @Override
    protected CartContract.Presenter getPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

        initView();
        initData();

    }

    private void initView() {
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }


    private void initData() {


        mList = new ArrayList<>();

        mAdapter = new CartExpandableListAdapter(mContext,mList);
        mExpandableListView.setAdapter(mAdapter);


        mPresenter.getCartData(mContext);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void setCartData(List<ShoppingCartBean> list) {
        mList.addAll(list);
        mAdapter.setData(list);
        expandAllGroup();
    }

    /**
     * 展开所有组
     */
    private void expandAllGroup() {

        for (int i = 0; i < mList.size(); i++) {
            mExpandableListView.expandGroup(i);
        }
    }
}
