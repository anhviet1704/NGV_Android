package com.base.app.ui.fragment;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentJobListBinding;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.ui.adapter.TimeLineAdapter;
import com.base.app.utils.Response;
import com.base.app.viewmodel.JobListFragmentVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JobListFragment extends BaseFragment<JobListFragmentVM, FragmentJobListBinding> {

    @Inject
    LoginItem mLoginItem;
    private List<JobCurrentItem> mDataList = new ArrayList<>();

    public static JobListFragment newInstance() {
        Bundle bundle = new Bundle();
        JobListFragment fragment = new JobListFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_job_list;
    }

    @Override
    protected Class<JobListFragmentVM> getViewModel() {
        return JobListFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        bind.rvJob.setLayoutManager(new LinearLayoutManager(getActivity()));
        bind.rvJob.setHasFixedSize(true);
        final TimeLineAdapter mTimeLineAdapter = new TimeLineAdapter(mDataList);
        bind.rvJob.setAdapter(mTimeLineAdapter);
        viewModel.getJobCurent(mLoginItem.getId()).observe(this, new Observer<ResponseObj<List<JobCurrentItem>>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<List<JobCurrentItem>> listResponseObj) {
                if (listResponseObj != null)
                    if (listResponseObj.getResponse() == Response.SUCCESS) {
                        mDataList = listResponseObj.getObj();
                        mTimeLineAdapter.onUpdateData(mDataList);
                    }
            }
        });
    }

    @Override
    public void onDestroyView() {
        viewModel.onClearData();
        super.onDestroyView();
    }
}
