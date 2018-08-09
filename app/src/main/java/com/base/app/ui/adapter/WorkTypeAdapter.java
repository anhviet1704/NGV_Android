package com.base.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.CategoryItem;
import com.base.app.ui.callback.OnClickItem;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkTypeAdapter extends RecyclerView.Adapter<WorkTypeAdapter.MyViewHolder> {

    private List<CategoryItem> mRoleItems;
    private Context context;
    private OnClickItem clickItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.tv_name)
        MagicTextView tvName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public WorkTypeAdapter(Context context, List<CategoryItem> mRoleItems, OnClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
        this.mRoleItems = mRoleItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_work_type, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CategoryItem mItem = mRoleItems.get(position);
        holder.tvName.setText(mItem.getName());
        if (mItem.isCheck()) {
            holder.ivCheck.setImageResource(R.drawable.ic_check);
        } else {
            holder.ivCheck.setImageResource(R.drawable.ic_uncheck);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoleItems.size();
    }

    public void onUpdateData(List<CategoryItem> mRoleItems) {
        this.mRoleItems = mRoleItems;
        notifyDataSetChanged();
    }
}