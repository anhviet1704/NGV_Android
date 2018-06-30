package com.base.app.repo;


import android.arch.lifecycle.MutableLiveData;

import com.base.app.base.AppExecutors;
import com.base.app.data.ApiServices;
import com.base.app.entity.UserEntity;
import com.base.app.model.ResponseObj;
import com.base.app.module.AppDatabase;
import com.base.app.utils.Response;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class UserRepository {

    private MutableLiveData<ResponseObj> userDetail;
    private ApiServices mApiServices;
    private Disposable disposable;
    private AppDatabase userRoomDatabase;

    @Inject
    public UserRepository(ApiServices mApiServices, AppDatabase mDatabase) {
        this.userDetail = new MutableLiveData<>();
        this.mApiServices = mApiServices;
        this.userRoomDatabase = mDatabase;
    }

    public MutableLiveData<ResponseObj> getUserDetails(String userId) {
        getUserDetailsFromServer(userId);
        return userDetail;
    }

    private void getUserDetailsFromServer(final String userId) {
        /*mApiServices.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(UserData user) {
                        userDetail.setValue(new ResponseObj(user.getUserEntity(), Response.SUCCESS));
                        saveDataToDB(user.getUserEntity());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getDataFromDb(userId);
                        userDetail.setValue(new ResponseObj(null, Response.FAILED));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });*/
    }

    private void saveDataToDB(final UserEntity userEntity) {
        AppExecutors.getInstance().diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        long[] editedRows = userRoomDatabase.userDao().addUser(userEntity);
                        for (long row :
                                editedRows) {
                        }
                    }
                }
        );
    }

    private void getDataFromDb(String userId) {
        userRoomDatabase.userDao().getUserDetail(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<UserEntity>() {
                    @Override
                    public void onNext(UserEntity userEntityData) {
                        if (userEntityData == null) {
                            userDetail.setValue(new ResponseObj(null, Response.FAILED));
                        } else {
                            userDetail.setValue(new ResponseObj(userEntityData, Response.SUCCESS));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        userDetail.setValue(new ResponseObj(null, Response.FAILED));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
