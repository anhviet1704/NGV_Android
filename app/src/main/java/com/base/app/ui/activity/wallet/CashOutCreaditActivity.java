package com.base.app.ui.activity.wallet;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.model.WorkItem;
import com.base.app.ui.adapter.HistoryAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.viewmodel.WalletActivityVM;

import java.util.ArrayList;
import java.util.List;


public class CashOutCreaditActivity extends BaseActivity<WalletActivityVM, ViewDataBinding> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected Class<WalletActivityVM> getViewModel() {
        return WalletActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {


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
        /*bind.rvHistory.setLayoutManager(new LinearLayoutManager(this));
        bind.rvHistory.setItemAnimator(new DefaultItemAnimator());
        bind.rvHistory.setAdapter(mWorkAdapter);*/
    }
}
