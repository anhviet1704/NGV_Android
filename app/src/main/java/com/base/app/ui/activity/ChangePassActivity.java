package com.base.app.ui.activity;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityChangePassBinding;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.utils.AppCons;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.PrefHelper;
import com.base.app.utils.Response;
import com.base.app.viewmodel.ChangePassActivityVM;
import com.blankj.utilcode.util.StringUtils;

import javax.inject.Inject;


public class ChangePassActivity extends BaseActivity<ChangePassActivityVM, ActivityChangePassBinding> {

    @Inject
    LoginItem mLoginItem;
    @Inject
    PrefHelper mPrefHelper;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_change_pass;
    }

    @Override
    protected Class<ChangePassActivityVM> getViewModel() {
        return ChangePassActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        bind.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bind.btFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldPass = bind.etOldPassword.getText().toString();
                        final String newPass = bind.etNewPassword.getText().toString();
                        String confirmPass = bind.etConfirmPassword.getText().toString();
                        if (!StringUtils.isTrimEmpty(oldPass) && !StringUtils.isTrimEmpty(newPass) && !StringUtils.isTrimEmpty(confirmPass))
                            if (newPass.equals(confirmPass)) {
                                viewModel.onChangePass(mLoginItem.getId(), oldPass, newPass).observe(ChangePassActivity.this, new Observer<ResponseObj>() {
                                    @Override
                                    public void onChanged(@Nullable ResponseObj responseObj) {
                                        if (responseObj.getResponse() == Response.SUCCESS) {
                                            mPrefHelper.putString(AppCons.LOGIN_PASSWORD, newPass);
                                            finish();
                                        } else if (responseObj.getResponse() == Response.UNAUTHORIZED) {
                                            NGVUtils.showAuthorized(ChangePassActivity.this, MainActivity.mViewRoot, mPrefHelper);
                                        } else {
                                            Toast.makeText(ChangePassActivity.this, responseObj.getErr(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                    }
                });
            }
        });

    }
}
