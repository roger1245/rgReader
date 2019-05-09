package com.lj.rgreader.utils;

import com.lj.rgreader.module.video.bean.RecommendInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BiliAppService {

    /**
     * 首页推荐数据
     */
    @GET("x/show/old?platform=android&device=&build=412001")
    Observable<RecommendInfo> getRecommendedInfo();
}
