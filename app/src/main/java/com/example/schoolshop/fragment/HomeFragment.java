package com.example.schoolshop.fragment;


import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;
import com.example.schoolshop.adapter.HomePagerAdapter;
import com.example.schoolshop.controller.impl.HomePresenterimpl;
import com.example.schoolshop.controller.impl.IHomePresenter;
import com.example.schoolshop.entity.Goodskind;
import com.example.schoolshop.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class HomeFragment extends BaseFragment implements IHomeCallback {
    @BindView(R.id.home_indicator)
    TabLayout mtabLayout;
    @BindView(R.id.home_pager)
    ViewPager homepager;
    private  IHomePresenter mhomePresenterimpl;
    private   HomePagerAdapter mhomePagerAdapter;

    @Override
    protected int getRootViewId() {

        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootview) {
        //初始化控件
        mtabLayout.setupWithViewPager(homepager);
        //homepager 设置适配器
         mhomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        //加载配置器
        homepager.setAdapter(mhomePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mhomePresenterimpl = new HomePresenterimpl();
        mhomePresenterimpl.registerCallback(this);
    }

    @Override
    protected void loadDate() {
        //加载数据
        mhomePresenterimpl.getGoodskind();

    }

    @Override
    public void onGoodskindLoaded(List<Goodskind> goodskind) {
        //加载的数据回来
        if (mhomePagerAdapter!=null){
            mhomePagerAdapter.setGoodskind(goodskind);
        }
    }

    @Override
    protected void release() {
        //取消回调注册
        if(mhomePresenterimpl != null){
            mhomePresenterimpl.unregisterCallback(this);
        }

    }
}
