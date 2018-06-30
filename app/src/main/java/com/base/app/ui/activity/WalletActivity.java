package com.base.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityNewPassBinding;
import com.base.app.databinding.ActivityWalletBinding;
import com.base.app.model.WorkItem;
import com.base.app.ui.adapter.HistoryAdapter;
import com.base.app.ui.adapter.JobFinishAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.viewmodel.ForgotPassActivityVM;
import com.base.app.viewmodel.WalletActivityVM;

import java.util.ArrayList;
import java.util.List;


public class WalletActivity extends BaseActivity<WalletActivityVM, ActivityWalletBinding> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected Class<WalletActivityVM> getViewModel() {
        return WalletActivityVM.class;
    }

    @Override
    protected void onCreate(Bundle instance, final WalletActivityVM viewModel) {
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

        List<WorkItem> mWorkItems = new ArrayList<>();
        mWorkItems.add(new WorkItem(1, "askjdhakjs"));
        mWorkItems.add(new WorkItem(2, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(4, "askjdhakjs"));
        HistoryAdapter mWorkAdapter = new HistoryAdapter(this, mWorkItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
               /* Intent intent = new Intent(this, WorkDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());*/
            }
        });
        bind.rvHistory.setLayoutManager(new LinearLayoutManager(this));
        bind.rvHistory.setItemAnimator(new DefaultItemAnimator());
        bind.rvHistory.setAdapter(mWorkAdapter);
    }
}
