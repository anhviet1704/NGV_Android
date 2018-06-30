package com.base.app.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentJobBinding;
import com.base.app.databinding.FragmentNotificationBinding;
import com.base.app.model.WorkItem;
import com.base.app.ui.adapter.NotificationAdapter;
import com.base.app.ui.adapter.TimeLineAdapter;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.viewmodel.WorkListFragmentVM;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends BaseFragment<WorkListFragmentVM, FragmentNotificationBinding> {

    private List<WorkItem> mDataList = new ArrayList<>();

    public static NotificationFragment newInstance() {
        Bundle bundle = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_notification;
    }

    @Override
    protected Class<WorkListFragmentVM> getViewModel() {
        return WorkListFragmentVM.class;
    }

    @Override
    protected void onCreate(Bundle instance, WorkListFragmentVM viewModel) {
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

        bind.rvNotification.setLayoutManager(new LinearLayoutManager(getActivity()));
        bind.rvNotification.setHasFixedSize(true);
        NotificationAdapter mAdapter = new NotificationAdapter(getContext(), mDataList, new OnClickItem() {
            @Override
            public void onClickItem(View v, int pos) {

            }
        });
        bind.rvNotification.setAdapter(mAdapter);
    }
}
