package com.example.schoolshop.fragment;

import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;

public class MeFragment extends BaseFragment {
    public String UserName;
    public MeFragment(String user) {
        this.UserName = user;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_me;
    }
    @Override
    protected void initView(View rootview) {
        setUpState(State.SUCCESS);
    }
}
