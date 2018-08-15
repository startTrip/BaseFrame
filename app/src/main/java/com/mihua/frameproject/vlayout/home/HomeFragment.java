package com.mihua.frameproject.vlayout.home;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.mihua.code.base.mvp.BaseFragment;
import com.mihua.frameproject.R;
import com.mihua.frameproject.vlayout.bean.HomeClassifyTitle;
import com.mihua.frameproject.vlayout.bean.SlideBean;
import com.mihua.frameproject.vlayout.home.adapter.BannerAdapter;
import com.mihua.frameproject.vlayout.home.adapter.ClassifyAdapter;
import com.mihua.frameproject.vlayout.home.adapter.ImageAdapter;
import com.mihua.frameproject.vlayout.home.adapter.RankAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View{

    @BindView(R.id.title_layout)
    LinearLayout title_Layout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<SlideBean> mBannerList;
    private ArrayList<HomeClassifyTitle> mClassifyList;
    private RecyclerView.RecycledViewPool mPool;
    private DelegateAdapter mDelegateAdapter;
    private List<DelegateAdapter.Adapter> mAdapters;
    private Runnable r;

    public boolean Bannner_Layout = false;

    public boolean Classify_Layout = true;

    public boolean Rank_Layout = true;

    private boolean Live_Layout  = true;

    private boolean SalePrice_Layout = true;
    private boolean Store_Layout = true;
    private boolean ReComment_Layout = true;
    private VirtualLayoutManager mVirtualLayoutManager;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeContract.Presenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        title_Layout.setAlpha(0);

        initData();
        initRecyclerView();

    }

    // 初始化数据
    private void initData() {
        // Banner 数据的集合
        mBannerList = new ArrayList<>();
        // 店铺分类数据
        mClassifyList = new ArrayList<>();

        initStoreData();

        showProgressDialog();
        mPresenter.getHomeData();
    }

    private void initStoreData() {
        HomeClassifyTitle homeClassifyTitle = new HomeClassifyTitle("半小时达",R.mipmap.content_ic_anhourupto_default_xxhdpi);
        HomeClassifyTitle homeClassifyTitle1 = new HomeClassifyTitle("越北海",R.mipmap.content_ic_yuebaihai_default_xxhdpi);
        HomeClassifyTitle homeClassifyTitle2 = new HomeClassifyTitle("泰润酒店",R.mipmap.content_ic_tairunjiudian_default_xxhdpi);
        HomeClassifyTitle homeClassifyTitle3 = new HomeClassifyTitle("领券中心",R.mipmap.content_ic_centreforbanknote_default_xxhdpi);
        HomeClassifyTitle homeClassifyTitle4 = new HomeClassifyTitle("所有店铺",R.mipmap.content_ic_allstores_default_xxhdpi);
        mClassifyList.add(homeClassifyTitle);
        mClassifyList.add(homeClassifyTitle1);
        mClassifyList.add(homeClassifyTitle2);
        mClassifyList.add(homeClassifyTitle3);
        mClassifyList.add(homeClassifyTitle4);
    }

    private void initRecyclerView() {
        // 设置LayoutManager
        mVirtualLayoutManager = new VirtualLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mVirtualLayoutManager);

        mPool = new RecyclerView.RecycledViewPool();
        mPool.setMaxRecycledViews(0,20);

        mRecyclerView.setRecycledViewPool(mPool);
        mDelegateAdapter = new DelegateAdapter(mVirtualLayoutManager,false);
        mRecyclerView.setAdapter(mDelegateAdapter);

        mAdapters = new LinkedList<>();

    }

    // 设置数据
    private void setData() {

        if(Bannner_Layout) {
            // 显示 外层的布局
            BannerAdapter adapter = new BannerAdapter(mContext,new LinearLayoutHelper(),1,
                    mPool,mBannerList);
            mAdapters.add(adapter);
        }
        if(Classify_Layout){
            GridLayoutHelper layoutHelper = new GridLayoutHelper(5);
            mAdapters.add(new ClassifyAdapter(mContext,layoutHelper,mClassifyList));
        }
        if(Rank_Layout){
            RankAdapter adapter = new RankAdapter(mContext,1,new LinearLayoutHelper(),"排行榜");
            mAdapters.add(adapter);
        }

        if(SalePrice_Layout){
            RankAdapter adapter = new RankAdapter(mContext,1,new LinearLayoutHelper(),"特价销售");
            mAdapters.add(adapter);
        }
        if(Live_Layout){
            OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
            helper.setColWeights(new float[]{40f, 60f});
            mAdapters.add(new ImageAdapter(mContext,helper,2));

            OnePlusNLayoutHelper helper1 = new OnePlusNLayoutHelper();
            helper1.setColWeights(new float[]{50f});
            mAdapters.add(new ImageAdapter(mContext,helper1,3));

            OnePlusNLayoutHelper helper2 = new OnePlusNLayoutHelper();
            mAdapters.add(new ImageAdapter(mContext,helper2,4));
        }
        if(Store_Layout){
            OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
            mAdapters.add(new ImageAdapter(mContext,helper,6));
        }
        if(ReComment_Layout){
            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
            gridLayoutHelper.setAutoExpand(true);
            gridLayoutHelper.setGap(20);
            mAdapters.add(new ImageAdapter(mContext,gridLayoutHelper,30));
        }
        mDelegateAdapter.setAdapters(mAdapters);
        hideProgressDialog();
    }


    @Override
    protected void setListener() {
        // 设置滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int offsetToStart = mVirtualLayoutManager.getOffsetToStart();
                float a = offsetToStart/1000.0f;
                title_Layout.setAlpha(a>1?1:a);
            }
        });
    }

    // 是否有 Banner 图
    @Override
    public void hasBanner(boolean b) {
        Bannner_Layout = b;
    }

    // 包含 Banner图 设置 Banner 图的数据
    @Override
    public void setBannerData(ArrayList<SlideBean> list) {
        mBannerList.clear();
        mBannerList.addAll(list);

    }


    @Override
    public void notifyData() {
        setData();
    }
}
