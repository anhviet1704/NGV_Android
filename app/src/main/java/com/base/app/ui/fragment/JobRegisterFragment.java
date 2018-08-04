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
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobRegisterAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.PrefHelper;
import com.base.app.utils.Response;
import com.base.app.viewmodel.JobRegisterFragmentVM;
import com.ethanhua.skeleton.SkeletonScreen;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class JobRegisterFragment extends BaseFragment<JobRegisterFragmentVM, FragmentJobListBinding> {
    @Inject
    LoginItem mLoginItem;
    @Inject
    PrefHelper mPrefHelper;

    private List<JobCurrentItem> mDataList = new ArrayList<>();

    public static JobRegisterFragment newInstance() {
        Bundle bundle = new Bundle();
        JobRegisterFragment fragment = new JobRegisterFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_job_list;
    }

    @Override
    protected Class<JobRegisterFragmentVM> getViewModel() {
        return JobRegisterFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        final JobRegisterAdapter mWorkAdapter = new JobRegisterAdapter(getContext(), mDataList, new OnClickItem() {
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
        mDialogLoading.show();
        //STATUS CODE :  === 1:waiting ,:2: approved, 3:complete, 4:cancel
        viewModel.getListJobRegister(mLoginItem.getId(), 1).observe(this, new Observer<ResponseObj<List<JobCurrentItem>>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<List<JobCurrentItem>> listResponseObj) {
                if (listResponseObj != null)
                    if (listResponseObj.getResponse() == Response.SUCCESS) {
                        mDataList = listResponseObj.getObj();
                        mWorkAdapter.onUpdateData(mDataList);
                        mDialogLoading.dismiss();
                    } else if (listResponseObj.getResponse() == Response.UNAUTHORIZED) {
                        NGVUtils.showAuthorized(getActivity(), MainActivity.mViewRoot, mPrefHelper);
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
