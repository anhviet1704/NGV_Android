package com.base.app.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.repo.JobRepo;
import com.base.app.utils.Response;

import javax.inject.Inject;

public class WorkMapFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private MutableLiveData<ResponseObj<JobNewResponse>> mJobs;

    @Inject
    public WorkMapFragmentVM(JobRepo repository) {
        this.mRepository = repository;
        mJobs = new MutableLiveData<>();
    }


    public MutableLiveData<ResponseObj<JobNewResponse>> getJobsMap(int osin_id, double latitude, double longitude, float radius, int mode, int limit) {
        if (mJobs.getValue() != null) {
            if (mJobs.getValue().getResponse() == Response.FAILED)
                mJobs = mRepository.getJobsMap(osin_id, latitude, longitude, radius, mode, limit);
        } else {
            mJobs = mRepository.getJobsMap(osin_id, latitude, longitude, radius, mode, limit);
        }
        return mJobs;
    }
}
