package com.mihua.frameproject.vlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.mihua.code.base.mvp.BaseActivity;
import com.mihua.code.base.mvp.BasePresenter;
import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.cart.CartFragment;
import com.mihua.frameproject.vlayout.categroy.ClassifyFragment;
import com.mihua.frameproject.vlayout.home.HomeFragment;
import com.mihua.frameproject.vlayout.my.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class VLayoutActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.main_tab_bar)
    RadioGroup mRadioGroup;
    private ArrayList<Fragment> mList;
    private int currntTab;

    @Override
    protected int getLayout() {
        return R.layout.activity_vlayout;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initEventAndData(Bundle bundle) {

        initData(bundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putInt("CURRENTTAB", currntTab);
    }

    private void initData(Bundle savedInstanceState) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 第一次初始化 将Fragment 添加到 管理器中
        mList = new ArrayList<>();
        if(savedInstanceState==null){

            mList.add(new HomeFragment());
            mList.add(new ClassifyFragment());
            mList.add(new CartFragment());
            mList.add(new MyFragment());

            // 将4个 Fragment添加到容器中,并隐藏
            for (int i = 0; i < mList.size(); i++) {
                transaction.add(R.id.main_container,mList.get(i),i+"");
                transaction.hide(mList.get(i));
            }
            currntTab = 0;
            transaction.show(mList.get(0));
            setCurrentTab(0);
        }else {
            for (int i = 0; i < 4; i++) {
                Fragment fragment = fragmentManager.findFragmentByTag(i + "");
                mList.add(fragment);
                transaction.hide(fragment);
            }
            // 得到保存的tab，切换到其中去
            int index = savedInstanceState.getInt("CURRENTTAB");
            Fragment fragment = fragmentManager.findFragmentByTag(index + "");
            transaction.show(fragment);
            setCurrentTab(index);

            Log.d("market","mlist|"+mList.size()+"...."+"index"+index);
        }
        transaction.commit();
    }

    @Override
    protected void setListener() {
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    // 设置当前的Tab
    private void setCurrentTab(int index) {
        switch (index) {
            case 0:
                mRadioGroup.check(R.id.main_tab_home);
                break;
            case 1:
                mRadioGroup.check(R.id.main_tab_classify);
                break;
            case 2:
                mRadioGroup.check(R.id.main_tab_gocart);
                break;
            case 3:
                mRadioGroup.check(R.id.main_tab_personal);
                break;
            default:
                break;
        }
    }

    /**
     * 切换Fragment的方法,用于显示和隐藏Fragment
     * @param index
     */
    private void switchFragment(int index) {

        int size = mList.size();
        Log.d("market","mlist|"+mList.size());
        if(index>=0 && index<size){
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            for (int i = 0; i < size; i++) {
                Fragment fragment = mList.get(i);
                if(i==index){
                    transaction.show(fragment);
                    fragment.setUserVisibleHint(true);
                }else {
                    transaction.hide(fragment);
                    fragment.setUserVisibleHint(false);
                }
            }
            transaction.commit();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        // 用索引记录 选中的是哪一个 RadioButton
        int index = 0;
        switch (checkedId) {
            case R.id.main_tab_home:
                index = 0;
                break;
            case R.id.main_tab_classify:
                index = 1;
                break;
            case R.id.main_tab_gocart:
                index = 2;
                break;
            case R.id.main_tab_personal:
                index = 3;
                break;
        }
        // 记录当前的 tab;
        currntTab = index;
        Log.d("market","index"+index);
        // 切换Fragment
        switchFragment(index);
    }
}
