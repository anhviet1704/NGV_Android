package com.base.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.BaseValueItem;
import com.base.app.ui.callback.OnClickMaster;
import com.blankj.utilcode.util.ScreenUtils;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;


public class DialogMaster<T> {

    private Context mContext;
    private Dialog mDialog;
    private List<T> mDatas = new ArrayList<>();
    private String mTitle;
    private MagicTextView mTvTitle;
    private MasterAdapter mAdapter;
    ImageView mIvClose;

    public DialogMaster(Context context) {
        mContext = context;
    }

    public void show() {
        if (mDialog != null) {
            if (mIvClose != null) {
                mIvClose.setVisibility(View.VISIBLE);
                mIvClose.setAlpha(0.5f);
                mIvClose.setRotation(45f);
                mIvClose.animate()
                        .alpha(1.f)
                        .rotation(90f)
                        .setDuration(250)
                        .start();
            }

            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            //mViewLoading.clearAnimation();
            mDialog.dismiss();
        }
    }

    public void setData(List<T> data) {
        mDatas = data;
        mAdapter.onUpdateData(mDatas);
    }


    public void setTitle(String title) {
        mTitle = title;
        mTvTitle.setText(mTitle);
    }

    public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.MyViewHolder> {

        OnClickMaster onClick;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public MagicTextView mTvName;
            public ConstraintLayout mViewRoot;

            public MyViewHolder(View view) {
                super(view);
                mTvName = view.findViewById(R.id.tv_name);
                mViewRoot = view.findViewById(R.id.view_root);
            }
        }


        public MasterAdapter(OnClickMaster click) {
            onClick = click;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_master_data, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            if (mDatas != null && mDatas.size() > 0) {
                if (mDatas.get(0) instanceof BaseValueItem) {
                    BaseValueItem item = (BaseValueItem) mDatas.get(position);
                    holder.mTvName.setText(item.getValue());
                } else if (mDatas.get(0) instanceof BaseValueItem) {
                    BaseValueItem item = (BaseValueItem) mDatas.get(position);
                    holder.mTvName.setText(item.getValue());
                }
                holder.mViewRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.onClickItem(position);
                        mDialog.dismiss();
                    }
                });

            }

        }

        public void onUpdateData(List<T> datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    public void onShowMasterData(final OnClickMaster mClick) {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_master, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        mTvTitle = mDialog.findViewById(R.id.tv_title);
        final ViewGroup mViewRoot = mDialog.findViewById(R.id.view_root);
        mIvClose = mDialog.findViewById(R.id.iv_close);
        RecyclerView mRvMasterData = mDialog.findViewById(R.id.rv_masterdata);
        mAdapter = new MasterAdapter(new OnClickMaster() {
            @Override
            public void onClickItem(int pos) {
                mClick.onClickItem(pos);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRvMasterData.setLayoutManager(mLayoutManager);
        mRvMasterData.setItemAnimator(new DefaultItemAnimator());
        mRvMasterData.setAdapter(mAdapter);
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }


}
