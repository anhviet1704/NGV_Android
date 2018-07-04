package com.base.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityVerifyBinding;
import com.base.app.viewmodel.VerifyActivityVM;


public class VerifyActivity extends BaseActivity<VerifyActivityVM, ActivityVerifyBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_verify;
    }

    @Override
    protected Class<VerifyActivityVM> getViewModel() {
        return VerifyActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*viewModel.updatePassWord(bind.etPassword.getText().toString()).observe(VerifyActivity.this, new Observer<ApiResponse<SchoolYearItem>>() {
                    @Override
                    public void onChanged(@Nullable ApiResponse<SchoolYearItem> schoolYearItemApiResponse) {
                        Intent intent = new Intent(VerifyActivity.this, NewPassActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                        startActivity(intent, options.toBundle());
                    }
                });*/

                Intent intent = new Intent(VerifyActivity.this, NewPassActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });

    }
}
