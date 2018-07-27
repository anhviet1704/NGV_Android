package com.base.app.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityLoginBinding;
import com.base.app.di.AppModule;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.utils.AppCons;
import com.base.app.utils.PrefHelper;
import com.base.app.utils.Response;
import com.base.app.utils.keyboard.KeyboardHeightObserver;
import com.base.app.utils.keyboard.KeyboardHeightProvider;
import com.base.app.viewmodel.LoginActivityVM;
import com.blankj.utilcode.util.StringUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseActivity<LoginActivityVM, ActivityLoginBinding> implements KeyboardHeightObserver {

    @Inject
    PrefHelper mPrefHelper;
    private String userName;
    private String passWord;
    private boolean isSplashShow = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected Class<LoginActivityVM> getViewModel() {
        return LoginActivityVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        //mDialogLoading = new DialogLoading(this, bind.viewRoot);
        keyboardHeightProvider = new KeyboardHeightProvider(this);
        bind.viewRoot.post(new Runnable() {
            public void run() {
                keyboardHeightProvider.start();
            }
        });
        onCheckPermission();
        onSetupClick();
        userName = mPrefHelper.getString(AppCons.LOGIN_USERNAME);
        passWord = mPrefHelper.getString(AppCons.LOGIN_PASSWORD);
        if (!userName.equals("")) {
            mDialogLoading.show();
            bind.etUsername.setText(userName);
            bind.etPassword.setText(passWord);
            bind.ivLogin.performClick();
        }
    }

    @SuppressLint("CheckResult")
    private void onCheckPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                    }
                });
    }


    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    private void onSetupClick() {
        RxTextView.textChanges(bind.etPassword)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        TransitionSet set = new TransitionSet()
                                .addTransition(new Scale(0.7f))
                                .addTransition(new Fade())
                                .setInterpolator(new LinearOutSlowInInterpolator());
                        TransitionManager.beginDelayedTransition(bind.viewRoot, set);
                        if (charSequence.length() >= 1) {
                            bind.ivShowPass.setVisibility(View.VISIBLE);
                            bind.ivLogin.setVisibility(View.VISIBLE);
                        } else {
                            bind.ivShowPass.setVisibility(View.GONE);
                            bind.ivLogin.setVisibility(View.GONE);
                        }
                    }
                });
        bind.ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (StringUtils.isTrimEmpty(bind.etUsername.getText().toString())
                        || TextUtils.isEmpty(bind.etPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, getString(R.string.tv_error_01), Toast.LENGTH_SHORT).show();
                } else {
                    if (!mDialogLoading.isShowing())
                        mDialogLoading.show();
                    viewModel.postLogin(bind.etUsername.getText().toString(), bind.etPassword.getText().toString())
                            .observe(LoginActivity.this, new Observer<ResponseObj<LoginItem>>() {
                                @Override
                                public void onChanged(@Nullable ResponseObj<LoginItem> loginItemResponseObj) {
                                    if (loginItemResponseObj.getResponse() == Response.SUCCESS) {
                                        AppModule.setLoginItem(loginItemResponseObj.getObj());
                                        mPrefHelper.putString(AppCons.LOGIN_USERNAME, bind.etUsername.getText().toString().trim());
                                        mPrefHelper.putString(AppCons.LOGIN_PASSWORD, bind.etPassword.getText().toString().trim());
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.ivLogin, 0, 0, 0, 0);
                                        startActivity(intent, options.toBundle());
                                        mDialogLoading.dismiss();
                                        finish();
                                    } else {
                                        mDialogLoading.dismiss();
                                        Toast.makeText(LoginActivity.this, loginItemResponseObj.getErr(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        bind.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.ivLogin, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.ivShowPass.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        bind.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        bind.etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                return true;
            }
        });
        bind.tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.ivLogin, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
    }


    private KeyboardHeightProvider keyboardHeightProvider;

    @Override
    protected void onResume() {
        super.onResume();
        keyboardHeightProvider.setKeyboardHeightObserver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        keyboardHeightProvider.setKeyboardHeightObserver(null);
    }

    @Override
    protected void onDestroy() {
        keyboardHeightProvider.close();
        super.onDestroy();
    }

    @Override
    public void onKeyboardHeightChanged(int height, int orientation) {
        bind.viewRoot.setPadding(0, 0, 0, height);
    }
}
