package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobCurrentItem;
import com.base.app.model.JobDetail;
import com.base.app.model.ResponseObj;
import com.base.app.repo.JobRepo;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;


public class WorkDetailActivityVM extends ViewModel {

    private JobRepo mRepository;
    private SingleLiveEvent<ResponseObj<JobDetail>> mJobDetail;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobHistory;
    private SingleLiveEvent<ResponseObj> mJobRegister;
    private SingleLiveEvent<ResponseObj> mJobCancel;

    @Inject
    public WorkDetailActivityVM(JobRepo repository) {
        this.mRepository = repository;
        mJobDetail = new SingleLiveEvent<>();
        mJobRegister = new SingleLiveEvent<>();
        mJobCancel = new SingleLiveEvent<>();
        mJobHistory = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<ResponseObj<JobDetail>> getJobDetail(int owner_job_id, int osin_id) {
        mJobDetail = mRepository.getjobDetail(owner_job_id, osin_id);
        return mJobDetail;
    }

    public SingleLiveEvent<ResponseObj> registerJob(int owner_job_id, int osin_id, String deal) {
        mJobRegister = mRepository.registerJob(owner_job_id, osin_id, deal);
        return mJobRegister;
    }

    public SingleLiveEvent<ResponseObj> cancelJob(int owner_job_id, int osin_id) {
        mJobCancel = mRepository.onCancelJob(owner_job_id, osin_id);
        return mJobCancel;
    }

    public SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> getListJobHistory(int job_id, int status) {
        mJobHistory = mRepository.getJobStatus(job_id, status);
        return mJobHistory;
    }

    public void onClearData() {
        mJobDetail.setValue(null);
    }

    public void onClearHistory() {
        mJobHistory.setValue(null);
    }
}
