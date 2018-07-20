package com.base.app.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.base.app.R;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LanguageActivity extends AppCompatActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.roundRectView5)
    RoundRectView roundRectView5;
    @BindView(R.id.iv_vn)
    ImageView ivVn;
    @BindView(R.id.tv_content)
    MagicTextView tvContent;
    @BindView(R.id.iv_en)
    ImageView ivEn;
    @BindView(R.id.bt_finish)
    MagicButton btFinish;
    String lang = "vi";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
    }

    public static void setLocale(String lang, Resources resources) {
        Locale myLocale = new Locale(lang);
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration conf = resources.getConfiguration();
        conf.locale = myLocale;
        resources.updateConfiguration(conf, dm);
    }

    @OnClick({R.id.iv_back, R.id.iv_vn, R.id.iv_en, R.id.bt_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_vn:
                ivVn.setBackgroundResource(R.drawable.ic_vn_select);
                ivEn.setBackgroundResource(R.drawable.ic_us);
                lang = "vi";
                break;
            case R.id.iv_en:
                ivEn.setBackgroundResource(R.drawable.ic_us_select);
                ivVn.setBackgroundResource(R.drawable.ic_vn);
                lang = "us";
                break;
            case R.id.bt_finish:
                setLocale("vi", getResources());
                finish();
                break;
        }
    }

    private void refeshLayout() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
