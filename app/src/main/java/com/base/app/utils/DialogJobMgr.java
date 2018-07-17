package com.base.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.constraint.Group;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.ui.callback.OnClickFinish;
import com.blankj.utilcode.util.ScreenUtils;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicTextView;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


public class DialogJobMgr<T> {

    ImageView mIvClose;
    ImageView mIvFinish;
    Group mGrFinish;
    MagicButton btFinish;
    MagicTextView mTvCancel;
    MagicTextView mTvFinish;
    private Context mContext;
    private Dialog mDialog;

    public DialogJobMgr(Context context) {
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
        mIvFinish = mDialog.findViewById(R.id.iv_finish);
        MagicTextView mTvStatus = mDialog.findViewById(R.id.tv_pass);
        mTvFinish = mDialog.findViewById(R.id.tv_finish);
        mTvCancel = mDialog.findViewById(R.id.tv_cancel);
        mGrFinish = mDialog.findViewById(R.id.gr_finish);
        btFinish = mDialog.findViewById(R.id.bt_finish);
        BlurView mBlurView = mDialog.findViewById(R.id.bottomBlurView);
        mBlurView.setupWith(root)
                //.windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(mContext))
                .blurRadius(8f)
                .setHasFixedTransformationMatrix(true);
        mIvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickFinish();
                mIvFinish.setVisibility(View.INVISIBLE);
                mTvFinish.setVisibility(View.INVISIBLE);
                mTvCancel.setVisibility(View.INVISIBLE);
                mGrFinish.setVisibility(View.VISIBLE);
                btFinish.setVisibility(View.VISIBLE);
            }
        });
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickRate();
            }
        });
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClickCancel();
            }
        });
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    public void onUpdateUI(int type) {
        if (type == 1) {
            // show view not start
            mIvFinish.setImageResource(R.drawable.ic_not_finish);
            mGrFinish.setVisibility(View.GONE);
            btFinish.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.VISIBLE);
            mTvFinish.setVisibility(View.VISIBLE);
        } else if (type == 2) {
            //show view start
            mIvFinish.setImageResource(R.drawable.ic_finish);
            mGrFinish.setVisibility(View.GONE);
            btFinish.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.VISIBLE);
            mTvFinish.setVisibility(View.VISIBLE);
        } else {
            //show rate
            mIvFinish.setVisibility(View.INVISIBLE);
            mTvCancel.setVisibility(View.INVISIBLE);
            mTvFinish.setVisibility(View.INVISIBLE);
            mGrFinish.setVisibility(View.VISIBLE);
            btFinish.setVisibility(View.VISIBLE);
        }
    }


}
