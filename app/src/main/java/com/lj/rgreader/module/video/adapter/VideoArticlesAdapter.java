package com.lj.rgreader.module.video.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
