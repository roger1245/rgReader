package com.lj.rgreader.module.video.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = "BasePagerAdapter";
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = new ArrayList<>(Arrays.asList(titles));
        for (int i = 0; i < titleList.size(); i++) {
            Log.d(TAG, titleList.get(i));
        }
        Log.d(TAG, String.valueOf(titleList.size()));
    }

//    public BasePagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
//        super(fm);
//        this.fragmentList = fragmentList;
//        this.titleList = titleList;
//    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
