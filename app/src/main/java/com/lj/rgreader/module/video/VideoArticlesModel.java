package com.lj.rgreader.module.video;

import android.util.Log;

import com.lj.rgreader.module.video.bean.RecommendInfo;
import com.lj.rgreader.utils.BiliAppService;
import com.lj.rgreader.utils.CallbackListener;
import com.lj.rgreader.utils.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoArticlesModel {
    private static String TAG = "VideoArticlesModel";
    private List<RecommendInfo.ResultBean> results = new ArrayList<>();


    public Disposable fetchArticlesFromInternet(CallbackListener<List<RecommendInfo.ResultBean>> callbackListener) {
        String url = "http://app.bilibili.com/";
        Log.d(TAG, "start");
        return RetrofitHelper.getRetrofit(url)
                .create(BiliAppService.class)
                .getRecommendedInfo()
                .map(RecommendInfo::getResult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultBeans -> {
                    results.addAll(resultBeans);
                    callbackListener.onFinish(resultBeans);
                }, throwable -> loadError());


    }


    private void loadError() {

    }
}
