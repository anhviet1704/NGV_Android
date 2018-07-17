package com.base.app.ui.fragment;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkListBinding;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobListAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.Response;
import com.base.app.viewmodel.WorkListFragmentVM;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

public class WorkListFragment extends BaseFragment<WorkListFragmentVM, FragmentWorkListBinding> {
    @Inject
    LoginItem mLoginItem;
    private static List<JobNewItem> mWorkItems;
    private JobListAdapter mWorkAdapter;

    public static WorkListFragment newInstance() {
        Bundle args = new Bundle();
        WorkListFragment fragment = new WorkListFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    public static List<JobNewItem> getData() {
        return mWorkItems;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_work_list;
    }

    @Override
    public Class<WorkListFragmentVM> getViewModel() {
        return WorkListFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        mWorkAdapter = new JobListAdapter(getContext(), mWorkItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                EventBus.getDefault().postSticky(mWorkItems.get(pos));
                Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.rvWork.setLayoutManager(new LinearLayoutManager(getContext()));
        bind.rvWork.setItemAnimator(new DefaultItemAnimator());
        bind.rvWork.setAdapter(mWorkAdapter);
        viewModel.getJobList(mLoginItem.getId(), 10, 0)
                .observe(this, new Observer<ResponseObj<JobNewResponse>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<JobNewResponse> response) {
                        if (response.getResponse() == Response.SUCCESS) {
                            mWorkItems = response.getObj().getData();
                            mWorkAdapter.onUpdateData(mWorkItems);
                        }
                    }
                });
    }
}
