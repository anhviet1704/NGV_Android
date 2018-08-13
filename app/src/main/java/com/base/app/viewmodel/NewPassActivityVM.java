package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobCurrentItem;
import com.base.app.model.ResponseObj;
import com.base.app.repo.RegisterRepo;
import com.base.app.repo.UserRepository;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;


public class NewPassActivityVM extends ViewModel {

    private RegisterRepo mRepository;

    @Inject
    public NewPassActivityVM(RegisterRepo repository) {
        this.mRepository = repository;
    }


    public SingleLiveEvent<ResponseObj> onForgotPassword(String osin_id, String password) {
        return mRepository.onForgotPassword(osin_id, password);
    }
}
