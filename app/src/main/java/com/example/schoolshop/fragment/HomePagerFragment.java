package com.example.schoolshop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;
import com.example.schoolshop.controller.IGoodsPagerPresenter;
import com.example.schoolshop.controller.impl.GoodsPagerPresenterImpl;
import com.example.schoolshop.entity.Goods;
import com.example.schoolshop.view.IGoodsPagerCallback;

import java.util.ArrayList;

import static com.example.schoolshop.utils.Constants.KEY_HOME_PAGER_TITLE;

public class HomePagerFragment extends BaseFragment implements IGoodsPagerCallback {

    private IGoodsPagerPresenter mgoodsPagerPresenter;

    public static HomePagerFragment newInstance(String title) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_HOME_PAGER_TITLE,title);
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootview) {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void initPresenter() {
        mgoodsPagerPresenter = GoodsPagerPresenterImpl.getsInstance();
        mgoodsPagerPresenter.registerViewCallback(this);
    }

    @Override
    protected void loadDate() {
        Bundle arguments = getArguments();
        String title =  arguments.getString(KEY_HOME_PAGER_TITLE);
        Log.d("title","title---->"+title);
        //加载title查到的数据
        if (mgoodsPagerPresenter!=null) {
            mgoodsPagerPresenter.getGoodsByTitle(title);
        }
    }

    @Override
    public void onContentload(ArrayList<Goods> content) {

    }

    @Override
    public void onLoading(String title) {

    }

    @Override
    public void onError(String title) {

    }

    @Override
    public void onEmpty(String title) {

    }

    @Override
    public void onLoadMoreLoaded(ArrayList<Goods> content) {

    }

    @Override
    public void onLoadMoreError(String title) {

    }

    @Override
    public void onLoadMoreEmpty(String title) {

    }

    @Override
    protected void release() {
        if (mgoodsPagerPresenter!=null) {
            mgoodsPagerPresenter.unregisterViewCallback(this);
        }
    }
}
