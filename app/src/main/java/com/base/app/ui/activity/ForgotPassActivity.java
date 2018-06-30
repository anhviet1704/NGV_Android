package com.base.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityForgotPassBinding;
import com.base.app.viewmodel.ForgotPassActivityVM;


public class ForgotPassActivity extends BaseActivity<ForgotPassActivityVM, ActivityForgotPassBinding> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forgot_pass;
    }

    @Override
    protected Class<ForgotPassActivityVM> getViewModel() {
        return ForgotPassActivityVM.class;
    }

    @Override
    protected void onCreate(Bundle instance, final ForgotPassActivityVM viewModel) {
        bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*viewModel.updatePassWord(bind.etPassword.getText().toString()).observe(ForgotPassActivity.this, new Observer<ApiResponse<SchoolYearItem>>() {
                    @Override
                    public void onChanged(@Nullable ApiResponse<SchoolYearItem> schoolYearItemApiResponse) {
                        Intent intent = new Intent(ForgotPassActivity.this, VerifyActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                        startActivity(intent, options.toBundle());
                    }
                });*/

                Intent intent = new Intent(ForgotPassActivity.this, VerifyActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.tvHavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassActivity.this, VerifyActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });

    }
}
