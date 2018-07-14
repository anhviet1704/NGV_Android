package com.base.app.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.joblasted.JobNewDetailItem;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;
import com.jaychang.srv.Updatable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobNewCell extends SimpleCell<JobNewDetailItem, JobNewCell.ViewHolder> implements Updatable<JobNewDetailItem> {


    public JobNewCell(JobNewDetailItem item) {
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
    protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
        /*if (payload != null) {
            // payload from updateCell()
            if (payload instanceof JobLastDetailItem) {
                holder.textView.setText(((JobLastDetailItem) payload).getTitle());
            }
            // payloads from updateCells()
            if (payload instanceof ArrayList) {
                List<JobLastDetailItem> payloads = ((ArrayList<JobLastDetailItem>) payload);
                holder.textView.setText(payloads.get(position).getTitle());
            }
            // payload from addOrUpdate()
            if (payload instanceof Bundle) {
                Bundle bundle = ((Bundle) payload);
                for (String key : bundle.keySet()) {
                    if (KEY_TITLE.equals(key)) {
                        holder.textView.setText(bundle.getString(key));
                    }
                }
            }
            return;
        }

        holder.textView.setText(getItem().getTitle());

        if (showHandle) {
            holder.dragHandle.setVisibility(View.VISIBLE);
        } else {
            holder.dragHandle.setVisibility(View.GONE);
        }*/
    }

    // Optional
    @Override
    protected void onUnbindViewHolder(ViewHolder holder) {
        // do your cleaning jobs here when the item view is recycled.
    }


    @Override
    protected long getItemId() {
        return 0;
        //return getItem().getId();
    }


    @Override
    public boolean areContentsTheSame(@NonNull JobNewDetailItem newItem) {
        return true;
        //return getItem().getTitle().equals(newItem.getTitle());
    }

    /**
     * If getItem() is the same as newItem (i.e. their return value of getItemId() are the same)
     * and areContentsTheSame()  return false, then the cell need to be updated,
     * onBindViewHolder() will be called with this payload object.
     */
    @Override
    public Object getChangePayload(JobNewDetailItem newItem) {
        Bundle bundle = new Bundle();
        //bundle.putString(KEY_TITLE, newItem.getTitle());
        return bundle;
    }

    public static class ViewHolder extends SimpleViewHolder {
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
