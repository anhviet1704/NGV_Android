package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobDetail;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobLasted;
import com.base.app.repo.JobRepo;
import com.base.app.repo.UserRepository;
import com.base.app.utils.SingleLiveEvent;

import javax.inject.Inject;


public class WorkDetailActivityVM extends ViewModel {

    private JobRepo mRepository;
    private SingleLiveEvent<ResponseObj<JobDetail>> mJobDetail;
    private SingleLiveEvent<ResponseObj> mJobRegister;
    private SingleLiveEvent<ResponseObj> mJobCancel;

    @Inject
    public WorkDetailActivityVM(JobRepo repository) {
        this.mRepository = repository;
        mJobDetail = new SingleLiveEvent<>();
        mJobRegister = new SingleLiveEvent<>();
        mJobCancel = new SingleLiveEvent<>();
    }

    public SingleLiveEvent<ResponseObj<JobDetail>> getJobDetail(int job_id, int sub_job_id, int osin_id) {
        mJobDetail = mRepository.getjobDetail(job_id, sub_job_id, osin_id);
        return mJobDetail;
    }

    public SingleLiveEvent<ResponseObj> registerJob(int job_id, int sub_job_id, int osin_id) {
        mJobRegister = mRepository.registerJob(job_id, sub_job_id, osin_id);
        return mJobRegister;
    }

    public SingleLiveEvent<ResponseObj> cancelJob(int job_id, int osin_id) {
        mJobCancel = mRepository.cancelJob(job_id, osin_id);
        return mJobCancel;
    }

    public void onClearData() {
        mJobDetail.setValue(null);
    }
}
