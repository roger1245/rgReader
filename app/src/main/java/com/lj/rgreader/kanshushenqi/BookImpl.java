package com.lj.rgreader.kanshushenqi;

import org.json.JSONObject;

import com.lj.rgreader.base.Book;

public class BookImpl implements Book {

//    private static final String QUERY_CHAPTER = "http://api.zhuishushenqi.com/atoc/%s?view=chapters";
//
    private JSONObject mBookObj;
//    private JSONObject mSourceObj;
//
//    private List<Chapter> chapterList;
//
    public BookImpl(JSONObject mBookObj) {
        this.mBookObj = mBookObj;
    }
//

//    private String getId(JSONObject jsonObject) {
//        String id = null;
//        try {
//            id = jsonObject.getString("_id");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
//
    @Override
    public String getTitle() {
        String title = null;
        try {
            title = mBookObj.getString("title");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }

    public String getId() {
        String id = null;
        try {
            id = mBookObj.getString("_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getCover() {
        String cover = null;
        try {
            cover = mBookObj.getString("cover");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cover;
    }

    //
    @Override
    public String getAuthor() {
        String author = null;
        try {
            author = mBookObj.getString("_author");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }
//
    @Override
    public String getShortInform() {
        String bookInform = null;
        try {
            bookInform = mBookObj.getString("shortIntro");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookInform;
    }
//
//
//    @Override



}
