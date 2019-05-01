package com.lj.rgreader.holder;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.lj.rgreader.base.Chapter;
import com.lj.rgreader.kanshushenqi.ChapterImpl;
import com.lj.rgreader.utils.HttpUtil;

public class ChapterHolder {
    private static final String TAG = "ChapterHolder";
    private static final String QUERY_SOURCE = "http://api.zhuishushenqi.com/atoc?view=summary&book=%s";
    private static final String QUERY_CHAPTER = "http://api.zhuishushenqi.com/atoc/%s?view=chapters";

    private String bookId;
    private JSONObject mSourceObj;

    private List<Chapter> chapterList;
//
    private String chaptersId;

    public ChapterHolder(String bookId, List<Chapter> chapterList) {
        this.chapterList = chapterList;
        this.bookId = bookId;
        selectSource();
        selectChapters();
    }

    public void selectSource() {
        try {
            String response = HttpUtil.sendHttpRequest(String.format(QUERY_SOURCE, bookId));
            parseResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析返回的书的数据
     * @param response
     */

    private void parseResponse(String response) {
        try {
            JSONArray sourceArray = new JSONArray(response);

            for (int i = 1; i < sourceArray.length(); i++) {
                JSONObject sourceObj = sourceArray.getJSONObject(i);
                String sourceName = sourceObj.getString("name");
                if (sourceName.equals("xbiquge")) {
                    mSourceObj = sourceObj;
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//
    private void selectChapters() {
        String response = HttpUtil.sendHttpRequest(String.format(QUERY_CHAPTER, getId(mSourceObj)));
        Log.d(TAG, response);
        parseResponseChapters(response);
    }

    private void parseResponseChapters(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("chapters");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject chapter = (JSONObject) jsonArray.get(i);
                Chapter chap = new ChapterImpl(chapter);
                chapterList.add(chap);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getId(JSONObject jsonObject) {
        String id = null;
        try {
            id = jsonObject.getString("_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}
