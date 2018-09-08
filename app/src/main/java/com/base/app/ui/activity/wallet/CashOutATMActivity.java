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


public class CashOutATMActivity extends BaseActivity<WalletActivityVM, ViewDataBinding> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_wallet_cashout_atm;
    }

    @Override
    protected Class<WalletActivityVM> getViewModel() {
        return WalletActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {

    }
}
