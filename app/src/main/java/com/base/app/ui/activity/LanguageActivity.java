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
import com.base.app.utils.AppCons;
import com.base.app.utils.PrefHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicTextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;


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
    @Inject
    PrefHelper mPrefHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_language);
        ButterKnife.bind(this);
        AppCons.LANGUAGE = mPrefHelper.getString(getPackageName() + AppCons.LANGUAGE_KEY);
        if (AppCons.LANGUAGE.equals("")) {
            AppCons.LANGUAGE = "vi";
        }
        if (AppCons.LANGUAGE.equals("vi")) {
            AppCons.LANGUAGE = "vi";
            ivVn.performClick();
        } else {
            ivEn.performClick();
        }

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
                AppCons.LANGUAGE = "vi";
                break;
            case R.id.iv_en:
                ivEn.setBackgroundResource(R.drawable.ic_us_select);
                ivVn.setBackgroundResource(R.drawable.ic_vn);
                AppCons.LANGUAGE = "en";
                break;
            case R.id.bt_finish:
                setLocale(AppCons.LANGUAGE);
                mPrefHelper.putString(getPackageName() + AppCons.LANGUAGE_KEY, AppCons.LANGUAGE);
                finish();
                break;
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //refresh activity
        finish();
        ActivityUtils.finishActivity(MainActivity.class);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);

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
