package com.example.schoolshop.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder mbind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = loadRootView(inflater,container,savedInstanceState);
        mbind = ButterKnife.bind(this,rootview);
        initView(rootview);
        initPresenter();
        loadDate();
        return rootview;
    }

    protected void initView(View rootview) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mbind != null){
            mbind.unbind();
        }
        release();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    protected void release() {
        //释放资源
    }

    protected void initPresenter() {
        //创建Presenter

    }

    protected void loadDate() {
        //加载数据

    }

    private View loadRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int resId = getRootViewId();
            return inflater.inflate(resId,container,false);
    }

    protected abstract int getRootViewId();
}
