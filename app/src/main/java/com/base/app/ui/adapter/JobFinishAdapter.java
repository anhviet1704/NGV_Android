package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.JobCurrentItem;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.NGVUtils;
import com.bumptech.glide.Glide;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobFinishAdapter extends RecyclerView.Adapter<JobFinishAdapter.MyViewHolder> {

    private List<JobCurrentItem> mItems;
    private Context context;
    private OnClickItem clickItem;

    public void onUpdateData(List<JobCurrentItem> dataList) {
        mItems = dataList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_des)
        ImageView ivDes;
        @BindView(R.id.tv_name)
        MagicTextView tvName;
        @BindView(R.id.tv_time)
        MagicTextView tvTime;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public JobFinishAdapter(Context context, List<JobCurrentItem> mWorkItems, OnClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
        this.mItems = mWorkItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_job_finish, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        JobCurrentItem mWorkItem = mItems.get(position);
        holder.tvName.setText(mWorkItem.getName());
        holder.tvTime.setText(mWorkItem.getStartTime());
        String url = "";
        try {
            url = mWorkItem.getJobImg().get(0).getValue();
        } catch (Exception e) {
        }
        Glide.with(context).load(url).apply(NGVUtils.onGetRound(6).placeholder(R.drawable.img_picture)).into(holder.ivDes);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}