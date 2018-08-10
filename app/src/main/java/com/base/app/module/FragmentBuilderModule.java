package com.base.app.module;

import com.base.app.ui.fragment.HomeFragment;
import com.base.app.ui.fragment.JobFinishFragment;
import com.base.app.ui.fragment.JobFragment;
import com.base.app.ui.fragment.JobCurrentFragment;
import com.base.app.ui.fragment.JobRegisterFragment;
import com.base.app.ui.fragment.NotificationFragment;
import com.base.app.ui.fragment.SettingFragment;
import com.base.app.ui.fragment.WorkListFragment;
import com.base.app.ui.fragment.WorkMapFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentBuilderModule {


    @ContributesAndroidInjector
    abstract HomeFragment HomeFragment();

    @ContributesAndroidInjector
    abstract JobFragment JobFragment();

    @ContributesAndroidInjector
    abstract NotificationFragment NotificationFragment();

    @ContributesAndroidInjector
    abstract WorkListFragment MovieListFragment();

    @ContributesAndroidInjector
    abstract WorkMapFragment WorkMapFragment();

    @ContributesAndroidInjector
    abstract JobCurrentFragment JobListFragment();

    @ContributesAndroidInjector
    abstract JobRegisterFragment JobRegisterFragment();

    @ContributesAndroidInjector
    abstract JobFinishFragment JobFinishFragment();

    @ContributesAndroidInjector
    abstract SettingFragment SettingFragment();
}
