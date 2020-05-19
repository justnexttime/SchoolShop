package com.example.schoolshop.Base;

public interface IBasePresenter<T> {

    void registerViewCallback(T callback);

    void unregisterViewCallback(T callback);
}
