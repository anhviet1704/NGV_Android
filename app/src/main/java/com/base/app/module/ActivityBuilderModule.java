package com.base.app.module;

import com.base.app.ui.activity.ChangePassActivity;
import com.base.app.ui.activity.ForgotPassActivity;
import com.base.app.ui.activity.LanguageActivity;
import com.base.app.ui.activity.LoginActivity;
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.activity.NewPassActivity;
import com.base.app.ui.activity.ProfileActivity;
import com.base.app.ui.activity.RegisterActivity;
import com.base.app.ui.activity.VerifyActivity;
import com.base.app.ui.activity.wallet.AddBankActivity;
import com.base.app.ui.activity.wallet.CashOutATMActivity;
import com.base.app.ui.activity.wallet.CashOutActivity;
import com.base.app.ui.activity.wallet.CashOutCreaditActivity;
import com.base.app.ui.activity.wallet.HistoryActivity;
import com.base.app.ui.activity.wallet.LinkActivity;
import com.base.app.ui.activity.wallet.WalletActivity;
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

    @ContributesAndroidInjector
    abstract ProfileActivity ProfileActivity();

    @ContributesAndroidInjector
    abstract LanguageActivity LanguageActivity();

    @ContributesAndroidInjector
    abstract LinkActivity LinkActivity();

    @ContributesAndroidInjector
    abstract AddBankActivity AddBankActivity();

    @ContributesAndroidInjector
    abstract CashOutActivity CashOutActivity();

    @ContributesAndroidInjector
    abstract CashOutATMActivity CashOutATMActivity();

    @ContributesAndroidInjector
    abstract CashOutCreaditActivity CashOutCreaditActivity();

    @ContributesAndroidInjector
    abstract HistoryActivity HistoryActivity();

}
