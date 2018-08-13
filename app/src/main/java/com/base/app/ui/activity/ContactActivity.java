package com.base.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.base.app.R;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactActivity extends AppCompatActivity {


    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.magicTextView5)
    MagicTextView magicTextView5;
    @BindView(R.id.magicTextView6)
    MagicTextView magicTextView6;
    @BindView(R.id.tv_hotline)
    MagicTextView tvHotline;
    @BindView(R.id.tv_phone)
    MagicTextView tvPhone;
    @BindView(R.id.tv_email)
    MagicTextView tvEmail;
    @BindView(R.id.tv_address)
    MagicTextView tvAddress;
    @BindView(R.id.roundRectView6)
    RoundRectView roundRectView6;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.bt_call)
    MagicButton btCall;
    @BindView(R.id.bt_email)
    MagicButton btEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.iv_back, R.id.bt_call, R.id.bt_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_call:
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + "0933533776";
                i.setData(Uri.parse(p));
                startActivity(i);
                break;
            case R.id.bt_email:
                String to = "ngv@ngv.com";
                String subject = "subject of NGV";
                String message = "content of NGV";
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(email, getString(R.string.tv_setting_024)));
                } catch (android.content.ActivityNotFoundException ex) {
                }
                break;
        }
    }
}
