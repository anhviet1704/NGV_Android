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

public class JobRegisterAdapter extends RecyclerView.Adapter<JobRegisterAdapter.MyViewHolder> {

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

    public JobRegisterAdapter(Context context, List<JobCurrentItem> mJobCurrentItems, OnClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
        this.mItems = mJobCurrentItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_job_register, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        JobCurrentItem mJobCurrentItem = mItems.get(position);
        holder.tvName.setText(mJobCurrentItem.getName());
        String date[] = mJobCurrentItem.getCreatedAt().split(" ");
        holder.tvTime.setText(String.format(context.getResources().getString(R.string.tv_work_026), date[0]));
        String url = "";
        try {
            url = mJobCurrentItem.getJobImg().get(0).getValue();
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