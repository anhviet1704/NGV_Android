package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.repo.UserRepository;

import javax.inject.Inject;


public class WalletActivityVM extends ViewModel {

    private UserRepository mRepository;

    @Inject
    public WalletActivityVM(UserRepository repository) {
        this.mRepository = repository;
    }


 /*   public LiveData<ApiResponse<SchoolYearItem>> updatePassWord(String newPass) {
        schoolTermLivedata = mRepository.updatePassWord(newPass);
        return schoolTermLivedata;
    }*/
}
