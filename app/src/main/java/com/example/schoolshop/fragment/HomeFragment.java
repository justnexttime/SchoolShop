package com.example.schoolshop.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;
import com.example.schoolshop.adapter.HomePagerAdapter;
import com.example.schoolshop.controller.impl.HomePresenterimpl;
import com.example.schoolshop.controller.IHomePresenter;
import com.example.schoolshop.view.IHomeCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class HomeFragment extends BaseFragment implements IHomeCallback {
    @BindView(R.id.home_indicator)
    TabLayout mtabLayout;
    @BindView(R.id.home_pager)
    ViewPager homepager;
    private  IHomePresenter mhomePresenterimpl;
    private  HomePagerAdapter homePagerAdapter;

    public String UserName;
    public HomeFragment(String user) {
       this.UserName = user;
    }

    @Override
    protected int getRootViewId() {

        return R.layout.fragment_home;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_home_fragment_layout,container,false);
    }

    @Override
    protected void initView(View rootview) {
        mtabLayout.setupWithViewPager(homepager);
        //给viewpager设置适配器
         homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(),UserName);
         homepager.setAdapter(homePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mhomePresenterimpl = new HomePresenterimpl();
        mhomePresenterimpl.registerViewCallback(this);
    }

    @Override
    protected void loadDate() {
        //加载数据
        mhomePresenterimpl.getGoodskinds();
    }

    @Override
    public void onGoodskindLoaded(ArrayList<String> gklist) {
        setUpState(State.SUCCESS);
        //加载的数据回来
        homePagerAdapter.setgoodkinds(gklist);
        Log.d("gklist","call--->"+gklist.toString());
    }

    @Override
    public void onNetworkError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    protected void release() {
        //取消回调注册
        if(mhomePresenterimpl!=null){
            mhomePresenterimpl.unregisterViewCallback(this);
        }
    }

    @Override
    protected void onRetryClick() {
        if(mhomePresenterimpl!=null){
            mhomePresenterimpl.getGoodskinds();
        }
    }
}
