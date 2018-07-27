package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.BaseValueItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.RoleItem;
import com.base.app.model.postobj.RegisterObj;
import com.base.app.repo.RegisterRepo;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;


public class RegisterActivityVM extends ViewModel {

    private RegisterRepo mRepository;
    private MutableLiveData<ResponseObj<List<BaseValueItem>>> mCountries = new MutableLiveData<>();
    private MutableLiveData<ResponseObj<RegisterItem>> mRegister = new MutableLiveData<>();
    private MutableLiveData<ResponseObj<List<BaseValueItem>>> mOffices = new MutableLiveData<>();
    private MutableLiveData<ResponseObj<List<RoleItem>>> mRoles = new MutableLiveData<>();
    private SingleLiveEvent<ResponseObj<Object>> mUpload = new SingleLiveEvent<>();

    @Inject
    public RegisterActivityVM(RegisterRepo repository) {
        this.mRepository = repository;
        mCountries = new MutableLiveData<>();
    }


    public LiveData<ResponseObj<List<BaseValueItem>>> getCountries() {
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

    public SingleLiveEvent<ResponseObj<Object>> uploadFile(MultipartBody.Part image) {
        mUpload = mRepository.uploadFile(image);
        return mUpload;
    }
}
