package com.base.app.ui.fragment;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkListBinding;
import com.base.app.model.LoginItem;
import com.base.app.model.ResponseObj;
import com.base.app.model.joblasted.JobNewItem;
import com.base.app.model.joblasted.JobNewResponse;
import com.base.app.ui.activity.LoginActivity;
import com.base.app.ui.activity.MainActivity;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.JobCell;
import com.base.app.ui.adapter.JobListAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.AppCons;
import com.base.app.utils.DialogHelper;
import com.base.app.utils.NGVUtils;
import com.base.app.utils.PrefHelper;
import com.base.app.utils.Response;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.blankj.utilcode.util.ActivityUtils;
import com.ethanhua.skeleton.SkeletonScreen;
import com.jaychang.srv.OnLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class WorkListFragment extends BaseFragment<WorkListFragmentVM, FragmentWorkListBinding> {
    @Inject
    LoginItem mLoginItem;
    @Inject
    PrefHelper mPrefHelper;
    private static List<JobNewItem> mWorkItems = new ArrayList<>();
    private int page = 1;
    private int lastPage = 1;

    public static WorkListFragment newInstance() {
        Bundle args = new Bundle();
        WorkListFragment fragment = new WorkListFragment();
        //fragment.setArguments(args);
        return fragment;
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
        mDialogLoading.show();
        onLoadJob();
        bind.rvWork.setAutoLoadMoreThreshold(2);
        bind.rvWork.setLoadMoreToTop(false);
        bind.rvWork.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public boolean shouldLoadMore() {
                if (page < lastPage)
                    return true;
                else
                    return false;
            }

            @Override
            public void onLoadMore() {
                onLoadJob();
            }
        });
        bind.rvWork.setItemAnimator(new SlideInUpAnimator());
    }

    private void onLoadJob() {
        if (page > 1)
            bind.rvWork.showLoadMoreView();
        viewModel.getJobList(mLoginItem.getId(), AppCons.PAGE_SIZE, 0, page)
                .observe(this, new Observer<ResponseObj<JobNewResponse>>() {
                    @Override
                    public void onChanged(@Nullable ResponseObj<JobNewResponse> response) {
                        if (response != null) {
                            bind.rvWork.hideLoadMoreView();
                            if (response.getResponse() == Response.SUCCESS) {
                                lastPage = response.getObj().getLastPage();
                                if (response.getObj().getData().size() > 0) {
                                    mWorkItems.addAll(response.getObj().getData());
                                    for (JobNewItem item : response.getObj().getData()) {
                                        JobCell cell = new JobCell(item);
                                        cell.setOnCellClickListener(item1 -> {
                                            EventBus.getDefault().postSticky(item1);
                                            Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                                            startActivity(intent);
                                        });
                                        bind.rvWork.addCell(cell);
                                    }
                                }
                            } else if (response.getResponse() == Response.UNAUTHORIZED) {
                                NGVUtils.showAuthorized(getActivity(), MainActivity.mViewRoot, mPrefHelper);
                            } else if (response.getResponse() == Response.FAILED) {

                            }
                            page++;
                            mDialogLoading.dismiss();
                        }
                    }

                });
    }
}
