package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobCurrentItem;
import com.base.app.model.JobDetail;
import com.base.app.model.ResponseObj;
import com.base.app.repo.JobRepo;
import com.base.app.repo.UserRepository;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;


public class JobListFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobCurrent;

    @Inject
    public JobListFragmentVM(JobRepo repository) {
        this.mRepository = repository;
    }


    public SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> getJobCurent(int osin_id) {
        mJobCurrent = mRepository.getJobCurent(osin_id);
        return mJobCurrent;
    }

    public void onClearData() {
        mJobCurrent.setValue(null);
    }
}
