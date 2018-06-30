package com.base.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.base.app.R;
import com.ivankocijan.magicviews.views.MagicTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
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
