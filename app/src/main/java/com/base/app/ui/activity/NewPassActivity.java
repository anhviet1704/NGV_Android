package com.base.app.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityNewPassBinding;
import com.base.app.viewmodel.ForgotPassActivityVM;


public class NewPassActivity extends BaseActivity<ForgotPassActivityVM, ActivityNewPassBinding> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_new_pass;
    }

    @Override
    protected Class<ForgotPassActivityVM> getViewModel() {
        return ForgotPassActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*viewModel.updatePassWord(bind.etPassword.getText().toString()).observe(NewPassActivity.this, new Observer<ApiResponse<SchoolYearItem>>() {
                    @Override
                    public void onChanged(@Nullable ApiResponse<SchoolYearItem> schoolYearItemApiResponse) {
                        Intent intent = new Intent(NewPassActivity.this, RegisterConfirmActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                        startActivity(intent, options.toBundle());
                    }
                });*/
            }
        });

    }
}
