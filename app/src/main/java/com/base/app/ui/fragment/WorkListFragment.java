package com.base.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentWorkListBinding;
import com.base.app.model.WorkItem;
import com.base.app.ui.activity.ForgotPassActivity;
import com.base.app.ui.activity.LoginActivity;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.adapter.WorkAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.viewmodel.WorkListFragmentVM;

import java.util.ArrayList;
import java.util.List;

public class WorkListFragment extends BaseFragment<WorkListFragmentVM, FragmentWorkListBinding> {
    private WorkAdapter mWorkAdapter;
    private List<WorkItem> mWorkItems;

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
    protected void onCreate(Bundle instance, WorkListFragmentVM viewModel) {
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
        mWorkAdapter = new WorkAdapter(getContext(), mWorkItems, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {
                Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                startActivity(intent, options.toBundle());
            }
        });
        bind.rvWork.setLayoutManager(new LinearLayoutManager(getContext()));
        bind.rvWork.setItemAnimator(new DefaultItemAnimator());
        bind.rvWork.setAdapter(mWorkAdapter);
         /*viewModel.getPopularMovies()
                .observe(this, listResource -> dataBinding.setResource(listResource));*/
    }




    /*@Override
    public void onMovieClicked(MovieEntity movieEntity, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
        //startActivity(MovieDetailActivity.newIntent(getActivity(), movieEntity.getId()), options.toBundle());
    }*/
}
