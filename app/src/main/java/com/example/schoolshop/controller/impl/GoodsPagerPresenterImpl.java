package com.example.schoolshop.controller.impl;

import com.example.schoolshop.controller.IGoodsPagerPresenter;
import com.example.schoolshop.view.IGoodsPagerCallback;

public class GoodsPagerPresenterImpl implements IGoodsPagerPresenter {
    private GoodsPagerPresenterImpl(){

    }

    public static IGoodsPagerPresenter sInstance = null;

    public static IGoodsPagerPresenter getsInstance(){
        if (sInstance == null) {
            sInstance = new GoodsPagerPresenterImpl();
        }
        return sInstance;
    }



    @Override
    public void getGoodsByTitle(String title) {
        //加载title搜索到的内容


    }

    @Override
    public void LoadMore(String title) {

    }

    @Override
    public void Reload(String title) {

    }

    @Override
    public void registerViewCallback(IGoodsPagerCallback callback) {

    }

    @Override
    public void unregisterViewCallback(IGoodsPagerCallback callback) {

    }
}
