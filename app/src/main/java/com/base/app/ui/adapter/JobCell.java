package com.base.app.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.utils.AppCons;
import com.base.app.utils.NGVUtils;
import com.bumptech.glide.Glide;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;
import com.jaychang.srv.Updatable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobCell extends SimpleCell<JobNewItem, JobCell.ViewHolder> implements Updatable<JobNewItem> {


    public JobCell(JobNewItem item) {
        super(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.row_work;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
        return new ViewHolder(cellView);
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, final int position, Context context, Object payload) {
        JobNewItem mWorkItem = getItem();
        holder.tvName.setText(mWorkItem.getJobName());
        holder.tvPrice.setText(NGVUtils.formatCurrency(context, mWorkItem.getFee()));
        holder.tvTime.setText(NGVUtils.onCaculatorDate(context, mWorkItem.getCreatedAt()) + " |");
        holder.tvDistrict.setText(mWorkItem.getDistrict());
        String url = "";
        try {
            url = mWorkItem.getJobImg().get(0).getValue();
        } catch (Exception e) {
        }
        Glide.with(context).load(AppCons.HOST_URL + url).apply(NGVUtils.onGetRound(6).placeholder(R.drawable.img_picture)).into(holder.ivDes);
    }

    @Override
    protected void onUnbindViewHolder(ViewHolder holder) {
    }

    @Override
    public boolean areContentsTheSame(@NonNull JobNewItem newItem) {
        return getItem().toString().equals(newItem.toString());
    }

    @Override
    public Object getChangePayload(@NonNull JobNewItem newItem) {
        return null;
    }

    static class ViewHolder extends SimpleViewHolder {
        @BindView(R.id.iv_des)
        ImageView ivDes;
        @BindView(R.id.tv_name)
        MagicTextView tvName;
        @BindView(R.id.tv_price)
        MagicTextView tvPrice;
        @BindView(R.id.tv_time)
        MagicTextView tvTime;
        @BindView(R.id.tv_district)
        MagicTextView tvDistrict;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
