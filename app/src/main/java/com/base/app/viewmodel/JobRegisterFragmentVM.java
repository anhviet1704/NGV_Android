package com.base.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.base.app.model.JobCurrentItem;
import com.base.app.model.ResponseObj;
import com.base.app.repo.JobRepo;
import com.base.app.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;


public class JobRegisterFragmentVM extends ViewModel {

    private JobRepo mRepository;
    private SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> mJobDetail;

    @Inject
    public JobRegisterFragmentVM(JobRepo repository) {
        this.mRepository = repository;
    }


    public SingleLiveEvent<ResponseObj<List<JobCurrentItem>>> getListJobRegister(int job_id, int status) {
        mJobDetail = mRepository.getJobStatus(job_id, status);
        return mJobDetail;
    }

    public void onClearData() {
        mJobDetail.setValue(null);
    }
}
