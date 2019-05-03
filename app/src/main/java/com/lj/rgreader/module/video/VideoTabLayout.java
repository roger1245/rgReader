package com.lj.rgreader.module.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.rgreader.InitApp;
import com.lj.rgreader.R;
import com.lj.rgreader.module.video.adapter.BasePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoTabLayout extends Fragment {
    private static final String TAG = "VideoTabLayout";

    private static VideoTabLayout instance = null;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static int pageSize = InitApp.getContext().getResources().getStringArray(R.array.mobile_video_id).length;
//    private String[] categoryId = InitApp.getContext().getResources().getStringArray(R.array.mobile_video_id);
    private String[] categoryName = InitApp.getContext().getResources().getStringArray(R.array.mobile_video_name);
    private List<Fragment> fragmentList = new ArrayList<>();
    private BasePagerAdapter adapter;

    //DLC单例模式
    public static VideoTabLayout getInstance() {
        if (instance == null) {
            synchronized (VideoTabLayout.class) {
                if (instance == null) {
                    instance = new VideoTabLayout();
                }
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_tab, container, false);
        initView(view);
        initData();
        return view;
    }
    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tab_layout_video);
        viewPager = view.findViewById(R.id.view_pager_video);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData() {
        for (int i = 0; i < pageSize; i++) {
            Fragment fragment = new VideoArticlesView();
            fragmentList.add(fragment);
        }
        adapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, categoryName);
        viewPager.setAdapter(adapter);
    }
}
