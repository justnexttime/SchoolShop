package com.example.schoolshop.Base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;


import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mbind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
        mbind = ButterKnife.bind(this);
        initView();
        initEvents();

    }

    protected void initEvents() {

    }

    protected abstract void initView();

    protected abstract int getlayoutId();

    protected void onDestroy() {
        super.onDestroy();
        if(mbind!=null){
            mbind.unbind();
        }
    }

}
