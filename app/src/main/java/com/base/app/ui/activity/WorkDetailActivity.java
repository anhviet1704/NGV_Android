package com.base.app.ui.activity;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityWorkDetailBinding;
import com.base.app.model.JobDetail;
import com.base.app.model.LoginItem;
import com.base.app.model.OsinItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobLastDetailItem;
import com.base.app.ui.adapter.ViewpagerWorkAdapter;
import com.base.app.ui.adapter.WorkUserAdapter;
import com.base.app.ui.callback.OnClickFinish;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.DialogHelper;
import com.base.app.utils.Response;
import com.base.app.utils.SpacesItemDecoration;
import com.base.app.viewmodel.WorkDetailActivityVM;
import com.blankj.utilcode.util.ConvertUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class WorkDetailActivity extends BaseActivity<WorkDetailActivityVM, ActivityWorkDetailBinding> {
    @Inject
    LoginItem mLoginItem;
    private JobLastDetailItem mJobLastDetailItem;

    private List<String> listItem = new ArrayList<>();
    private ViewpagerWorkAdapter fragmentAdapter;
    private WorkUserAdapter mUserAdapter;
    private List<OsinItem> mOsinItems;
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
    protected void onInit(Bundle instance) {
        viewModel.getJobDetail(mJobLastDetailItem.getJobId(), mJobLastDetailItem.getSubJobId(), mLoginItem.getId()).observe(this, new Observer<ResponseObj<JobDetail>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<JobDetail> response) {
                if (response != null)
                    if (response.getResponse() == Response.SUCCESS) {
                        onUpdateUI(response.getObj());
                    }
            }
        });
        onSetupViewPager();
        onSetupRvUser();
        bind.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAlreadyRegister) {
                    final DialogHelper mDialogConfirm = new DialogHelper(WorkDetailActivity.this);
                    mDialogConfirm.onShowDialogConfirm(bind.viewRoot, new OnClickFinish() {
                        @Override
                        public void onClickItem() {
                            viewModel.cancelJob(mJobLastDetailItem.getJobId(), mJobLastDetailItem.getSubJobId(), mLoginItem.getId())
                                    .observe(WorkDetailActivity.this, new Observer<ResponseObj>() {
                                        @Override
                                        public void onChanged(@Nullable ResponseObj response) {
                                            if (response.getResponse() == Response.SUCCESS) {
                                                isAlreadyRegister = false;
                                                bind.tvSubmit.setTextColor(Color.parseColor("#FFFFFF"));
                                                bind.tvSubmit.setBackgroundColor(Color.parseColor("#84B8FF"));
                                                bind.viewWaitingJob.setVisibility(View.GONE);
                                                mDialogConfirm.dismiss();
                                            } else {
                                                Toast.makeText(WorkDetailActivity.this, response.getErr(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });
                    mDialogConfirm.show();
                } else {
                    final DialogHelper mDialogRegisterJob = new DialogHelper(WorkDetailActivity.this);
                    mDialogRegisterJob.onShowRegisterJob(bind.viewRoot, new OnClickFinish() {
                        @Override
                        public void onClickItem() {
                            viewModel.registerJob(mJobLastDetailItem.getJobId(), mJobLastDetailItem.getSubJobId(), mLoginItem.getId())
                                    .observe(WorkDetailActivity.this, new Observer<ResponseObj>() {
                                        @Override
                                        public void onChanged(@Nullable ResponseObj response) {
                                            if (response.getResponse() == Response.SUCCESS) {
                                                isAlreadyRegister = true;
                                                bind.tvSubmit.setTextColor(Color.parseColor("#223254"));
                                                bind.tvSubmit.setBackgroundColor(Color.parseColor("#DFE5ED"));
                                                bind.viewWaitingJob.setVisibility(View.VISIBLE);
                                                mDialogRegisterJob.dismiss();
                                            } else {
                                                Toast.makeText(WorkDetailActivity.this, response.getErr(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });
                    mDialogRegisterJob.show();
                }
            }
        });


    }

    private void onUpdateUI(JobDetail obj) {
        String time = obj.getStartDate() + obj.getStartTime() + "-" + obj.getEndTime();
        bind.tvWorkName.setText(obj.getJobName());
        bind.tvWorkPrice.setText(obj.getFee());
        bind.tvWorkTime.setText(obj.getStartDate());
        bind.tvUserName.setText(obj.getOwnerFullName());
        bind.tvUserAddress.setText(obj.getOwnerAddress());
        bind.tvContent.setText(obj.getDescription());
        bind.tvJobTime.setText(time);
        bind.tvJobAddress.setText(String.format(getString(R.string.tv_work_004), obj.getJobAddress()));
        mOsinItems = obj.getOsin();
        mUserAdapter.onUpdateData(mOsinItems);
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
        mUserAdapter = new WorkUserAdapter(this, mOsinItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                DialogHelper mDialogHelper = new DialogHelper(WorkDetailActivity.this);
                mDialogHelper.onShowUserInfo(bind.viewRoot);
                mDialogHelper.show();
            }
        });
        bind.rvUser.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bind.rvUser.setItemAnimator(new DefaultItemAnimator());
        SpacesItemDecoration decoration = new SpacesItemDecoration(ConvertUtils.dp2px(8), ConvertUtils.dp2px(8));
        bind.rvUser.addItemDecoration(decoration);
        bind.rvUser.setAdapter(mUserAdapter);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(JobLastDetailItem event) {
        mJobLastDetailItem = event;
    }

    @Override
    protected void onDestroy() {
        viewModel.onClearData();
        super.onDestroy();
    }
}
