package com.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.base.app.di.DaggerAppComponent;
import com.blankj.utilcode.util.Utils;
import com.facebook.accountkit.AccountKit;
import com.ivankocijan.magicviews.MagicViews;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class MyApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
        MagicViews.setFontFolderPath(this, "fonts");
        Utils.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}
