package com.example.schoolshop.controller.impl;

import com.example.schoolshop.view.IHomeCallback;

public interface IHomePresenter {
    /*
    *获取商品分类
    * */
    void getGoodskinds();

    /*
     *注册ui
     */
    void registerCallback(IHomeCallback iHomeCallback);

    /*
    * 释放ui注册
    */
    void unregisterCallback(IHomeCallback iHomeCallback);
}
