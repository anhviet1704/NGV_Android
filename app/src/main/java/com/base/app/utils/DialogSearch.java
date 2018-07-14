package com.base.app.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.app.R;
import com.base.app.ui.adapter.SearchAdapter;
import com.base.app.ui.callback.OnClickSearch;
import com.blankj.utilcode.util.ScreenUtils;
import com.ivankocijan.magicviews.views.MagicEditText;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DialogSearch<T> {

    private Context mContext;
    private Dialog mDialog;
    private List<T> mDatasSearch = new ArrayList<>();
    private SearchAdapter mSearchAdapter;
    MagicTextView mTvClose;

    public DialogSearch(Context context) {
        mContext = context;
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog != null) {
            //mViewLoading.clearAnimation();
            mDialog.dismiss();
        }
    }

    public void setDataSearch(List<T> data) {
        mDatasSearch = data;
        mSearchAdapter.onUpdateData(mDatasSearch);
    }

    @SuppressLint("CheckResult")
    public void onShowSearch(final OnClickSearch mClick) {
        int width = ScreenUtils.getScreenWidth();
        int height = ScreenUtils.getScreenHeight();
        mDialog = new Dialog(mContext, R.style.AppThemeNoToolBar);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_search, null);
        ViewGroup.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;// - BarUtils.getStatusBarHeight();
        view.setLayoutParams(params);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        mTvClose = mDialog.findViewById(R.id.tv_close);
        MagicEditText mEtSearch = mDialog.findViewById(R.id.et_seach);
        final ViewGroup mViewRoot = mDialog.findViewById(R.id.view_root);
        RecyclerView mRvSearch = mDialog.findViewById(R.id.rv_search);
        mSearchAdapter = new SearchAdapter(mContext, new OnClickSearch() {
            @Override
            public void onClickItem(View v, String job_id) {
                mClick.onClickItem(v, job_id);
                dismiss();
            }

        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRvSearch.setLayoutManager(mLayoutManager);
        mRvSearch.setItemAnimator(new DefaultItemAnimator());
        mRvSearch.setAdapter(mSearchAdapter);
        RxTextView.textChanges(mEtSearch)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mSearchAdapter.getFilter().filter(charSequence);
                    }
                });
        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }


}
