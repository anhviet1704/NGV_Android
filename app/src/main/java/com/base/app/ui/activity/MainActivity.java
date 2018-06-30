package com.base.app.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.base.app.R;
import com.base.app.ui.fragment.HomeFragment;
import com.base.app.ui.fragment.JobFragment;
import com.base.app.ui.fragment.NotificationFragment;
import com.base.app.ui.fragment.SettingFragment;
import com.loopeer.shadow.ShadowView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.iv_work)
    ImageView ivWork;
    @BindView(R.id.iv_notification)
    ImageView ivNotification;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.shadow_view)
    ShadowView shadowView;
    @BindView(R.id.view)
    FrameLayout view;
    @BindView(R.id.container)
    ConstraintLayout container;

   /* @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MovieListViewModel> getViewModel() {
        return MovieListViewModel.class;
    }

    @Override
    protected void onCreate(Bundle instance, MovieListViewModel viewModel, ActivityMainBinding binding) {

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(R.id.view, HomeFragment.newInstance(), null)
                .commit();
        //dataBinding.viewPager.setAdapter(new MoviesPagerAdapter(getSupportFragmentManager()));
        //dataBinding.tabs.setupWithViewPager(dataBinding.viewPager);
        //dataBinding.viewPager.setOffscreenPageLimit(3);
    }

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @OnClick({R.id.iv_home, R.id.iv_work, R.id.iv_notification, R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                onClearSelectBottomBar();
                ivHome.setBackgroundColor(Color.parseColor("#84B8FF"));
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view, HomeFragment.newInstance(), null)
                        .commit();
                break;
            case R.id.iv_work:
                onClearSelectBottomBar();
                ivWork.setBackgroundColor(Color.parseColor("#84B8FF"));
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view, JobFragment.newInstance(), null)
                        .commit();
                break;
            case R.id.iv_notification:
                onClearSelectBottomBar();
                ivNotification.setBackgroundColor(Color.parseColor("#84B8FF"));
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view, NotificationFragment.newInstance(), null)
                        .commit();
                break;
            case R.id.iv_setting:
                onClearSelectBottomBar();
                ivSetting.setBackgroundColor(Color.parseColor("#84B8FF"));
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view, SettingFragment.newInstance(), null)
                        .commit();
                break;
        }
    }

    private void onClearSelectBottomBar() {
        ivHome.setBackgroundColor(Color.parseColor("#FAFAFA"));
        ivWork.setBackgroundColor(Color.parseColor("#FAFAFA"));
        ivNotification.setBackgroundColor(Color.parseColor("#FAFAFA"));
        ivSetting.setBackgroundColor(Color.parseColor("#FAFAFA"));
    }
}
