package com.example.schoolshop.view;

import com.example.schoolshop.Base.IBaseCallback;
import com.example.schoolshop.entity.Goods;

import java.util.List;

public interface IGoodsPagerCallback extends IBaseCallback {

    //取到title
    String titleget();
    //数据加载回来
    void onContentload(List<Goods>  content);

    //下拉加载
    void onLoadMoreLoaded(List<Goods> content);

    void onLoadMoreError();

    void onLoadMoreEmpty();

}
