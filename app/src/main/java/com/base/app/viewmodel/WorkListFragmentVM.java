package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.repo.RegisterRepo;

import javax.inject.Inject;

public class WorkListFragmentVM extends ViewModel {

    private RegisterRepo mRepository;
    private MutableLiveData<ResponseObj<LoginItem>> mLogin;

    @Inject
    public WorkListFragmentVM(RegisterRepo repository) {
        this.mRepository = repository;
        mLogin = new MutableLiveData<>();
    }


    public LiveData<ResponseObj<LoginItem>> postLogin(String phone, String pass) {
        mLogin = mRepository.postLogin(phone, pass);
        return mLogin;
    }
}
