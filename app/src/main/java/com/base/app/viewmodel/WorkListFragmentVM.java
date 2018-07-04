package com.base.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobDetail;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobLasted;
import com.base.app.repo.JobRepo;
import com.base.app.repo.RegisterRepo;

import javax.inject.Inject;

import retrofit2.http.Field;

public class WorkListFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private MutableLiveData<ResponseObj<JobLasted>> mJobs;

    @Inject
    public WorkListFragmentVM(JobRepo repository) {
        this.mRepository = repository;
        mJobs = new MutableLiveData<>();
    }


    public LiveData<ResponseObj<JobLasted>> getJobList(int osin_id, int limit, int mode) {
        mJobs = mRepository.getjobs(osin_id, limit, mode);
        return mJobs;
    }
}
