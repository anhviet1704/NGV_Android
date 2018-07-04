package com.base.app.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.base.app.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;


public abstract class BaseActivity<T extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public B bind;
    public T viewModel;

    protected abstract
    @LayoutRes
    int getLayoutResId();

    protected abstract Class<T> getViewModel();

    protected abstract void onInit(Bundle instance);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        EventBus.getDefault().register(this);
        bind = DataBindingUtil.setContentView(this, getLayoutResId());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        ButterKnife.bind(this);
        onInit(savedInstanceState);
        ImageView ivBack = findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    supportFinishAfterTransition();
                }
            });
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(String event) {
    }

    /*
    //Close keyboard when click out edittext
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof MagicEditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }*/
}
