package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.base.app.model.CountryResponse;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.repo.RegisterRepo;
import com.base.app.repo.UserRepository;
import com.base.app.ui.activity.RegisterActivity;
import com.base.app.utils.Response;
import com.base.app.utils.SingleLiveEvent;

import javax.inject.Inject;

public class LoginActivityVM extends ViewModel {

    private RegisterRepo mRepository;
    private SingleLiveEvent<ResponseObj<LoginItem>> mLogin;

    @Inject
    public LoginActivityVM(RegisterRepo repository) {
        this.mRepository = repository;
        mLogin = new SingleLiveEvent<>();
    }


    public SingleLiveEvent<ResponseObj<LoginItem>> postLogin(String phone, String pass) {
        mLogin = mRepository.postLogin(phone, pass);
        return mLogin;
    }
}
