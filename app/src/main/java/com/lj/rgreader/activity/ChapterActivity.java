package com.lj.rgreader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lj.rgreader.R;
import com.lj.rgreader.adapter.ChapterAdapter;
import com.lj.rgreader.base.Chapter;
import com.lj.rgreader.base.Content;
import com.lj.rgreader.holder.ChapterHolder;
import com.lj.rgreader.holder.ContentHolder;
import com.lj.rgreader.kanshushenqi.ContentImpl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {

    public static final String TAG = "ChapterActivity";

    private List<Chapter> chapterList;
    private ChapterAdapter chapterAdapter;
    private String bookId;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private Chapter chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        Intent intent = getIntent();
        bookId = intent.getStringExtra("extra_data");
        Log.d(TAG, bookId);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(chapterList.size() - 1);
            }
        });
        recyclerView = findViewById(R.id.recycler_view_chapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chapterList = new ArrayList<>();
        chapterAdapter = new ChapterAdapter(chapterList);
        recyclerView.setAdapter(chapterAdapter);
        new FetchChapters().execute();

    }

    private class FetchChapters extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ChapterHolder chapterHolder = new ChapterHolder(bookId, chapterList);
            Log.d(TAG, chapterList.get(0).getTitle());
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            chapterAdapter.notifyDataSetChanged();

        }
    }

    private class FetchContent extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ContentHolder contentHolder = new ContentHolder(chapter);
            Content content = contentHolder.getContent();
            Log.d(TAG, content.getContent());
            ChapterActivity.startIntent(recyclerView.getContext(), content.getContent());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public static void startIntent(Context context, String content) {
        Intent intent = new Intent(context, ContentActivity.class);
        intent.putExtra("extra_data",content );
        context.startActivity(intent);
    }

//    private String save(String inputTxt) {
//        FileOutputStream out = null;
//        BufferedWriter writer = null;
//        String fileName = null;
//        try {
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
//            String dateStr = simpleDateFormat.format(date);
//            fileName = dateStr + ".txt";
//            Log.d(TAG, fileName);
//            out = openFileOutput(fileName, Context.MODE_PRIVATE);
//            writer = new BufferedWriter(new OutputStreamWriter(out));
//            writer.write(inputTxt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (writer != null) {
//                    writer.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return fileName;
//    }
    public  void getContent(Chapter chapter) {
            Log.d(TAG, chapter.getLink());
            this.chapter = chapter;
            new FetchContent().execute();


    }
}
