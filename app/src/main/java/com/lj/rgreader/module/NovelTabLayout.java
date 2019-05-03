package com.lj.rgreader.module;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lj.rgreader.R;
import com.lj.rgreader.activity.ChapterActivity;
import com.lj.rgreader.adapter.BookAdapter;
import com.lj.rgreader.base.Book;
import com.lj.rgreader.callbackListener.OnLoadMoreListener;
import com.lj.rgreader.holder.BookHolder;
import com.lj.rgreader.module.video.VideoTabLayout;

import java.util.ArrayList;
import java.util.List;

public class NovelTabLayout extends Fragment {
    private static String TAG = "NovelTabLayout";
    private static NovelTabLayout instance = null;
    private List<Book> books;
    private EditText mEditText;
    private BookAdapter bookAdapter;
    private Button mButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    //DLC单例模式
    public static NovelTabLayout getInstance() {
        if (instance == null) {
            synchronized (VideoTabLayout.class) {
                if (instance == null) {
                    instance = new NovelTabLayout();
                }
            }
        }
        return instance;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novel_tab, container, false);
        initView(view);
        return view;
    }
    private class FetchBooks extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String bookName = mEditText.getText().toString();
            if (books != null) {
                books.clear();
            }
            Log.d(TAG, bookName);
            BookHolder platform = new BookHolder(books);
            platform.query(bookName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            swipeRefreshLayout.setRefreshing(false);
            bookAdapter.notifyDataSetChanged();
        }


    }

    public static void startIntent(Context context, String bookId) {
        Intent intent = new Intent(context, ChapterActivity.class);
        intent.putExtra("extra_data", bookId);
        context.startActivity(intent);
    }

    private void initView(View view) {
        mEditText = view.findViewById(R.id.edit_text);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mButton = view.findViewById(R.id.button_book);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> new FetchBooks().execute());
        mButton.setOnClickListener(v -> {
            swipeRefreshLayout.setRefreshing(true);
            new FetchBooks().execute();
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                for (int i = 0; i < 2; i++) {
                    books.add(books.get(i));
                }
                if (bookAdapter.isFirstCreate()) {
                    bookAdapter.setFirstCreate(false);
                }
                bookAdapter.notifyDataSetChanged();
            }
        });
        books = new ArrayList<>();
        bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
    }
}
