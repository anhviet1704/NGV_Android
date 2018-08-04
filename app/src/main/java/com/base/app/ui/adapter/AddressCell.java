package com.base.app.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.base.app.R;
import com.base.app.automap.PlaceItem;
import com.base.app.automap.PlaceItem;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;
import com.jaychang.srv.Updatable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressCell extends SimpleCell<PlaceItem, AddressCell.ViewHolder> implements Updatable<PlaceItem> {


    public AddressCell(PlaceItem item) {
        super(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.row_address;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
        return new ViewHolder(cellView);
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, final int position, Context context, Object payload) {
        PlaceItem item = getItem();
        holder.tvTitle.setText(item.getName());
        holder.tvDes.setText(item.getDes());
    }

    @Override
    protected void onUnbindViewHolder(ViewHolder holder) {
    }

    @Override
    public boolean areContentsTheSame(@NonNull PlaceItem newItem) {
        return getItem().toString().equals(newItem.toString());
    }

    @Override
    public Object getChangePayload(@NonNull PlaceItem newItem) {
        return null;
    }

    static class ViewHolder extends SimpleViewHolder {
        @BindView(R.id.tv_title)
        MagicTextView tvTitle;
        @BindView(R.id.tv_des)
        MagicTextView tvDes;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
