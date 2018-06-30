package com.base.app.repo;


import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.data.ApiServices;
import com.base.app.model.BaseValueItem;
import com.base.app.model.CountryItem;
import com.base.app.model.CountryResponse;
import com.base.app.model.LoginItem;
import com.base.app.model.RegisterItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.RoleItem;
import com.base.app.model.postobj.RegisterObj;
import com.base.app.module.AppDatabase;
import com.base.app.utils.Response;
import com.base.app.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class RegisterRepo {

    private MutableLiveData<ResponseObj<List<CountryItem>>> mCountries;
    private MutableLiveData<ResponseObj<List<BaseValueItem>>> mOffices;
    private MutableLiveData<ResponseObj<List<RoleItem>>> mRoles;
    private MutableLiveData<ResponseObj<RegisterItem>> mRegister;
    private SingleLiveEvent<ResponseObj<LoginItem>> mLogin;
    private ApiServices mApiServices;
    private Disposable disposable;
    private AppDatabase mAppDatabase;


    public RegisterRepo(ApiServices mApiServices, AppDatabase mDatabase) {
        this.mCountries = new MutableLiveData<>();
        this.mRoles = new MutableLiveData<>();
        this.mOffices = new MutableLiveData<>();
        this.mRegister = new MutableLiveData<>();
        this.mLogin = new SingleLiveEvent<>();
        this.mApiServices = mApiServices;
        this.mAppDatabase = mDatabase;
    }

    public MutableLiveData<ResponseObj<List<CountryItem>>> getCounties() {
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
        mApiServices.postLogin(phone, pass)
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

    private void getCountriesFromServer() {
        mApiServices.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<CountryResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<CountryResponse> repsonse) {
                        if (repsonse.getSuccess()) {
                            String[] temp = repsonse.getData().toString().replace("{", "").replace("}", "").replaceAll("'", "").split(",");
                            List<CountryItem> mCountryItems = new ArrayList<>();
                            for (int i = 0; i < temp.length; i++) {
                                try {
                                    String[] a = temp[i].split("=");
                                    CountryItem item = new CountryItem(a[0], a[1]);
                                    mCountryItems.add(item);
                                } catch (Exception e) {
                                }
                                if (i == temp.length - 1) {
                                    Collections.sort(mCountryItems, new Comparator<CountryItem>() {
                                        public int compare(CountryItem obj1, CountryItem obj2) {
                                            return obj1.getName().compareToIgnoreCase(obj2.getName());
                                        }
                                    });
                                    mCountries.setValue(new ResponseObj(mCountryItems, Response.SUCCESS));
                                }
                            }
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
        mApiServices.getRoles()
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
        mApiServices.getOffices()
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
        mApiServices.postRegister(registerObj)
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

}
