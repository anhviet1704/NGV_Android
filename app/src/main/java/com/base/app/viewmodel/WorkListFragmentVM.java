package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.repo.JobRepo;
import com.base.app.utils.SingleLiveEvent;

import javax.inject.Inject;

public class WorkListFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private SingleLiveEvent<ResponseObj<JobNewResponse>> mJobs;

    @Inject
    public WorkListFragmentVM(JobRepo repository) {
        this.mRepository = repository;
        mJobs = new SingleLiveEvent<>();
    }


    public SingleLiveEvent<ResponseObj<JobNewResponse>> getJobList(int osin_id, int limit, int mode, int page) {
        mJobs = mRepository.getjobsNew(osin_id, limit, mode, page);
        return mJobs;
    }
}
