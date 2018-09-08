package com.base.app.ui.activity.wallet;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.viewmodel.WalletActivityVM;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CashOutActivity extends BaseActivity<WalletActivityVM, ViewDataBinding> {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.view_toolbar)
    RoundRectView mViewToolbar;
    @BindView(R.id.tv_sum)
    MagicTextView mTvSum;
    @BindView(R.id.roundRectView4)
    RoundRectView mRoundRectView4;
    @BindView(R.id.magicTextView8)
    MagicTextView mMagicTextView8;
    @BindView(R.id.imageView7)
    ImageView mImageView7;
    @BindView(R.id.view_atm)
    RoundRectView mViewAtm;
    @BindView(R.id.imageView8)
    ImageView mImageView8;
    @BindView(R.id.view_visa)
    RoundRectView mViewVisa;
    @BindView(R.id.view_root)
    ConstraintLayout mViewRoot;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet_cashout;
    }

    @Override
    protected Class<WalletActivityVM> getViewModel() {
        return WalletActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
    }

    @OnClick({R.id.iv_back, R.id.view_atm, R.id.view_visa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.view_atm:
                startActivity(new Intent(CashOutActivity.this, CashOutATMActivity.class));
                break;
            case R.id.view_visa:
                startActivity(new Intent(CashOutActivity.this, CashOutCreaditActivity.class));
                break;
        }
    }
}
