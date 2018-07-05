package com.base.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.app.R;
import com.blankj.utilcode.util.ScreenUtils;
import com.github.florent37.shapeofview.shapes.RoundRectView;


public class DialogLoading {

    private Dialog mDialog;
    private RoundRectView mViewLoading;

    public DialogLoading(Context mContext, View root) {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_error, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        mViewLoading = (RoundRectView) mDialog.findViewById(R.id.view_loading);
        //BlurringView mBlurLayout = (BlurringView) mDialog.findViewById(R.id.blurring_view);
        //mBlurLayout.setBlurredView(root);
    }

    public void show() {
        if (mDialog != null) {
            if (mViewLoading != null) {
                mViewLoading.setVisibility(View.VISIBLE);
                mViewLoading.setAlpha(0.f);
                mViewLoading.setScaleX(0.f);
                mViewLoading.setScaleY(0.f);
                mViewLoading.animate()
                        .alpha(1.f)
                        .scaleX(1.f).scaleY(1.f)
                        .setDuration(350)
                        .start();
            }
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            mViewLoading.clearAnimation();
            mDialog.dismiss();
        }
    }

}
