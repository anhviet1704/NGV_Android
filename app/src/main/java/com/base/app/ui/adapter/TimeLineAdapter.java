package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.app.R;
import com.base.app.model.WorkItem;
import com.base.app.utils.VectorDrawableUtils;
import com.github.vipulasri.timelineview.TimelineView;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

    private List<WorkItem> mFeedList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<WorkItem> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.row_timeline, parent, false);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        WorkItem item = mFeedList.get(position);
        holder.timeMarker.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker));
        holder.tvTime.setText("ngah hom nay");
        holder.tvName.setText("ngah hom nay");
        holder.tvAddress.setText("ngah hom nay");
    }

    @Override
    public int getItemCount() {
        return (mFeedList != null ? mFeedList.size() : 0);
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.time_marker)
        TimelineView timeMarker;
        @BindView(R.id.tv_time)
        MagicTextView tvTime;
        @BindView(R.id.tv_name)
        MagicTextView tvName;
        @BindView(R.id.tv_address)
        MagicTextView tvAddress;

        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            timeMarker.initLine(viewType);
        }
    }
}
