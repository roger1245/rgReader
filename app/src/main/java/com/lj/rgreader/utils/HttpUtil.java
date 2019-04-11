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

//
//                HttpURLConnection connection = null;
//                try {
//
//
//                    URL url = new URL(address);
//                    Log.d(TAG, "1");
//                    connection = (HttpsURLConnection) url.openConnection();
//                    Log.d(TAG, "1.1");
//                    connection.setRequestMethod("GET");
//                    Log.d(TAG, address);
//                    Log.d(TAG, "1.2");
//                    connection.setConnectTimeout(8000);
//                    Log.d(TAG, "2");
//                    connection.setReadTimeout(8000);
//                    InputStream in = connection.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    Log.d(TAG, "3");
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//
//                    Log.d(TAG, "4");
//                    if (listener != null) {
//                        listener.onFinish(response.toString());
//                        Log.d(TAG, response.toString());
//                    }
//                } catch (Exception e) {
//                    if (listener != null) {
//                        listener.onError(e);
//                    }
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
        return null;

    }
}
