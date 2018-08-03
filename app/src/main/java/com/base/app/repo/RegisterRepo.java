package com.base.app.repo;


import android.arch.lifecycle.MutableLiveData;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.data.ApiServices;
import com.base.app.model.BaseValueItem;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.RoleItem;
import com.base.app.model.UploadItem;
import com.base.app.model.postobj.RegisterObj;
import com.base.app.module.AppDatabase;
import com.base.app.utils.AppCons;
import com.base.app.utils.Response;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

@Singleton
public class RegisterRepo {

    private MutableLiveData<ResponseObj<List<BaseValueItem>>> mCountries;
    private MutableLiveData<ResponseObj<List<BaseValueItem>>> mOffices;
    private MutableLiveData<ResponseObj<List<RoleItem>>> mRoles;
    private MutableLiveData<ResponseObj<RegisterItem>> mRegister;
    private SingleLiveEvent<ResponseObj<LoginItem>> mLogin;
    private SingleLiveEvent<ResponseObj> mChangePass;
    private ApiServices mApiServices;
    private Disposable disposable;
    private AppDatabase mAppDatabase;


    public RegisterRepo(ApiServices mApiServices, AppDatabase mDatabase) {
        this.mCountries = new MutableLiveData<>();
        this.mRoles = new MutableLiveData<>();
        this.mOffices = new MutableLiveData<>();
        this.mRegister = new MutableLiveData<>();
        this.mLogin = new SingleLiveEvent<>();
        this.mChangePass = new SingleLiveEvent<>();
        this.mApiServices = mApiServices;
        this.mAppDatabase = mDatabase;
    }

    public MutableLiveData<ResponseObj<List<BaseValueItem>>> getCounties() {
        if (mCountries.getValue() != null) {
            if (mCountries.getValue().getResponse() == Response.FAILED)
                getCountriesFromServer();
        } else {
            getCountriesFromServer();
        }
        return mCountries;
    }

    public MutableLiveData<ResponseObj<List<BaseValueItem>>> getOffices() {
        if (mOffices.getValue() != null) {
            if (mOffices.getValue().getResponse() == Response.FAILED)
                getOfficesFromServer();
        } else {
            getOfficesFromServer();
        }
        return mOffices;
    }

    public MutableLiveData<ResponseObj<List<RoleItem>>> getRoles() {
        if (mRoles.getValue() != null) {
            if (mRoles.getValue().getResponse() == Response.FAILED)
                getRolesFromServer();
        } else {
            getRolesFromServer();
        }
        return mRoles;
    }

    public MutableLiveData<ResponseObj<RegisterItem>> postRegister(RegisterObj registerObj) {
        postRegisterServer(registerObj);
        return mRegister;
    }

    public SingleLiveEvent<ResponseObj<LoginItem>> postLogin(String phone, String pass) {
        postLoginServer(phone, pass);
        return mLogin;
    }


    private void postLoginServer(String phone, String pass) {
        mApiServices.postLogin(AppCons.LANGUAGE, phone, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<LoginItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<LoginItem> repsonse) {
                        if (repsonse.getSuccess())
                            mLogin.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else {
                            mLogin.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLogin.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    public SingleLiveEvent<ResponseObj> onChangePassword(int id, String oldPass, String newPass) {
        onChangePassFromServer(id, oldPass, newPass);
        return mChangePass;
    }

    private void onChangePassFromServer(int id, String oldPass, String newPass) {
        mApiServices.postChangePasss(AppCons.LANGUAGE, id, oldPass, newPass, newPass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BaseObj>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BaseObj repsonse) {
                        if (repsonse.getSuccess())
                            mChangePass.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else {
                            mChangePass.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mChangePass.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });
    }

    private void getCountriesFromServer() {
        mApiServices.getCountries(AppCons.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseList<BaseValueItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseList<BaseValueItem> repsonse) {
                        if (repsonse.getSuccess()) {
                            mCountries.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        } else
                            mCountries.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCountries.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });

    }

    private void getRolesFromServer() {
        mApiServices.getRoles(AppCons.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseList<RoleItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseList<RoleItem> repsonse) {
                        if (repsonse.getSuccess())
                            mRoles.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mRoles.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRoles.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });

    }

    private void getOfficesFromServer() {
        mApiServices.getOffices(AppCons.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseList<BaseValueItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseList<BaseValueItem> repsonse) {
                        if (repsonse.getSuccess())
                            mOffices.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else {
                            mOffices.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mOffices.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });

    }

    private void postRegisterServer(final RegisterObj registerObj) {
        mApiServices.postRegister(AppCons.LANGUAGE, registerObj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<RegisterItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<RegisterItem> repsonse) {
                        if (repsonse.getSuccess())
                            mRegister.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mRegister.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRegister.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });

    }

    public SingleLiveEvent<ResponseObj<UploadItem>> uploadFile(MultipartBody.Part image) {
        SingleLiveEvent<ResponseObj<UploadItem>> upload = new SingleLiveEvent<>();
        mApiServices.uploadFile(AppCons.LANGUAGE, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BaseObj<UploadItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BaseObj repsonse) {
                        if (repsonse.getSuccess())
                            mRegister.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mRegister.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRegister.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });
        return upload;
    }
}
