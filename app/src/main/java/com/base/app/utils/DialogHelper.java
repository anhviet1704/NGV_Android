package com.base.app.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.constraint.Group;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.OsinItem;
import com.base.app.ui.callback.OnClickDialog;
import com.base.app.ui.callback.OnClickFinish;
import com.base.app.ui.callback.OnClickMaster;
import com.base.app.ui.callback.OnClickRegisterJob;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicEditText;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


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
        //mDatas = data;
        mAdapter.onUpdateData(data);
    }


    public void setTitle(String title) {
        mTitle = title;
        mTvTitle.setText(mTitle);
    }

    public void onShowUserInfo(ViewGroup root, OsinItem osinItem, final OnClickDialog mclick) {
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
        MagicButton viewClose = mDialog.findViewById(R.id.viewClose);
        RecyclerView rvWork = mDialog.findViewById(R.id.rv_work);
        BlurView mBlurView = mDialog.findViewById(R.id.bottomBlurView);
        mBlurView.setupWith(root)
                //.windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(mContext))
                .blurRadius(10f)
                .setHasFixedTransformationMatrix(true);
        tvName.setText(osinItem.getOsinFullName());
        tvStatus.setText(osinItem.getRoleName());
        tvBirthday.setText(osinItem.getBirthday());
        Glide.with(mContext).load(osinItem.getAvatar()).apply(NGVUtils.onGetRound(6).placeholder(R.drawable.ic_avatar)).into(ivAvatar);
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
                mclick.onClicClose();
            }
        });
    }

    public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.MyViewHolder> {

        OnClickMaster onClick;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public MagicTextView mTvName;

            public MyViewHolder(View view) {
                super(view);
                mTvName = view.findViewById(R.id.tv_name);
            }
        }

        public MasterAdapter(OnClickMaster click) {
            onClick = click;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_job_history, parent, false);
            return new MyViewHolder(itemView);
        }

        @SuppressLint("StringFormatMatches")
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            if (mDatas != null && mDatas.size() > 0) {
                if (mDatas.get(0) instanceof JobCurrentItem) {
                    JobCurrentItem item = (JobCurrentItem) mDatas.get(position);
                    holder.mTvName.setText(Html.fromHtml(String.format(mContext.getResources().getString(R.string.tv_user_008, item.getName()))));
                }
                /*holder.mViewRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClick.onClickItem(position);
                        mDialog.dismiss();
                    }
                });*/

            }

        }

        public void onUpdateData(List<T> datas) {
            mDatas.clear();
            mDatas = datas;
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

    public void onShowRegisterJob(ViewGroup root, final OnClickRegisterJob mClick) {
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
        BlurView mBlurView = mDialog.findViewById(R.id.bottomBlurView);
        mBlurView.setupWith(root)
                .blurAlgorithm(new RenderScriptBlur(mContext))
                .blurRadius(10f)
                .setHasFixedTransformationMatrix(true);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickRegister(etSumPrice.getText().toString());
            }
        });


    }

    public void onShowDialogConfirm(ViewGroup root, final OnClickFinish mClick) {
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
        BlurView mBlurView = mDialog.findViewById(R.id.bottomBlurView);
        mBlurView.setupWith(root)
                //.windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(mContext))
                .blurRadius(10f)
                .setHasFixedTransformationMatrix(true);

        mBtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onClickItem();
            }
        });
        mBtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    public void onShowDialogFinish(ViewGroup root, final OnClickFinish mClick) {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_job_start, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);

        ImageView mIvClose = mDialog.findViewById(R.id.iv_close);
        RoundRectView mViewCall = mDialog.findViewById(R.id.view_call);
        RoundRectView mViewFindStreet = mDialog.findViewById(R.id.view_find_street);
        RoundRectView mViewTime = mDialog.findViewById(R.id.view_time);
        MagicTextView mTvTime = mDialog.findViewById(R.id.tv_time_job);
        ImageView mIvFinish = mDialog.findViewById(R.id.iv_finish);
        MagicTextView mTvStatus = mDialog.findViewById(R.id.tv_pass);
        MagicTextView mTvCancel = mDialog.findViewById(R.id.tv_cancel);
        Group mGrFinish = mDialog.findViewById(R.id.gr_finish);
        MagicButton btFinish = mDialog.findViewById(R.id.bt_finish);
        BlurView mBlurView = mDialog.findViewById(R.id.bottomBlurView);
        mBlurView.setupWith(root)
                //.windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(mContext))
                .blurRadius(10f)
                .setHasFixedTransformationMatrix(true);


        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }


}
