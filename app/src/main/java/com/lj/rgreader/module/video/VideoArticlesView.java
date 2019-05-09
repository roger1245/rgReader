package com.lj.rgreader.module.video;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lj.rgreader.R;
import com.lj.rgreader.module.video.bean.RecommendInfo;

import java.util.List;

public class VideoArticlesView extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String CATEGORY_ID = "CATEGORY_ID";
    private String categoryId;
    private VideoArticlesPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    public static VideoArticlesView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID, categoryId);
        VideoArticlesView videoArticlesView = new VideoArticlesView();
        videoArticlesView.setArguments(bundle);
        return videoArticlesView;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        categoryId = getArguments().getString(CATEGORY_ID);

        presenter = new VideoArticlesPresenter();
        presenter.attach(this);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        initData();
        presenter.fetchArticles();
        return view;
    }
    private void initView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout_fragment_list);
        recyclerView = view.findViewById(R.id.recycler_view_fragment_list);
    }
    private void initData() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }


    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
            presenter.unSubscribe();
        }
    }

    @Override
    public void onRefresh() {
        presenter.fetchArticles();
    }

    public void onSetAdapter(List<RecommendInfo.ResultBean> list) {


    }
}
