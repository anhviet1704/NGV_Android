package com.base.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.base.app.R;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobCell;
import com.base.app.ui.adapter.SearchAdapter;
import com.base.app.ui.callback.OnClickSearch;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.ivankocijan.magicviews.views.MagicEditText;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DialogSearch<T> {

    private Activity mContext;
    private Dialog mDialog;
    private List<T> mDatasSearch = new ArrayList<>();
    private SearchAdapter mSearchAdapter;
    MagicTextView mTvClose;
    WorkListFragmentVM mViewModel;
    private LoginItem mLoginItem;
    PrefHelper mPrefHelper;

    public DialogSearch(Activity context, WorkListFragmentVM viewModel, LoginItem loginItem, PrefHelper prefHelper) {
        mContext = context;
        mLoginItem = loginItem;
        mViewModel = viewModel;
        mPrefHelper = prefHelper;
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
        View mViewLoading = mDialog.findViewById(R.id.view_loading);
        RecyclerView mRvSearch = mDialog.findViewById(R.id.rv_search);
        mSearchAdapter = new SearchAdapter(mContext, new OnClickSearch() {
            @Override
            public void onClickItem(View v, Object object) {
                mClick.onClickItem(v, object);
                KeyboardUtils.hideSoftInput(mEtSearch);
                //dismiss();
            }

        });
        KeyboardUtils.showSoftInput(mEtSearch);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRvSearch.setLayoutManager(mLayoutManager);
        mRvSearch.setItemAnimator(new DefaultItemAnimator());
        mRvSearch.setAdapter(mSearchAdapter);
        /*RxTextView.textChanges(mEtSearch)
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mSearchAdapter.getFilter().filter(charSequence);
                    }
                });*/
        mEtSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    mViewLoading.setVisibility(View.VISIBLE);
                    mRvSearch.setVisibility(View.GONE);
                    mViewModel.getMaidJobSearch(mLoginItem.getId(), 30, mEtSearch.getText().toString()).observe((LifecycleOwner) mContext, new Observer<ResponseObj<JobNewResponse>>() {
                        @Override
                        public void onChanged(@Nullable ResponseObj<JobNewResponse> response) {
                            if (response.getResponse() == Response.SUCCESS) {
                                if (response.getObj().getData().size() > 0) {
                                    mSearchAdapter.onUpdateOnlineData(response.getObj().getData());
                                    KeyboardUtils.hideSoftInput(mEtSearch);
                                }
                                mViewLoading.setVisibility(View.GONE);
                                mRvSearch.setVisibility(View.VISIBLE);
                            } else if (response.getResponse() == Response.UNAUTHORIZED) {
                                NGVUtils.showAuthorized(mContext, MainActivity.mViewRoot, mPrefHelper);
                            }
                        }
                    });
                    return true;
                }
                return false;
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
