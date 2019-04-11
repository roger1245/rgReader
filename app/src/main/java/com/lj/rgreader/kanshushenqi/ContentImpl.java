package com.lj.rgreader.kanshushenqi;

import com.lj.rgreader.base.Content;

import org.json.JSONObject;

public class ContentImpl implements Content {

    public ContentImpl(JSONObject mContentObj) {
        this.mContentObj = mContentObj;
    }

    private JSONObject mContentObj;
    @Override
    public String getContent() {
        String content = null;
        try {
            content = mContentObj.getString("body");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
