package com.lj.rgreader.widget.sectionRVAdapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lj.rgreader.R;
import com.lj.rgreader.module.video.bean.RecommendInfo;

import java.util.List;

public class HomeRecommendedSection extends StatelessSection {
    private static final String TAG = "HomeRecommendedSection";

    private Context mContext;
    private String type;

    private static final String TYPE_RECOMMENDED = "recommend";
    private static final String TYPE_LIVE = "live";
    private static final String TYPE_BANGUMI = "bangumi_2";
    private static final String TYPE_ACTIVITY = "activity";
    private static final String TYPE_REGION = "region";

    private int[] icons = new int[]{
            R.drawable.ic_header_hot, R.drawable.ic_head_live,
            R.drawable.ic_category_t13, R.drawable.ic_category_t1,
            R.drawable.ic_category_t3, R.drawable.ic_category_t129,
            R.drawable.ic_category_t4, R.drawable.ic_category_t119,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t155, R.drawable.ic_category_t5,
            R.drawable.ic_category_t11, R.drawable.ic_category_t23
    };

    private List<RecommendInfo.ResultBean.BodyBean> datas;

    public HomeRecommendedSection(Context context, String type, List<RecommendInfo.ResultBean.BodyBean> datas) {
//        super(R.layout.layout_home_recommend_head, R.layout.layout_home_recommend_item, R.layout.layout_home_recommend_foot);
        super(SectionParameters.builder()
                .itemResourceId(R.layout.layout_home_recommend_item)
                .headerResourceId(R.layout.layout_home_recommend_head)
                .footerResourceId(R.layout.layout_home_recommend_foot)
                .build());
        this.mContext = context;
        this.type = type;
        this.datas = datas;
    }

    @Override
    public int getContentItemsTotal() {
        return datas.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Log.d(TAG, String.valueOf(itemViewHolder.mVideoImg == null));
        RecommendInfo.ResultBean.BodyBean bodyBean = datas.get(position);
//        if (itemViewHolder.mVideoImg != null) {
            Glide.with(mContext)
                    .load(Uri.parse(bodyBean.getCover()))
                    .placeholder(R.drawable.bili_loading)
                    .into(itemViewHolder.mVideoImg);

//        Log.d(TAG, bodyBean.toString());
        itemViewHolder.mVideoTitle.setText(bodyBean.getTitle());
        itemViewHolder.mVideoPlayNum.setText(bodyBean.getPlay());
        itemViewHolder.mVideoReviewCount.setText(bodyBean.getDanmaku());

    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.mType.setText(type);
    }

    /**
     * 根据title设置typeIcon
     */
    private void setTypeIcon(HeaderViewHolder headerViewHolder) {
        switch (type) {
            case "热门焦点":
                headerViewHolder.mTypeImg.setImageResource(icons[0]);
                break;
            case "正在直播":
                headerViewHolder.mTypeImg.setImageResource(icons[1]);
                break;
            case "番剧推荐":
                headerViewHolder.mTypeImg.setImageResource(icons[2]);
                break;
            case "动画区":
                headerViewHolder.mTypeImg.setImageResource(icons[3]);
                break;
            case "音乐区":
                headerViewHolder.mTypeImg.setImageResource(icons[4]);
                break;
            case "舞蹈区":
                headerViewHolder.mTypeImg.setImageResource(icons[5]);
                break;
            case "游戏区":
                headerViewHolder.mTypeImg.setImageResource(icons[6]);
                break;
            case "鬼畜区":
                headerViewHolder.mTypeImg.setImageResource(icons[7]);
                break;
            case "科技区":
                headerViewHolder.mTypeImg.setImageResource(icons[8]);
                break;
            case "生活区":
                headerViewHolder.mTypeImg.setImageResource(icons[9]);
                break;
            case "时尚区":
                headerViewHolder.mTypeImg.setImageResource(icons[10]);
                break;
            case "娱乐区":
                headerViewHolder.mTypeImg.setImageResource(icons[11]);
                break;
            case "电视剧区":
                headerViewHolder.mTypeImg.setImageResource(icons[12]);
                break;
            case "电影区":
                headerViewHolder.mTypeImg.setImageResource(icons[13]);
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder getFooterViewHolder(View view) {
        return new FooterViewHolder(view);
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        super.onBindFooterViewHolder(holder);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        //        CardView mCardView;
        ImageView mVideoImg;
        TextView mVideoTitle;
        TextView mVideoPlayNum;
        TextView mVideoReviewCount;
//        RelativeLayout mLiveLayout;
//        LinearLayout mVideoLayout;
//        TextView mLiveUp;
//        TextView mLiveOnLine;
//        RelativeLayout mBangumiLayout;
//        TextView mBangumiUpdate;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideoImg = itemView.findViewById(R.id.item_video_image_view);
            mVideoTitle = itemView.findViewById(R.id.item_video_play_title);
            mVideoPlayNum = itemView.findViewById(R.id.item_video_play_number);
            mVideoReviewCount = itemView.findViewById(R.id.item_video_comment_number);
        }
    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView mTypeImg;
        TextView mType;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            mTypeImg = itemView.findViewById(R.id.item_type_img);
            mType = itemView.findViewById(R.id.item_type_tv);
        }
    }
}
