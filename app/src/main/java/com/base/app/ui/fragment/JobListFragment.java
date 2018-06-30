package com.base.app.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentJobListBinding;
import com.base.app.databinding.FragmentNotificationBinding;
import com.base.app.model.WorkItem;
import com.base.app.ui.adapter.TimeLineAdapter;
import com.base.app.viewmodel.JobListFragmentVM;
import com.base.app.viewmodel.JobListFragmentVM;

import java.util.ArrayList;
import java.util.List;

public class JobListFragment extends BaseFragment<JobListFragmentVM, FragmentJobListBinding> {

    private List<WorkItem> mDataList = new ArrayList<>();

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
    protected void onCreate(Bundle instance, JobListFragmentVM viewModel) {
        /*viewModel.getUser("JakeWharton").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateUI(binding, user);
            }
        });*/
        mDataList.add(new WorkItem(1, "askjdhakjs"));
        mDataList.add(new WorkItem(2, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));
        mDataList.add(new WorkItem(3, "askjdhakjs"));

        bind.rvJob.setLayoutManager(new LinearLayoutManager(getActivity()));
        bind.rvJob.setHasFixedSize(true);
        TimeLineAdapter mTimeLineAdapter = new TimeLineAdapter(mDataList);
        bind.rvJob.setAdapter(mTimeLineAdapter);
    }
}
