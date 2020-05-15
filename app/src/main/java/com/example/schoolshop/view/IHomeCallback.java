package com.example.schoolshop.view;

import com.example.schoolshop.entity.Goodskind;

import java.util.ArrayList;
import java.util.List;

public interface IHomeCallback {

    void onGoodskindLoaded(ArrayList<String> gklist);

    void onNetworkError();

    void onLoading();

    void  onEmpty();
}
