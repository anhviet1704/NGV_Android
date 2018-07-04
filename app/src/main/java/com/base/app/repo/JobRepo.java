package com.base.app.repo;


import android.arch.lifecycle.MutableLiveData;

import com.base.app.base.BaseObj;
import com.base.app.data.ApiServices;
import com.base.app.model.CountryItem;
import com.base.app.model.JobDetail;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobLasted;
import com.base.app.module.AppDatabase;
import com.base.app.utils.Response;
import com.base.app.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class JobRepo {

    private MutableLiveData<ResponseObj<JobLasted>> mJobs;
    private SingleLiveEvent<ResponseObj<JobDetail>> mJobDetail;
    private SingleLiveEvent<ResponseObj<LoginItem>> mLogin;
    private ApiServices mApiServices;
    private Disposable disposable;
    private AppDatabase mAppDatabase;


    public JobRepo(ApiServices mApiServices, AppDatabase mDatabase) {
        this.mJobs = new MutableLiveData<>();
        this.mJobDetail = new SingleLiveEvent<>();
        this.mLogin = new SingleLiveEvent<>();
        this.mApiServices = mApiServices;
        this.mAppDatabase = mDatabase;
    }

    public MutableLiveData<ResponseObj<JobLasted>> getjobs(int osin_id, int limit, int mode) {
        if (mJobs.getValue() != null) {
            if (mJobs.getValue().getResponse() == Response.FAILED)
                getjobsFromServer(osin_id, limit, mode);
        } else {
            getjobsFromServer(osin_id, limit, mode);
        }
        return mJobs;
    }

    public SingleLiveEvent<ResponseObj<JobDetail>> getjobDetail(int job_id, int sub_job_id, int osin_id) {
        if (mJobDetail.getValue() != null) {
            if (mJobDetail.getValue().getResponse() == Response.FAILED)
                getjobDetailFromServer(job_id, sub_job_id, osin_id);
        } else {
            getjobDetailFromServer(job_id, sub_job_id, osin_id);
        }
        return mJobDetail;
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

    private void getjobsFromServer(int osin_id, int limit, int mode) {
        mApiServices.getMaidJobLasted(osin_id, limit, mode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<JobLasted>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<JobLasted> repsonse) {
                        if (repsonse.getSuccess())
                            mJobs.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mJobs.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobs.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });

    }

    private void getjobDetailFromServer(int job_id, int sub_job_id, int osin_id) {
        mApiServices.getMaidJobDetail(job_id, sub_job_id, osin_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<JobDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<JobDetail> repsonse) {
                        if (repsonse.getSuccess())
                            mJobDetail.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mJobDetail.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobDetail.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });

    }


}
