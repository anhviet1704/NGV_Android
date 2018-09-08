package com.base.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.base.app.R;
import com.base.app.model.LoginItem;
import com.base.app.ui.activity.AboutActivity;
import com.base.app.ui.activity.ChangePassActivity;
import com.base.app.ui.activity.ContactActivity;
import com.base.app.ui.activity.LanguageActivity;
import com.base.app.ui.activity.LoginActivity;
import com.base.app.ui.activity.ProfileActivity;
import com.base.app.ui.activity.wallet.WalletActivity;
import com.base.app.utils.AppCons;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.PrefHelper;
import com.bumptech.glide.Glide;
import com.ivankocijan.magicviews.views.MagicTextView;
import com.loopeer.shadow.ShadowView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class SettingFragment extends Fragment {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    MagicTextView tvName;
    @BindView(R.id.tv_status)
    MagicTextView tvStatus;
    @BindView(R.id.constraintLayout3)
    ConstraintLayout constraintLayout3;
    @BindView(R.id.view_wallet)
    LinearLayout viewWallet;
    @BindView(R.id.view_change_pass)
    LinearLayout viewChangePass;
    @BindView(R.id.view_change_language)
    LinearLayout viewChangeLanguage;
    @BindView(R.id.shadow_view)
    ShadowView shadowView;
    @BindView(R.id.view_info)
    LinearLayout viewInfo;
    @BindView(R.id.view_signout)
    LinearLayout viewSignout;
    Unbinder unbinder;
    @Inject
    LoginItem mLoginItem;

    @Inject
    PrefHelper mPrefHelper;
    @BindView(R.id.view_call_phone)
    LinearLayout viewCallPhone;

    public static SettingFragment newInstance() {
        Bundle bundle = new Bundle();
        SettingFragment fragment = new SettingFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AndroidSupportInjection.inject(this);
        tvName.setText(mLoginItem.getFullname());
        Glide.with(this).load(AppCons.HOST_URL + mLoginItem.getAvatar()).apply(NGVUtils.onGetCircleCrop().placeholder(R.drawable.ic_avatar)).into(ivAvatar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_avatar, R.id.view_wallet, R.id.view_change_pass, R.id.view_change_language, R.id.view_info, R.id.view_signout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                NGVUtils.startActivity(getActivity(), ProfileActivity.class, ivAvatar);
                break;
            case R.id.view_wallet:
                startActivity(new Intent(getActivity(), WalletActivity.class));
                break;
            case R.id.view_change_pass:
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(viewChangePass, 0, 0, 0, 0);
                startActivity(new Intent(getActivity(), ChangePassActivity.class));
                break;
            case R.id.view_change_language:
                ActivityOptionsCompat options1 = ActivityOptionsCompat.makeClipRevealAnimation(viewChangeLanguage, 0, 0, 0, 0);
                startActivity(new Intent(getActivity(), LanguageActivity.class));
                break;
            case R.id.view_info:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                //NGVUtils.startActivity(getActivity(), AboutActivity.class, viewInfo);
                break;
            case R.id.view_signout:
                mPrefHelper.putString(AppCons.LOGIN_USERNAME, "");
                mPrefHelper.putString(AppCons.LOGIN_PASSWORD, "");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent, ActivityOptionsCompat.makeClipRevealAnimation(view, 0, 0, 0, 0).toBundle());
                getActivity().finish();
                break;
        }
    }

    @OnClick(R.id.view_call_phone)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), ContactActivity.class));
    }
}