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

    @Inject
    public WorkDetailActivityVM(JobRepo repository) {
        this.mRepository = repository;
        mJobDetail = new SingleLiveEvent<>();
    }


    public SingleLiveEvent<ResponseObj<JobDetail>> getJobDetail(int job_id, int sub_job_id, int osin_id) {
        mJobDetail = mRepository.getjobDetail(job_id, sub_job_id, osin_id);
        return mJobDetail;
    }

    public void onClearData() {
        mJobDetail.setValue(null);
    }
}
