package com.example.schoolshop.fragment;

import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;

public class HomePagerFragment extends BaseFragment {
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootview) {
        setUpState(State.SUCCESS);
    }
}
