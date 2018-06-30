package com.base.app.viewmodel;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.base.app.model.ResponseObj;
import com.base.app.repo.UserRepository;
import com.base.app.utils.Response;

import javax.inject.Inject;

public class MainActivityVM extends ViewModel {

    private UserRepository repository;

    private MediatorLiveData<ResponseObj> data;
    private MutableLiveData<Boolean> isDataBeingFetched;
    private MutableLiveData<String> queriedUserId;
    private MutableLiveData<ResponseObj> userDetailMutableLiveData;

    @Inject
    MainActivityVM(UserRepository repository) {
        this.repository = repository;
        isDataBeingFetched = new MutableLiveData<>();
        isDataBeingFetched.setValue(false);
        data = new MediatorLiveData<>();
        queriedUserId = new MutableLiveData<>();
    }

    MutableLiveData<String> getQueriedUserId() {
        return queriedUserId;
    }

    public MutableLiveData<ResponseObj> getData() {
        /*if (data != null && queriedUserId != null) {
            if (data.getValue() != null &&
                    data.getValue().getResponse() != null &&
                    data.getValue().getResponse() == Response.SUCCESS &&
                    queriedUserId.getValue() != null &&
                    queriedUserId.getValue().length() != 0) {
                if (String.valueOf(data.getValue().getUserEntity().getUserId())
                        .equals(queriedUserId.getValue())) {
                    return data;
                }
            }
        }*/
        data = new MediatorLiveData<>();
        return data;
    }

    MutableLiveData<Boolean> getIsDataBeingFetched() {
        return isDataBeingFetched;
    }

    /*void fetchData() {
        if (data != null) {
            getUserData(repository, queriedUserId.getValue());
        }
    }

    private void getUserData(UserRepository repository, String userId) {
        if (data.getValue() != null &&
                data.getValue().getUserEntity() != null &&
                String.valueOf(data.getValue().getUserEntity().getUserId()).equals(queriedUserId.getValue()))
            return;
        isDataBeingFetched.setValue(true);
        if (userDetailMutableLiveData != null)
            data.removeSource(userDetailMutableLiveData);
        userDetailMutableLiveData = repository.getUserDetails(userId);
        data.addSource(userDetailMutableLiveData,
                new Observer<ResponseObj>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj userDetail) {
                        isDataBeingFetched.setValue(false);
                        data.setValue(userDetail);
                    }
                });
    }*/
}
