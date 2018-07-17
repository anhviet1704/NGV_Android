package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.app.R;
import com.base.app.model.JobCurrentItem;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.VectorDrawableUtils;
import com.github.vipulasri.timelineview.TimelineView;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

    private List<JobCurrentItem> mDatas;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnClickItem mClick;

    public TimeLineAdapter(List<JobCurrentItem> datas, OnClickItem Click) {
        if (datas == null) {
            mDatas = new ArrayList<>();
        } else
            mDatas = datas;
        mClick = Click;
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
        JobCurrentItem item = mDatas.get(position);
        holder.timeMarker.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker));
        holder.tvTime.setText(item.getStartTime());//not format
        holder.tvName.setText(item.getName());
        holder.tvAddress.setText(String.format(mContext.getResources().getString(R.string.tv_work_025), item.getAddress()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickItem(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mDatas != null ? mDatas.size() : 0);
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

    public void onUpdateData(List<JobCurrentItem> items) {
        this.mDatas = items;
        notifyDataSetChanged();
    }
}
