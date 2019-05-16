package com.lj.rgreader.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lj.ljimageloader.ImageLoader;
import com.lj.rgreader.R;
import com.lj.rgreader.base.Book;
import com.lj.rgreader.module.NovelTabLayout;

import java.net.URLDecoder;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_CONTENT = 0;
    private final static int TYPE_FOOTER = 1;
    private boolean isFirstCreate = true;

    public boolean isFirstCreate() {
        return isFirstCreate;
    }

    public void setFirstCreate(boolean firstCreate) {
        isFirstCreate = firstCreate;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mBookList.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    public static final String TAG = "BookAdapter";
    private List<Book> mBookList;

    public BookAdapter(List<Book> mBookList) {
        this.mBookList = mBookList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER && !isFirstCreate) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_item, viewGroup, false);
            return new FootViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_item, viewGroup, false);
            ItemViewHolder holder = new ItemViewHolder(view);
            holder.bookView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Book book = mBookList.get(position);

                    NovelTabLayout.startIntent(v.getContext(), book.getId());

                }
            });
            return holder;
        }
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (getItemViewType(i) == TYPE_FOOTER) {

        } else {
            Book book = mBookList.get(i);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.bookName.setText(book.getTitle());
            Log.d(TAG, book.getTitle());
            viewHolder.bookInform.setText(book.getShortInform());
            String cover = book.getCover();
            int begin = cover.indexOf("http");
            int last = cover.indexOf(".jpg");
            String url = cover.substring(begin, last + 4);

            String decodedLink = null;
            try {
                decodedLink = URLDecoder.decode(url, "UTF-8");
            }
            catch (Exception e) {
                /**
                 * ignore
                 */
            }
            Log.d(TAG, decodedLink);

//            Glide.with(viewHolder.itemView.getContext())
//                    .load(decodedLink)
//                    .placeholder(R.drawable.bili_loading)
//                    .into(viewHolder.bookImage);
            ImageLoader.build(viewHolder.itemView.getContext())
                    .place(R.drawable.bili_loading)
                    .error(R.drawable.jiantou)
                    .bindBitmap(decodedLink, viewHolder.bookImage);



        }
    }

    @Override
    public int getItemCount() {
        return mBookList.size() + 1;
    }

    static class  ItemViewHolder extends RecyclerView.ViewHolder {

        View bookView;
        ImageView bookImage;
        TextView bookName;
        TextView bookInform;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            bookView = itemView;
            bookName = itemView.findViewById(R.id.book_name);
            bookInform = itemView.findViewById(R.id.book_short_inform);
            bookImage = itemView.findViewById(R.id.book_image_view);
        }
    }
    static class FootViewHolder extends RecyclerView.ViewHolder {


        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
