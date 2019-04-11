package com.lj.rgreader.kanshushenqi;

import com.lj.rgreader.base.Chapter;

import org.json.JSONObject;

public class ChapterImpl implements Chapter {

    private JSONObject mChapterObj;

    public ChapterImpl(JSONObject mChapterObj) {
        this.mChapterObj = mChapterObj;
    }

    @Override
    public String getTitle() {
        String title = null;
        try {
            title = mChapterObj.getString("title");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }

    @Override
    public String getLink() {
        String link = null;
        try {
            link = mChapterObj.getString("link");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return link;
    }
}
