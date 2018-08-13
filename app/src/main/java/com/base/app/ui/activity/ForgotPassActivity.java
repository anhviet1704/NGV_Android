package com.base.app.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseActivity;
import com.base.app.databinding.ActivityForgotPassBinding;
import com.base.app.viewmodel.ForgotPassActivityVM;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Locale;


public class ForgotPassActivity extends BaseActivity<ForgotPassActivityVM, ActivityForgotPassBinding> {

    public static int APP_REQUEST_CODE = 1;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_forgot_pass;
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
                onAccountKitLogin();
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

    private void onAccountKitLogin() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.CODE
//        UIManager uiManager = new SkinManager(                LoginType.PHONE,                SkinManager.Skin.TRANSLUCENT,                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? getResources().getColor(R.color.colorPrimary,null):getResources().getColor(R.color.colorPrimary)),
//                R.drawable.background,
//                SkinManager.Tint.WHITE,
//                0.55
//        );
//         If you want default country code
//        // configurationBuilder.setDefaultCountryCode("IN");
//        //configurationBuilder.setUIManager(uiManager);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
        /*final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(loginType, AccountKitActivity.ResponseType.TOKEN);
        if (loginType == LoginType.EMAIL) {
            configurationBuilder.setInitialEmail(login);
        } else {
            PhoneNumber phoneNumber = new PhoneNumber("VI", login, "+84");
            configurationBuilder.setInitialPhoneNumber(phoneNumber);
        }
        configurationBuilder.setDefaultCountryCode("VI");
        final AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);*/
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
            } else {
                if (loginResult.getAccessToken() != null) {
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                            if (account.getPhoneNumber() != null) {
                                PhoneNumber phoneNumber = account.getPhoneNumber();
                                String phoneNumberString = phoneNumber.toString();
                                Log.e("PhoneNumber", "" + account.getPhoneNumber().getPhoneNumber());
                                Log.e("CountryCode", "" + account.getPhoneNumber().getCountryCode());
                                Log.e("NumberString", phoneNumberString);
                                if (phoneNumberString.contains(bind.etPassword.getText().toString().substring(1))) {
                                    Intent intent = new Intent(ForgotPassActivity.this, NewPassActivity.class);
                                    ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(bind.btFinish, 0, 0, 0, 0);
                                    startActivity(intent, options.toBundle());
                                } else {
                                    Toast.makeText(ForgotPassActivity.this, getString(R.string.tv_error_04), Toast.LENGTH_SHORT).show();
                                }
                            }
                            if (account.getEmail() != null)
                                Log.e("Email", account.getEmail());
                        }

                        @Override
                        public void onError(final AccountKitError error) {
                        }
                    });
                }
            }
        }
    }
}
