package com.lj.rgreader.holder;

import android.util.Log;

import com.lj.rgreader.base.Chapter;
import com.lj.rgreader.base.Content;
import com.lj.rgreader.kanshushenqi.ContentImpl;
import com.lj.rgreader.utils.HttpUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ContentHolder {

    public static final String TAG = "ContentHolder";
    private static final String QUERY_CONTENT = "http://chapterup.zhuishushenqi.com/chapter/%s";

    private Chapter chapter;

    public ContentHolder(Chapter chapter) {
        this.chapter = chapter;
    }

    public Content getContent() {
        String response = HttpUtil.sendHttpRequest(String.format(QUERY_CONTENT, getEncodedLink()));
        JSONObject jsonObject = null;
        JSONObject contentObj = null;
        try {
            jsonObject = new JSONObject(response);
            contentObj = jsonObject.getJSONObject("chapter");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Content content = new ContentImpl(contentObj);
        try {
            Log.d(TAG, jsonObject.getString("body"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.d(TAG, content.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    private String getEncodedLink() {
        String encodedLink = null;

        try {
            encodedLink = URLEncoder.encode(chapter.getLink(), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return encodedLink;
    }
}
