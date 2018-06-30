package com.base.app.module;

import com.base.app.ui.activity.ChangePassActivity;
import com.base.app.ui.activity.ForgotPassActivity;
import com.base.app.ui.activity.LoginActivity;
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.activity.NewPassActivity;
import com.base.app.ui.activity.RegisterActivity;
import com.base.app.ui.activity.VerifyActivity;
import com.base.app.ui.activity.WalletActivity;
import com.base.app.ui.activity.WorkDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract LoginActivity LoginActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity RegisterActivity();

    @ContributesAndroidInjector
    abstract ForgotPassActivity ForgotPassActivity();

    @ContributesAndroidInjector
    abstract NewPassActivity NewPassActivity();

    @ContributesAndroidInjector
    abstract VerifyActivity VerifyActivity();

    @ContributesAndroidInjector
    abstract WorkDetailActivity WorkDetailActivity();

    @ContributesAndroidInjector
    abstract ChangePassActivity ChangePassActivity();

    @ContributesAndroidInjector
    abstract WalletActivity WalletActivity();

}
