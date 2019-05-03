package com.lj.rgreader.module.video;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.rgreader.R;

public class VideoArticlesView extends Fragment {
    private static final String CATEGORY_ID = "CATEGORY_ID";
    private String categoryId;

    public static VideoArticlesView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID, categoryId);
        VideoArticlesView videoArticlesView = new VideoArticlesView();
        videoArticlesView.setArguments(bundle);
        return videoArticlesView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        categoryId = getArguments().getString(CATEGORY_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }
}
