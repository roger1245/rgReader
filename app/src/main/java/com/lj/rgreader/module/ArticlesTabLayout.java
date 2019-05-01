package com.lj.rgreader.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.rgreader.R;

public class ArticlesTabLayout extends Fragment {
    private static ArticlesTabLayout instance = null;

    //DLC单例模式
    public static ArticlesTabLayout getInstance() {
        if (instance == null) {
            synchronized (ArticlesTabLayout.class) {
                if (instance == null) {
                    instance = new ArticlesTabLayout();
                }
            }
        }
        return instance;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_tab, container, false);
//        initView(view);
//        initData();
        return view;
    }
}
