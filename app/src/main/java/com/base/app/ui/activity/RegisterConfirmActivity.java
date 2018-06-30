package com.base.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.base.app.R;
import com.github.florent37.shapeofview.shapes.RoundRectView;
import com.ivankocijan.magicviews.views.MagicButton;
import com.ivankocijan.magicviews.views.MagicTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterConfirmActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.view_toolbar)
    RoundRectView viewToolbar;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.magicTextView2)
    MagicTextView magicTextView2;
    @BindView(R.id.bt_finish)
    MagicButton btFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_confirm);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.bt_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                supportFinishAfterTransition();
                break;
            case R.id.bt_finish:
                supportFinishAfterTransition();
                break;
        }
    }
}
