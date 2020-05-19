package com.example.schoolshop.controller;

import com.example.schoolshop.Base.IBasePresenter;
import com.example.schoolshop.view.IGoodsPagerCallback;

public interface IGoodsPagerPresenter extends IBasePresenter<IGoodsPagerCallback> {
    //根据title获取商品信息
    void getGoodsByTitle(String title);

    void LoadMore(String title);

    void Reload(String title);

}
