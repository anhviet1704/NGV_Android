package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.BaseValueItem;
import com.base.app.model.CountryItem;
import com.base.app.model.CountryResponse;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.RoleItem;
import com.base.app.model.postobj.RegisterObj;
import com.base.app.repo.RegisterRepo;

import java.util.List;

import javax.inject.Inject;


public class RegisterActivityVM extends ViewModel {

    private RegisterRepo mRepository;
    private MutableLiveData<ResponseObj<List<CountryItem>>> mCountries = new MutableLiveData<>();
    private MutableLiveData<ResponseObj<RegisterItem>> mRegister = new MutableLiveData<>();
    private MutableLiveData<ResponseObj<List<BaseValueItem>>> mOffices = new MutableLiveData<>();
    private MutableLiveData<ResponseObj<List<RoleItem>>> mRoles = new MutableLiveData<>();

    @Inject
    public RegisterActivityVM(RegisterRepo repository) {
        this.mRepository = repository;
        mCountries = new MutableLiveData<>();
    }


    public LiveData<ResponseObj<List<CountryItem>>> getCountries() {
        mCountries = mRepository.getCounties();
        return mCountries;
    }

    public MutableLiveData<ResponseObj<List<BaseValueItem>>> getOffices() {
        mOffices = mRepository.getOffices();
        return mOffices;
    }

    public MutableLiveData<ResponseObj<List<RoleItem>>> getRoles() {
        mRoles = mRepository.getRoles();
        return mRoles;
    }

    public LiveData<ResponseObj<RegisterItem>> postRegister(RegisterObj registerObj) {
        mRegister = mRepository.postRegister(registerObj);
        return mRegister;
    }
}
