package com.example.schoolshop.adapter;

import android.util.Log;

import com.example.schoolshop.fragment.HomePagerFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private  ArrayList<String>  gklist1 = new ArrayList<String>();
    private  HomePagerFragment homePagerFragment;
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return gklist1.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        homePagerFragment = new HomePagerFragment();
        return homePagerFragment;
    }

    @Override
    public int getCount() {
        return gklist1.size();
    }


    public void setgoodkinds(ArrayList<String> gklist) {
            gklist1.clear();
            gklist1.addAll(gklist);
            Log.d("adapter","adapter--->"+gklist1.toString());
            notifyDataSetChanged();

    }
}
