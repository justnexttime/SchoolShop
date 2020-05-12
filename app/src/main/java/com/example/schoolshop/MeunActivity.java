package com.example.schoolshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.os.Bundle;

import com.example.schoolshop.Base.BaseFragment;
import com.example.schoolshop.fragment.HomeFragment;
import com.example.schoolshop.fragment.MeFragment;
import com.example.schoolshop.fragment.ShopcarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MeunActivity  extends FragmentActivity {

    @BindView(R.id.main_navigationbar)
    public BottomNavigationView  bottomNavigationView;
    HomeFragment homeFragment;
    ShopcarFragment shopcarFragment;
    MeFragment meFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meun);
        ButterKnife.bind(this);
        initFragment();
        initListend();


    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        shopcarFragment = new ShopcarFragment();
        meFragment  = new MeFragment();
        fm = getSupportFragmentManager();
        switchFragment(homeFragment);
    }

    private void initListend() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.home){
                    switchFragment(homeFragment);
                }else if(menuItem.getItemId() == R.id.shopcar){
                    switchFragment(shopcarFragment);
                }else if(menuItem.getItemId() == R.id.me){
                    switchFragment(meFragment);
                }

                return true;
            }
        });
    }

    private void switchFragment(BaseFragment baseFragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_page_container,baseFragment);
        transaction.commit();
    }




}
