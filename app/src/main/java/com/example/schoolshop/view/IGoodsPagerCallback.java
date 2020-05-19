package com.example.schoolshop.view;

import com.example.schoolshop.entity.Goods;

import java.util.ArrayList;

public interface IGoodsPagerCallback {
    //数据加载回来
    void onContentload(ArrayList<Goods>  content);
    //加载中
    void onLoading(String title);
    //错误
    void onError(String title);
    //数据空
    void onEmpty(String title);

    //下拉加载
    void onLoadMoreLoaded(ArrayList<Goods>  content);

    void onLoadMoreError(String title);

    void onLoadMoreEmpty(String title);

}
