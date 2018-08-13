package com.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.base.app.di.DaggerAppComponent;
import com.base.app.utils.AppCons;
import com.base.app.utils.PrefHelper;
import com.blankj.utilcode.util.Utils;
import com.facebook.accountkit.AccountKit;
import com.ivankocijan.magicviews.MagicViews;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class MyApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Inject
    PrefHelper mPrefHelper;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
        MagicViews.setFontFolderPath(this, "fonts");
        Utils.init(this);
        AppCons.LANGUAGE = mPrefHelper.getString(getPackageName() + AppCons.LANGUAGE_KEY);
        if (AppCons.LANGUAGE.equals("")) {
            AppCons.LANGUAGE = "vi";
        }
        Locale myLocale = new Locale(AppCons.LANGUAGE);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        AccountKit.initialize(getApplicationContext());
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
