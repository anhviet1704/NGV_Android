package com.base.app.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;


public abstract class BaseFragment<T extends ViewModel, B extends ViewDataBinding> extends Fragment {
    Unbinder mUnbinder;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public T viewModel;

    public B bind;

    @LayoutRes
    public abstract int getLayoutRes();

    protected abstract Class<T> getViewModel();

    protected abstract void onInit(Bundle instance);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        View view = bind.getRoot();
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AndroidSupportInjection.inject(this);
        //AndroidInjection.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
        onInit(savedInstanceState);
    }

    @Subscribe
    public void onEvent(String event) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

}
