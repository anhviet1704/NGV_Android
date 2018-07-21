package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobCurrentItem;
import com.base.app.model.ResponseObj;
import com.base.app.repo.JobRepo;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;


public class JobListFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobCurrent;
    private SingleLiveEvent<ResponseObj> mJobCancel;
    private SingleLiveEvent<ResponseObj> mJobFinish;
    private SingleLiveEvent<ResponseObj> mJobRate;

    @Inject
    public JobListFragmentVM(JobRepo repository) {
        this.mRepository = repository;
        this.mJobCancel = new SingleLiveEvent<>();
    }


    public SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> getJobCurent(int osin_id) {
        mJobCurrent = mRepository.getJobCurent(osin_id);
        return mJobCurrent;
    }

    public SingleLiveEvent<ResponseObj> cancelJob(int owner_job_id, int osin_id) {
        mJobCancel = mRepository.onCancelJob(owner_job_id, osin_id);
        return mJobCancel;
    }

    public SingleLiveEvent<ResponseObj> finishJob(int owner_job_id, int osin_id) {
        mJobFinish = mRepository.onFinishJob(owner_job_id, osin_id);
        return mJobFinish;
    }

    public SingleLiveEvent<ResponseObj> rateJob(int osin_id, int owner_job_id, int rate_job) {
        mJobRate = mRepository.onRateJob(osin_id, owner_job_id, rate_job);
        return mJobRate;
    }

    public void onClearData() {
        mJobCurrent.setValue(null);
    }
}
