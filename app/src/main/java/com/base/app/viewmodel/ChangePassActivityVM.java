package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.repo.RegisterRepo;
import com.base.app.repo.UserRepository;
import com.base.app.utils.SingleLiveEvent;

import javax.inject.Inject;


public class ChangePassActivityVM extends ViewModel {

    private RegisterRepo mRepository;
    private SingleLiveEvent<ResponseObj> mLogin;

    @Inject
    public ChangePassActivityVM(RegisterRepo repository) {
        this.mRepository = repository;
    }


    public SingleLiveEvent<ResponseObj> onChangePass(int id, String oldPass, String newPass) {
        mLogin = mRepository.onChangePassword(id, oldPass, newPass);
        return mLogin;
    }
}
