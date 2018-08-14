package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.OsinItem;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.AppCons;
import com.base.app.utils.NGVUtils;
import com.bumptech.glide.Glide;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkUserAdapter extends RecyclerView.Adapter<WorkUserAdapter.MyViewHolder> {

    private List<OsinItem> mOsinItems;
    private Context context;
    private OnClickItem clickItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name)
        MagicTextView tvName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public WorkUserAdapter(Context context, List<OsinItem> mOsinItems, OnClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
        if (mOsinItems == null) {
            this.mOsinItems = new ArrayList<>();
        } else {
            this.mOsinItems = mOsinItems;
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_work_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        OsinItem mOsinItem = mOsinItems.get(position);
        holder.tvName.setText(mOsinItem.getOsinFullName());
        Glide.with(context).load(AppCons.HOST_URL + mOsinItem.getAvatar()).apply(NGVUtils.onGetCircleCrop().placeholder(R.drawable.ic_avatar)).into(holder.ivAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOsinItems.size();
    }

    public void onUpdateData(List<OsinItem> mWorkItems) {
        this.mOsinItems = mWorkItems;
        notifyDataSetChanged();
    }
}