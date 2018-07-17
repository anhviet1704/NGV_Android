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
import com.base.app.databinding.FragmentJobListBinding;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobFinishAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.Response;
import com.base.app.viewmodel.JobFinishFragmentVM;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JobFinishFragment extends BaseFragment<JobFinishFragmentVM, FragmentJobListBinding> {

    @Inject
    LoginItem mLoginItem;

    private List<JobCurrentItem> mDataList = new ArrayList<>();

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
    protected void onInit(Bundle instance) {
        final JobFinishAdapter mWorkAdapter = new JobFinishAdapter(getContext(), mDataList, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                EventBus.getDefault().postSticky(mDataList.get(pos));
                Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.rvJob.setLayoutManager(new LinearLayoutManager(getContext()));
        bind.rvJob.setItemAnimator(new DefaultItemAnimator());
        bind.rvJob.setAdapter(mWorkAdapter);
        //STATUS CODE :  === 1:waiting ,:2: approved, 3:complete, 4:cancel
        viewModel.getListJobFinish(mLoginItem.getId(), 3).observe(this, new Observer<ResponseObj<List<JobCurrentItem>>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<List<JobCurrentItem>> listResponseObj) {
                if (listResponseObj != null)
                    if (listResponseObj.getResponse() == Response.SUCCESS) {
                        mDataList = listResponseObj.getObj();
                        mWorkAdapter.onUpdateData(mDataList);
                    }
            }
        });
    }

    @Override
    public void onStop() {
        viewModel.onClearData();
        super.onStop();
    }
}
