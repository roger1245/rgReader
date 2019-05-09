package com.lj.rgreader.utils;

public interface CallbackListener <T> {
    void onFinish(T t);
    void onError();
}
