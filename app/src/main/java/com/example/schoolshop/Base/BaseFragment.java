package com.example.schoolshop.Base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.example.schoolshop.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private State currentState = State.NONE;
    private View successView;
    private View loadingView;
    private View errorView;
    private View emptyView;
    public enum State{
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }
    private Unbinder mbind;

    private FrameLayout mbaseContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.base_fragment_layout,container,false);
        mbaseContainer = rootview.findViewById(R.id.base_container);
        loadStateView(inflater,container,savedInstanceState);
        mbind= ButterKnife.bind(this,rootview);
        initView(rootview);
        initPresenter();
        loadDate();
        return rootview;
    }

    //加载各种状态
    private void loadStateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //成功的view
        successView = loadsuccessView(inflater,container,savedInstanceState);
        mbaseContainer.addView(successView);
        //loading的view
        loadingView = loadLoadingView(inflater,container,savedInstanceState);
        mbaseContainer.addView(loadingView);
        //error的view
        errorView = loadErrorView(inflater,container,savedInstanceState);
        mbaseContainer.addView(errorView);
        //empty的view
        emptyView = loadEmptyView(inflater,container,savedInstanceState);
        mbaseContainer.addView(emptyView);
        setUpState(State.NONE);
    }

    public void setUpState(State state) {
        this.currentState = state;
        successView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE:View.GONE);
        loadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE:View.GONE);
        errorView.setVisibility(currentState == State.ERROR ? View.VISIBLE:View.GONE);
        emptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE:View.GONE);


    }

    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }

    protected View loadErrorView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_error,container,false);
    }

    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_loading,container,false) ;
    }



    private View loadsuccessView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resId = getRootViewId();
        Log.d("state1","调用到这里了");
        return inflater.inflate(resId,container,false);
    }


    //加载loading


    protected void initView(View rootview) {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mbind != null){
            mbind.unbind();
        }
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



    protected abstract int getRootViewId();

}
