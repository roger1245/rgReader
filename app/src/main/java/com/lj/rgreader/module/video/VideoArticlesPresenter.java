package com.lj.rgreader.module.video;

import com.lj.rgreader.module.video.bean.RecommendInfo;
import com.lj.rgreader.utils.CallbackListener;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class VideoArticlesPresenter {
    private VideoArticlesView videoArticlesView;

    private CompositeDisposable subscription;
    private static String TAG = "VideoArticlesPresenter";
    private List<RecommendInfo.ResultBean> list;

    protected VideoArticlesModel videoArticlesModel = new VideoArticlesModel();

    public VideoArticlesPresenter() {
        subscription = new CompositeDisposable();
    }


    public void fetchArticles() {
        videoArticlesView.showLoading();
        Disposable disposable = videoArticlesModel.fetchArticlesFromInternet(new CallbackListener<List<RecommendInfo.ResultBean>>() {

            @Override
            public void onFinish(List<RecommendInfo.ResultBean> resultBeans) {
                list = resultBeans;
                videoArticlesView.onSetAdapter(list);
                videoArticlesView.hideLoading();
            }

            @Override
            public void onError() {

            }
        });
        addSub(disposable);
    }

    public void attach(VideoArticlesView videoArticlesView) {
        this.videoArticlesView = videoArticlesView;
    }

    public void detach() {
        videoArticlesView = null;
    }
    protected void addSub(Disposable disposable) {
        if (subscription == null) {
            return;
        }

        if (disposable != null) {
            subscription.add(disposable);
        }
    }
    public void unSubscribe() {
        if (subscription == null) {
            return;
        }

        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }


}
