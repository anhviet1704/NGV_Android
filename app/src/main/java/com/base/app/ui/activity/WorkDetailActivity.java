package com.base.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityWorkDetailBinding;
import com.base.app.model.WorkItem;
import com.base.app.ui.adapter.ViewpagerWorkAdapter;
import com.base.app.ui.adapter.WorkUserAdapter;
import com.base.app.ui.callback.OnClickFinish;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.DialogHelper;
import com.base.app.utils.SpacesItemDecoration;
import com.base.app.viewmodel.WorkDetailActivityVM;
import com.blankj.utilcode.util.ConvertUtils;

import java.util.ArrayList;
import java.util.List;


public class WorkDetailActivity extends BaseActivity<WorkDetailActivityVM, ActivityWorkDetailBinding> {

    private List<String> listItem = new ArrayList<>();
    private ViewpagerWorkAdapter fragmentAdapter;
    private WorkUserAdapter mWorkUserAdapter;
    private List<WorkItem> mWorkItems;
    private boolean isAlreadyRegister = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_work_detail;
    }

    @Override
    protected Class<WorkDetailActivityVM> getViewModel() {
        return WorkDetailActivityVM.class;
    }

    @Override
    protected void onCreate(Bundle instance, final WorkDetailActivityVM viewModel) {
        /*bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//*viewModel.updatePassWord(bind.etPassword.getText().toString()).observe(NewPassActivity.this, new Observer<ApiResponse<SchoolYearItem>>() {
                    @Override
                    public void onChanged(@Nullable ApiResponse<SchoolYearItem> schoolYearItemApiResponse) {
                        Intent intent = new Intent(NewPassActivity.this, RegisterConfirmActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                        startActivity(intent, options.toBundle());
                    }
                });*//*
            }
        });*/
        onSetupViewPager();
        onSetupRvUser();

        bind.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAlreadyRegister) {
                    DialogHelper mDialogConfirm = new DialogHelper(WorkDetailActivity.this);
                    mDialogConfirm.onShowDialogConfirm(bind.viewRoot, new OnClickFinish() {
                        @Override
                        public void onClickItem() {
                            isAlreadyRegister = false;
                            bind.tvSubmit.setTextColor(Color.parseColor("#FFFFFF"));
                            bind.tvSubmit.setBackgroundColor(Color.parseColor("#84B8FF"));
                            bind.viewWaitingJob.setVisibility(View.GONE);
                        }
                    });
                    mDialogConfirm.show();
                } else {
                    DialogHelper mDialogRegisterJob = new DialogHelper(WorkDetailActivity.this);
                    mDialogRegisterJob.onShowRegisterJob(new OnClickFinish() {
                        @Override
                        public void onClickItem() {
                            isAlreadyRegister = true;
                            bind.tvSubmit.setTextColor(Color.parseColor("#223254"));
                            bind.tvSubmit.setBackgroundColor(Color.parseColor("#DFE5ED"));
                            bind.viewWaitingJob.setVisibility(View.VISIBLE);
                        }
                    });
                    mDialogRegisterJob.show();
                }
            }
        });


    }

    private void onSetupViewPager() {
        listItem.add("https://i.imgur.com/PpZlIRy.jpg");
        listItem.add("https://i.imgur.com/USo6P0c.jpg");
        listItem.add("https://i.imgur.com/8Lz9Np9.jpg");
        listItem.add("https://i.imgur.com/PpZlIRy.jpg");
        listItem.add("https://i.imgur.com/USo6P0c.jpg");
        listItem.add("https://i.imgur.com/8Lz9Np9.jpg");

        fragmentAdapter = new ViewpagerWorkAdapter(this, getSupportFragmentManager(), listItem);
        bind.viewPager.setAdapter(fragmentAdapter);
        bind.viewPager.setCurrentItem(0);
        bind.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void onSetupRvUser() {
        mWorkItems = new ArrayList<>();
        mWorkItems.add(new WorkItem(1, "askjdhakjs"));
        mWorkItems.add(new WorkItem(2, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(4, "askjdhakjs"));
        mWorkUserAdapter = new WorkUserAdapter(this, mWorkItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                DialogHelper mDialogHelper = new DialogHelper(WorkDetailActivity.this);
                mDialogHelper.onShowUserInfo();
                mDialogHelper.show();
            }
        });
        bind.rvUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bind.rvUser.setItemAnimator(new DefaultItemAnimator());
        SpacesItemDecoration decoration = new SpacesItemDecoration(ConvertUtils.dp2px(8), ConvertUtils.dp2px(8));
        bind.rvUser.addItemDecoration(decoration);
        bind.rvUser.setAdapter(mWorkUserAdapter);
    }
}
