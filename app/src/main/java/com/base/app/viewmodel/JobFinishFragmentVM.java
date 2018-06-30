package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.repo.UserRepository;

import javax.inject.Inject;


public class JobFinishFragmentVM extends ViewModel {

    private UserRepository mRepository;

    @Inject
    public JobFinishFragmentVM(UserRepository repository) {
        this.mRepository = repository;
    }


 /*   public LiveData<ApiResponse<SchoolYearItem>> updatePassWord(String newPass) {
        schoolTermLivedata = mRepository.updatePassWord(newPass);
        return schoolTermLivedata;
    }*/
}
