package com.lj.rgreader.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lj.rgreader.R;
import com.lj.rgreader.activity.ChapterActivity;
import com.lj.rgreader.base.Chapter;
import com.lj.rgreader.holder.ChapterHolder;
import com.lj.rgreader.holder.ContentHolder;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    public static final String TAG = "ChapterAdapter";
    private List<Chapter> mChapterList;

    public ChapterAdapter(List<Chapter> mChapterList) {
        this.mChapterList = mChapterList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View chapterView;
        TextView chapterTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterView = itemView;
            chapterTitle = itemView.findViewById(R.id.chapter_name);
        }
    }

    @NonNull
    @Override
    public ChapterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chapter_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        holder.chapterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.d(TAG, String.valueOf(position));
                Chapter chapter = mChapterList.get(position);
                Log.d(TAG, chapter.getLink());
                ChapterActivity chapterActivity = (ChapterActivity)v.getContext();
                chapterActivity.getContent(chapter);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ViewHolder viewHolder, int i) {
        Chapter chapter = mChapterList.get(i);
        viewHolder.chapterTitle.setText(chapter.getTitle());

    }

    @Override
    public int getItemCount() {
        return mChapterList.size();
    }
}
