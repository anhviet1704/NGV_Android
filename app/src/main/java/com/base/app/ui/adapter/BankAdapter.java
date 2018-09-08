package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.WorkItem;
import com.base.app.ui.callback.OnClickItem;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.MyViewHolder> {

    private List<WorkItem> mWorkItems;
    private Context context;
    private OnClickItem clickItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        MagicTextView tvName;
        @BindView(R.id.tv_number)
        MagicTextView tvNumber;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public BankAdapter(Context context, List<WorkItem> mWorkItems, OnClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
        this.mWorkItems = mWorkItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bank, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        WorkItem mItem = mWorkItems.get(position);
        holder.tvName.setText(mItem.getValue());
        holder.tvNumber.setText(mItem.getValue());
        holder.ivIcon.setImageResource(R.drawable.ic_input_small);
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

    public void onUpdateData(List<WorkItem> mWorkItems) {
        this.mWorkItems = mWorkItems;
        notifyDataSetChanged();
    }
}