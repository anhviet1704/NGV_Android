package com.base.app.ui.activity.wallet;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.model.WorkItem;
import com.base.app.ui.adapter.HistoryAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.viewmodel.WalletActivityVM;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HistoryActivity extends BaseActivity<WalletActivityVM, ViewDataBinding> {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.view_toolbar)
    RoundRectView mViewToolbar;
    @BindView(R.id.view2)
    View mView2;
    @BindView(R.id.tv_type)
    MagicTextView mTvType;
    @BindView(R.id.iv_prev)
    ImageView mIvPrev;
    @BindView(R.id.iv_next)
    ImageView mIvNext;
    @BindView(R.id.rv_history)
    RecyclerView mRvHistory;
    @BindView(R.id.view_root)
    ConstraintLayout mViewRoot;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet_history;
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
        mRvHistory.setLayoutManager(new LinearLayoutManager(this));
        mRvHistory.setItemAnimator(new DefaultItemAnimator());
        mRvHistory.setAdapter(mWorkAdapter);
    }

    @OnClick({R.id.iv_back, R.id.iv_prev, R.id.iv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_prev:
                break;
            case R.id.iv_next:
                break;
        }
    }
}
