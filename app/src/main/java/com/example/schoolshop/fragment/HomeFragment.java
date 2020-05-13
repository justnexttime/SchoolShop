package com.example.schoolshop.fragment;


import android.util.Log;
import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;
import com.example.schoolshop.adapter.HomePagerAdapter;
import com.example.schoolshop.controller.impl.HomePresenterimpl;
import com.example.schoolshop.controller.impl.IHomePresenter;
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
    @Override
    protected int getRootViewId() {

        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootview) {
        mtabLayout.setupWithViewPager(homepager);
        //给viewpager设置适配器
         homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
         homepager.setAdapter(homePagerAdapter);
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
        mhomePresenterimpl.getGoodskinds();
    }

    @Override
    public void onGoodskindLoaded(ArrayList<String> gklist) {
        //加载的数据回来
        homePagerAdapter.setgoodkinds(gklist);
        Log.d("gklist","call--->"+gklist.toString());
    }

    @Override
    protected void release() {
        //取消回调注册
        if(mhomePresenterimpl!=null){
            mhomePresenterimpl.unregisterCallback(this);
        }
    }
}
