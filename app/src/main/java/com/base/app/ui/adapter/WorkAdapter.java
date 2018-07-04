package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.WorkItem;
import com.base.app.model.joblasted.JobLastDetailItem;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.NGVUtils;
import com.bumptech.glide.Glide;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.MyViewHolder> {

    private List<JobLastDetailItem> mWorkItems;
    private Context context;
    private OnClickItem clickItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
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

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public WorkAdapter(Context context, List<JobLastDetailItem> mWorkItems, OnClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
        if (mWorkItems == null) {
            this.mWorkItems = new ArrayList<>();
        } else {
            this.mWorkItems = mWorkItems;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_work, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        JobLastDetailItem mWorkItem = mWorkItems.get(position);
        holder.tvName.setText(mWorkItem.getName());
        holder.tvPrice.setText(mWorkItem.getFee());
        holder.tvTime.setText(mWorkItem.getDiffTime() + "|");
        holder.tvDistrict.setText(mWorkItem.getDistrict());
        String url = "https://camo.mybb.com/e01de90be6012adc1b1701dba899491a9348ae79/687474703a2f2f7777772e6a71756572797363726970742e6e65742f696d616765732f53696d706c6573742d526573706f6e736976652d6a51756572792d496d6167652d4c69676874626f782d506c7567696e2d73696d706c652d6c69676874626f782e6a7067";
        Glide.with(context).load(url).apply(NGVUtils.onGetRound(6)).into(holder.ivDes);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWorkItems.size();
    }

    public void onUpdateData(List<JobLastDetailItem> mWorkItems) {
        this.mWorkItems = mWorkItems;
        notifyDataSetChanged();
    }
}