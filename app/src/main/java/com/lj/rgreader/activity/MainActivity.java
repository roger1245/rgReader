package com.lj.rgreader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.lj.rgreader.R;
import com.lj.rgreader.adapter.BookAdapter;

import java.util.ArrayList;
import java.util.List;

import com.lj.rgreader.base.Book;
import com.lj.rgreader.callbackListener.OnLoadMoreListener;
import com.lj.rgreader.holder.BookHolder;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    private List<Book> books;
    private EditText mEditText;
    private BookAdapter bookAdapter;
    private Button mButton;

    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit_text);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mButton = findViewById(R.id.button_book);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> new FetchBooks().execute());
//        ImageLoader imageLoader =
        mButton.setOnClickListener(v -> {
            swipeRefreshLayout.setRefreshing(true);
            new FetchBooks().execute();
            Log.d(TAG, "onClick");
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                for (int i = 0; i < 2; i++) {
                    books.add(books.get(i));
                }
                bookAdapter.notifyDataSetChanged();
            }
        });
        books = new ArrayList<>();
        bookAdapter = new BookAdapter(books);
        recyclerView.setAdapter(bookAdapter);
        Log.d(TAG, "Activity create");

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
        intent.putExtra("extra_data",bookId );
        context.startActivity(intent);
    }


}
