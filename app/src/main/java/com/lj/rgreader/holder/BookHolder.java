package com.lj.rgreader.holder;

import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import com.lj.rgreader.base.Book;
import com.lj.rgreader.kanshushenqi.BookImpl;
import com.lj.rgreader.utils.HttpUtil;

public class BookHolder {

    private Handler handler;

    public static final String TAG = "BookHolder";

    private List<Book> bookList = null;

    private static final String QUERY_BOOK = "http://api.zhuishushenqi.com/book/fuzzy-search?query=%s";

    public BookHolder(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void query(String keyWords) {
        Log.d(TAG, "query success");
        Log.d(TAG, String.format(QUERY_BOOK, keyWords));
        String response = HttpUtil.sendHttpRequest(String.format(QUERY_BOOK, keyWords));
        parseResponse(response);
    }

    private void parseResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getBoolean("ok")) {
                JSONArray bookArray = jsonObject.getJSONArray("books");

                for (int i = 0; i < bookArray.length(); i++) {
                    JSONObject bookObj = bookArray.getJSONObject(i);
                    bookList.add(new BookImpl(bookObj));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
