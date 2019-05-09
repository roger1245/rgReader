package com.lj.rgreader.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

    public static final String TAG = "HttpUtil";


    public static String sendHttpRequest(final String address) {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(address).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
