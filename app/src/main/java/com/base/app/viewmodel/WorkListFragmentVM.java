package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.repo.JobRepo;

import javax.inject.Inject;

public class WorkListFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private MutableLiveData<ResponseObj<JobNewResponse>> mJobs;

    @Inject
    public WorkListFragmentVM(JobRepo repository) {
        this.mRepository = repository;
        mJobs = new MutableLiveData<>();
    }


    public LiveData<ResponseObj<JobNewResponse>> getJobList(int osin_id, int limit, int mode) {
        mJobs = mRepository.getjobs(osin_id, limit, mode);
        return mJobs;
    }
}
