package com.example.schoolshop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.R;

import java.time.LocalDate;

import static com.example.schoolshop.utils.Constants.KEY_HOME_PAGER_TITLE;

public class HomePagerFragment extends BaseFragment {

    public static HomePagerFragment newInstance(String title) {
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_HOME_PAGER_TITLE,title);
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootview) {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void loadDate() {
        Bundle arguments = getArguments();
        String title =  arguments.getString(KEY_HOME_PAGER_TITLE);
        Log.d("title","title---->"+title);
        //加载title查到的数据

    }
}
