package com.lj.rgreader.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lj.ljimageloader.ImageLoader;
import com.lj.rgreader.R;
import com.lj.rgreader.activity.MainActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.lj.rgreader.base.Book;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_CONTENT = 0;
    private final static int TYPE_FOOTER = 1;

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
        if (i == TYPE_FOOTER) {
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

                    MainActivity.startIntent(v.getContext(), book.getId());
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
            ImageLoader.build(viewHolder.itemView.getContext()).bindBitmap(decodedLink, viewHolder.bookImage);
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
