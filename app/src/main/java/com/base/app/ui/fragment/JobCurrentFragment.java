package com.base.app.ui.fragment;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentJobListBinding;
import com.base.app.model.JobCurrentItem;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.adapter.TimeLineAdapter;
import com.base.app.ui.callback.OnClickFinish;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.DialogJobMgr;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.PrefHelper;
import com.base.app.utils.Response;
import com.base.app.viewmodel.JobListFragmentVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class JobCurrentFragment extends BaseFragment<JobListFragmentVM, FragmentJobListBinding> {

    @Inject
    LoginItem mLoginItem;
    @Inject
    PrefHelper mPrefHelper;
    private List<JobCurrentItem> mDataList = new ArrayList<>();
    private TimeLineAdapter mTimeLineAdapter;

    public static JobCurrentFragment newInstance() {
        Bundle bundle = new Bundle();
        JobCurrentFragment fragment = new JobCurrentFragment();
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
        mDialogLoading.show();
        bind.rvJob.setLayoutManager(new LinearLayoutManager(getActivity()));
        bind.rvJob.setHasFixedSize(true);
        mTimeLineAdapter = new TimeLineAdapter(mDataList, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                DialogJobMgr mDialogJob = new DialogJobMgr(getActivity());
                mDialogJob.onShowDialogFinish(JobFragment.getRoot(), mDataList.get(pos), new OnClickFinish() {
                    @Override
                    public void onClickFinish() {
                        mDialogLoading.show();
                        viewModel.finishJob(mDataList.get(pos).getOwnerJobId(), mLoginItem.getId()).observe(getActivity(), new Observer<ResponseObj>() {
                            @Override
                            public void onChanged(@Nullable ResponseObj responseObj) {
                                if (responseObj != null) {
                                    mDialogLoading.dismiss();
                                    if (responseObj.getResponse() == Response.SUCCESS) {
                                        mDialogJob.onUpdateUI(3);
                                        onGetJobs();
                                    } else {
                                        Toast.makeText(getContext(), responseObj.getErr(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onClickRate(int type) {
                        mDialogLoading.show();
                        //1: hài lòng, 2:không hài lòng
                        viewModel.rateJob(mLoginItem.getId(), mDataList.get(pos).getOwnerJobId(), type).observe(getActivity(), new Observer<ResponseObj>() {
                            @Override
                            public void onChanged(@Nullable ResponseObj responseObj) {
                                if (responseObj != null) {
                                    mDialogLoading.dismiss();
                                    if (responseObj.getResponse() == Response.SUCCESS) {
                                        mDialogJob.dismiss();
                                    } else {
                                        Toast.makeText(getContext(), responseObj.getErr(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onClickCancel() {
                        mDialogLoading.show();
                        viewModel.cancelJob(mDataList.get(pos).getOwnerJobId(), mLoginItem.getId()).observe(getActivity(), new Observer<ResponseObj>() {
                            @Override
                            public void onChanged(@Nullable ResponseObj responseObj) {
                                if (responseObj != null) {
                                    mDialogLoading.dismiss();
                                    if (responseObj.getResponse() == Response.SUCCESS) {
                                        mDialogJob.dismiss();
                                        onGetJobs();
                                    } else {
                                        Toast.makeText(getContext(), responseObj.getErr(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                });
                mDialogJob.onUpdateUI(1);
                mDialogJob.show();
            }
        });
        bind.rvJob.setAdapter(mTimeLineAdapter);
        onGetJobs();

    }

    private void onGetJobs() {
        viewModel.getJobCurent(mLoginItem.getId()).observe(this, new Observer<ResponseObj<List<JobCurrentItem>>>() {
            @Override
            public void onChanged(@Nullable ResponseObj<List<JobCurrentItem>> listResponseObj) {
                if (listResponseObj != null) {
                    if (listResponseObj.getResponse() == Response.SUCCESS) {
                        if (listResponseObj.getObj().size() > 0) {
                            JobFragment.onUpdateFirstJob(listResponseObj.getObj().get(0));
                            if (listResponseObj.getObj().size() >= 1) {
                                mDataList = listResponseObj.getObj().subList(1, listResponseObj.getObj().size());
                                //mDataList = listResponseObj.getObj();
                                mTimeLineAdapter.onUpdateData(mDataList);
                                bind.viewNodata.setVisibility(View.GONE);
                            } else {
                                bind.viewNodata.setVisibility(View.VISIBLE);
                            }

                        }
                    } else if (listResponseObj.getResponse() == Response.UNAUTHORIZED) {
                        NGVUtils.showAuthorized(getActivity(), MainActivity.mViewRoot, mPrefHelper);
                    }
                    mDialogLoading.dismiss();
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
