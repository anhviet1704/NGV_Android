package com.base.app.repo;


import android.arch.lifecycle.MutableLiveData;

import com.base.app.base.BaseList;
import com.base.app.base.BaseObj;
import com.base.app.data.ApiServices;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.JobDetail;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.module.AppDatabase;
import com.base.app.utils.Response;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class JobRepo {

    private SingleLiveEvent<ResponseObj<JobDetail>> mJobDetail;
    private SingleLiveEvent<ResponseObj> mJobRegister;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobCurent;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobStatusRegister;

    private ApiServices mApiServices;
    private Disposable disposable;
    private AppDatabase mAppDatabase;


    public JobRepo(ApiServices mApiServices, AppDatabase mDatabase) {
        this.mJobDetail = new SingleLiveEvent<>();
        this.mJobRegister = new SingleLiveEvent<>();
        this.mJobCurent = new SingleLiveEvent<>();
        this.mJobStatusRegister = new SingleLiveEvent<>();
        this.mApiServices = mApiServices;
        this.mAppDatabase = mDatabase;
    }

    public SingleLiveEvent<ResponseObj<JobDetail>> getjobDetail(int owner_job_id, int osin_id) {
        if (mJobDetail.getValue() != null) {
            if (mJobDetail.getValue().getResponse() == Response.FAILED)
                getjobDetailFromServer(owner_job_id, osin_id);
        } else {
            getjobDetailFromServer(owner_job_id, osin_id);
        }
        return mJobDetail;
    }

    public SingleLiveEvent<ResponseObj> registerJob(int owner_job_id, int osin_id, String deal) {
        if (mJobRegister.getValue() != null) {
            if (mJobRegister.getValue().getResponse() == Response.FAILED)
                onRegisterJobFromServer(owner_job_id, osin_id, deal);
        } else {
            onRegisterJobFromServer(owner_job_id, osin_id, deal);
        }
        return mJobRegister;
    }

    public SingleLiveEvent<ResponseObj<JobNewResponse>> getjobsNew(int osin_id, int limit, int mode, int page) {
        SingleLiveEvent<ResponseObj<JobNewResponse>> mJobs = new SingleLiveEvent<>();
        mApiServices.getMaidJobLasted(osin_id, limit, mode, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<JobNewResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<JobNewResponse> repsonse) {
                        if (repsonse.getSuccess())
                            if (repsonse.getData().getData().size() == 0)
                                mJobs.setValue(new ResponseObj(repsonse.getData(), Response.NODATA));
                            else
                                mJobs.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mJobs.setValue(new ResponseObj(null, Response.FAILED, repsonse.getMessage()));
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
        return mJobs;
    }

    private void getjobDetailFromServer(int owner_job_id, int osin_id) {
        mApiServices.getMaidJobDetail(owner_job_id, osin_id)
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

    private void onRegisterJobFromServer(int owner_job_id, int osin_id, String deal) {
        mApiServices.getMaidJobRegister(owner_job_id, osin_id, deal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mJobRegister.setValue(new ResponseObj(null, Response.SUCCESS));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobRegister.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });

    }

    public SingleLiveEvent<ResponseObj> onCancelJob(int owner_job_id, int osin_id) {
        SingleLiveEvent<ResponseObj> mJobCancel = new SingleLiveEvent<>();
        mApiServices.getMaidJobCancel(owner_job_id, osin_id)
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
        return mJobCancel;
    }

    public SingleLiveEvent<ResponseObj> onFinishJob(int owner_job_id, int osin_id) {
        SingleLiveEvent<ResponseObj> mJob = new SingleLiveEvent<>();
        mApiServices.getMaidJobFinish(owner_job_id, osin_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mJob.setValue(new ResponseObj(null, Response.SUCCESS));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJob.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });
        return mJob;
    }

    public SingleLiveEvent<ResponseObj> onRateJob(int osin_id, int owner_job_id, int rate_job) {
        SingleLiveEvent<ResponseObj> mJob = new SingleLiveEvent<>();
        mApiServices.maidRateJob(osin_id, owner_job_id, rate_job)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BaseObj>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(BaseObj baseObj) {
                        mJob.setValue(new ResponseObj(null, Response.SUCCESS));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJob.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }
                });
        return mJob;
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
        if (mJobStatusRegister.getValue() != null) {
            if (mJobStatusRegister.getValue().getResponse() == Response.FAILED)
                onGetJobStatusJobFromServer(osin_id, status);
        } else {
            onGetJobStatusJobFromServer(osin_id, status);
        }
        return mJobStatusRegister;
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
                            mJobStatusRegister.setValue(new ResponseObj(repsonse.getData(), Response.SUCCESS));
                        else
                            mJobStatusRegister.setValue(new ResponseObj(repsonse.getData(), Response.FAILED, repsonse.getMessage()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mJobStatusRegister.setValue(new ResponseObj(null, Response.FAILED, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<ResponseObj<JobNewResponse>> getJobsMap(int osin_id, double latitude, double longitude, float radius, int mode, int limit) {
        MutableLiveData<ResponseObj<JobNewResponse>> mJobs = new MutableLiveData<>();
        mApiServices.getMaidJobLastedMap(osin_id, latitude, longitude, radius, mode, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseObj<JobNewResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(BaseObj<JobNewResponse> repsonse) {
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
        return mJobs;
    }
}
