package com.base.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentJobListBinding;
import com.base.app.databinding.FragmentNotificationBinding;
import com.base.app.model.WorkItem;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobFinishAdapter;
import com.base.app.ui.adapter.TimeLineAdapter;
import com.base.app.ui.adapter.WorkAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.viewmodel.JobFinishFragmentVM;
import com.base.app.viewmodel.JobFinishFragmentVM;
import com.base.app.viewmodel.JobFinishFragmentVM;

import java.util.ArrayList;
import java.util.List;

public class JobFinishFragment extends BaseFragment<JobFinishFragmentVM, FragmentJobListBinding> {

    private List<WorkItem> mWorkItems = new ArrayList<>();

    public static JobFinishFragment newInstance() {
        Bundle bundle = new Bundle();
        JobFinishFragment fragment = new JobFinishFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_job_list;
    }

    @Override
    protected Class<JobFinishFragmentVM> getViewModel() {
        return JobFinishFragmentVM.class;
    }

    @Override
    protected void onCreate(Bundle instance, JobFinishFragmentVM viewModel) {
        /*viewModel.getUser("JakeWharton").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateUI(binding, user);
            }
        });*/
        mWorkItems = new ArrayList<>();
        mWorkItems.add(new WorkItem(1, "askjdhakjs"));
        mWorkItems.add(new WorkItem(2, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(3, "askjdhakjs"));
        mWorkItems.add(new WorkItem(4, "askjdhakjs"));
        JobFinishAdapter mWorkAdapter = new JobFinishAdapter(getContext(), mWorkItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.rvJob.setLayoutManager(new LinearLayoutManager(getContext()));
        bind.rvJob.setItemAnimator(new DefaultItemAnimator());
        bind.rvJob.setAdapter(mWorkAdapter);
    }
}
