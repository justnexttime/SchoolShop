package com.example.schoolshop.controller;

public interface IGoodsPagerPresenter {
    //根据title获取商品信息
    void getGoodsByTitle(String title);

    void LoadMore(String title);

    void Reload(String title);
}
