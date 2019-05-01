package com.lj.rgreader;

import android.app.Application;
import android.content.Context;

public class InitApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}
