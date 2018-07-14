package com.base.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.base.app.R;
import com.base.app.model.LoginItem;
import com.base.app.utils.NGVUtils;
import com.bumptech.glide.Glide;
import com.ivankocijan.magicviews.views.MagicTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;


public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.textView2)
    MagicTextView textView2;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.tv_name)
    MagicTextView tvName;
    @BindView(R.id.tv_birthday)
    MagicTextView tvBirthday;
    @BindView(R.id.tv_phone)
    MagicTextView tvPhone;
    @BindView(R.id.tv_email)
    MagicTextView tvEmail;
    @BindView(R.id.tv_address)
    MagicTextView tvAddress;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @Inject
    LoginItem mLoginItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Glide.with(this).load(mLoginItem.getAvatar()).apply(NGVUtils.onGetCircleCrop().placeholder(R.drawable.ic_avatar)).into(ivAvatar);
        tvName.setText(mLoginItem.getFullname());
        tvBirthday.setText(mLoginItem.getBirthday());
        tvPhone.setText(mLoginItem.getPhone());
        tvEmail.setText(mLoginItem.getEmail());
        tvAddress.setText(mLoginItem.getAddress());
    }


    @OnClick({R.id.iv_avatar, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
