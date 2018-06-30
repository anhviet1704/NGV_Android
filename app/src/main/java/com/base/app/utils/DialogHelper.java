package com.base.app.utils;

import android.annotation.SuppressLint;
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
import com.base.app.model.CountryItem;
import com.base.app.ui.adapter.SearchAdapter;
import com.base.app.ui.callback.OnClickFinish;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.ui.callback.OnClickMaster;
import com.blankj.utilcode.util.ScreenUtils;
import com.fivehundredpx.android.blur.BlurringView;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicEditText;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DialogHelper<T> {

    private Context mContext;
    private Dialog mDialog;
    private List<T> mDatas = new ArrayList<>();
    private String mTitle;
    private MagicTextView mTvTitle;
    private MasterAdapter mAdapter;
    ImageView mIvClose;

    public DialogHelper(Context context) {
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
                } else if (mDatas.get(0) instanceof CountryItem) {
                    CountryItem item = (CountryItem) mDatas.get(position);
                    holder.mTvName.setText(item.getName());
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


    public void onShowUserInfo() {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_user, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        ImageView ivAvatar = mDialog.findViewById(R.id.iv_avatar);
        MagicTextView tvName = mDialog.findViewById(R.id.tv_name);
        MagicTextView tvStatus = mDialog.findViewById(R.id.tv_status);
        MagicTextView tvBirthday = mDialog.findViewById(R.id.tv_birthday);
        RoundRectView viewClose = mDialog.findViewById(R.id.view_close);
        RecyclerView rvWork = mDialog.findViewById(R.id.rv_work);

        mAdapter = new MasterAdapter(new OnClickMaster() {
            @Override
            public void onClickItem(int pos) {
                //mClick.onClickItem(pos);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvWork.setLayoutManager(mLayoutManager);
        rvWork.setItemAnimator(new DefaultItemAnimator());
        rvWork.setAdapter(mAdapter);
        viewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    public void onShowRegisterJob(final OnClickFinish mClick) {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_register_job, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        ImageView ivClose = mDialog.findViewById(R.id.iv_close);
        MagicTextView tvWorkTime = mDialog.findViewById(R.id.tv_work_time);
        MagicTextView tvTime = mDialog.findViewById(R.id.tv_time);
        MagicTextView tvJobName = mDialog.findViewById(R.id.tv_job_name);
        MagicTextView tvJobPlace = mDialog.findViewById(R.id.tv_job_place);
        MagicTextView tvPriceJob = mDialog.findViewById(R.id.tv_price_job);
        MagicTextView tvTimeJob = mDialog.findViewById(R.id.tv_time_job);
        MagicEditText etSumPrice = mDialog.findViewById(R.id.et_sum_price);
        MagicButton btFinish = mDialog.findViewById(R.id.bt_finish);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickItem();
                mDialog.dismiss();
            }
        });


    }

    public void onShowDialogConfirm(View root, final OnClickFinish mClick) {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_confirm, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);

        MagicTextView mBtYes = mDialog.findViewById(R.id.bt_yes);
        MagicTextView mBtNo = mDialog.findViewById(R.id.bt_no);
        BlurringView mBlurLayout = mDialog.findViewById(R.id.blurring_view);
        mBlurLayout.setBlurredView(root);

        mBtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onClickItem();
                mDialog.dismiss();
            }
        });
        mBtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mBlurLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


}
