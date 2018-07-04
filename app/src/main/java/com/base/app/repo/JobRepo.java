package com.base.app.repo;


import android.arch.lifecycle.MutableLiveData;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.data.ApiServices;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.JobDetail;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobLasted;
import com.base.app.module.AppDatabase;
import com.base.app.utils.Response;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class JobRepo {

    private MutableLiveData<ResponseObj<JobLasted>> mJobs;
    private SingleLiveEvent<ResponseObj<JobDetail>> mJobDetail;
    private SingleLiveEvent<ResponseObj> mJobRegister;
    private SingleLiveEvent<ResponseObj> mJobCancel;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobCurent;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobStatus;

    private ApiServices mApiServices;
    private Disposable disposable;
    private AppDatabase mAppDatabase;


    public JobRepo(ApiServices mApiServices, AppDatabase mDatabase) {
        this.mJobs = new MutableLiveData<>();
        this.mJobDetail = new SingleLiveEvent<>();
        this.mJobRegister = new SingleLiveEvent<>();
        this.mJobCancel = new SingleLiveEvent<>();
        this.mJobCurent = new SingleLiveEvent<>();
        this.mJobStatus = new SingleLiveEvent<>();
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

    public SingleLiveEvent<ResponseObj> registerJob(int job_id, int sub_job_id, int osin_id) {
        if (mJobRegister.getValue() != null) {
            if (mJobRegister.getValue().getResponse() == Response.FAILED)
                onRegisterJobFromServer(job_id, sub_job_id, osin_id);
        } else {
            onRegisterJobFromServer(job_id, sub_job_id, osin_id);
        }
        return mJobRegister;
    }

    public SingleLiveEvent<ResponseObj> cancelJob(int job_id, int osin_id) {
        if (mJobCancel.getValue() != null) {
            if (mJobCancel.getValue().getResponse() == Response.FAILED)
                onCancelJobFromServer(job_id, osin_id);
        } else {
            onCancelJobFromServer(job_id, osin_id);
        }
        return mJobCancel;
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

    private void onRegisterJobFromServer(int job_id, int sub_job_id, int osin_id) {
        mApiServices.getMaidJobRegister(job_id, sub_job_id, osin_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mJobDetail.setValue(new ResponseObj(null, Response.SUCCESS));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobDetail.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });

    }

    private void onCancelJobFromServer(int job_id, int osin_id) {
        mApiServices.getMaidJobCancel(job_id, osin_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mJobCancel.setValue(new ResponseObj(null, Response.SUCCESS));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobCancel.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });

    }


    public SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> getJobCurent(int osin_id) {
        if (mJobCurent.getValue() != null) {
            if (mJobCurent.getValue().getResponse() == Response.FAILED)
                onGetCurrentJobFromServer(osin_id);
        } else {
            onGetCurrentJobFromServer(osin_id);
        }
        return mJobCurent;
    }

    private void onGetCurrentJobFromServer(int osin_id) {
        mApiServices.getMaidJobCurrent(osin_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseList<JobCurrentItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseList<JobCurrentItem> repsonse) {
                        if (repsonse.getSuccess())
                            mJobCurent.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mJobCurent.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobCurent.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> getJobStatus(int osin_id, int status) {
        //1:waiting ,:2: approved, 3:complete, 4:cancel
        if (mJobStatus.getValue() != null) {
            if (mJobStatus.getValue().getResponse() == Response.FAILED)
                onGetJobStatusJobFromServer(osin_id, status);
        } else {
            onGetJobStatusJobFromServer(osin_id, status);
        }
        return mJobStatus;
    }

    private void onGetJobStatusJobFromServer(int osin_id, int status) {
        mApiServices.getMaidJobHistory(osin_id, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseList<JobCurrentItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseList<JobCurrentItem> repsonse) {
                        if (repsonse.getSuccess())
                            mJobStatus.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mJobStatus.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobStatus.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
