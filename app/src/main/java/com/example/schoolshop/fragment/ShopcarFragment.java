package com.example.schoolshop.fragment;

import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;

public class ShopcarFragment extends BaseFragment {
    public String UserName;

    public ShopcarFragment(String user) {
        this.UserName = user;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_shopcar;
    }
    @Override
    protected void initView(View rootview) {
        setUpState(State.SUCCESS);
    }
}
