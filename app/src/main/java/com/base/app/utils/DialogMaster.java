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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.model.BaseValueItem;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.ui.adapter.SearchAdapter;
import com.base.app.ui.callback.OnClickMaster;
import com.base.app.ui.callback.OnClickSearch;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.ivankocijan.magicviews.views.MagicEditText;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class DialogMaster<T> {

    private Context mContext;
    private Dialog mDialog;
    private List<T> mDatas = new ArrayList<>();
    private String mTitle;
    private MagicTextView mTvTitle;
    private SearchAdapter mAdapter;
    ImageView mIvClose;
    View mViewLoading;
    View mViewSearch;
    RecyclerView mRvMasterData;

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
        mViewLoading.setVisibility(View.GONE);
        mRvMasterData.setVisibility(View.VISIBLE);
    }


    public void setTitle(String title) {
        mTitle = title;
        mTvTitle.setText(mTitle);
    }

    public void onShowMasterData(final OnClickSearch mClick) {
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
        mViewLoading = mDialog.findViewById(R.id.view_loading);
        mViewSearch = mDialog.findViewById(R.id.view_search);
        mRvMasterData = mDialog.findViewById(R.id.rv_masterdata);
        MagicEditText mEtSearch = mDialog.findViewById(R.id.et_seach);
        MagicTextView mTvClose = mDialog.findViewById(R.id.tv_close);
        ImageView mIvSearch = mDialog.findViewById(R.id.iv_search);
        mAdapter = new SearchAdapter(mContext, new OnClickSearch() {
            @Override
            public void onClickItem(View v, Object object) {
                mClick.onClickItem(v, object);
                mEtSearch.setText("");
                KeyboardUtils.hideSoftInput(mEtSearch);
                mDialog.dismiss();
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
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewSearch.setVisibility(View.VISIBLE);
                KeyboardUtils.showSoftInput(mEtSearch);
            }
        });
        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
                mViewSearch.setVisibility(View.GONE);
                KeyboardUtils.hideSoftInput(mEtSearch);
            }
        });

        RxTextView.textChanges(mEtSearch)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mAdapter.getFilter().filter(charSequence);
                    }
                });
    }


}
