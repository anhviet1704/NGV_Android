package com.base.app.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityNewPassBinding;
import com.base.app.model.ResponseObj;
import com.base.app.utils.Response;
import com.base.app.viewmodel.NewPassActivityVM;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;


public class NewPassActivity extends BaseActivity<NewPassActivityVM, ActivityNewPassBinding> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_new_pass;
    }

    @Override
    protected Class<NewPassActivityVM> getViewModel() {
        return NewPassActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = bind.etPassword.getText().toString();
                String passConfirm = bind.etPasswordConfirm.getText().toString();
                if (StringUtils.isTrimEmpty(pass) || StringUtils.isTrimEmpty(passConfirm)) {

                } else {
                    if (pass.equals(passConfirm))
                        viewModel.onForgotPassword("", bind.etPassword.getText().toString()).observe(NewPassActivity.this, new Observer<ResponseObj>() {
                            @Override
                            public void onChanged(@Nullable ResponseObj responseObj) {
                                if (responseObj != null) {
                                    if (responseObj.getResponse() == Response.SUCCESS) {
                                        ActivityUtils.finishAllActivities();
                                        startActivity(new Intent(NewPassActivity.this, LoginActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(NewPassActivity.this, getString(R.string.tv_error_01), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                }


            }
        });
    }
}
