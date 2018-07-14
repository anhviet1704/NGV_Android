package com.base.app.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentHomeBinding;
import com.base.app.ui.activity.WorkDetailActivity;
import com.base.app.ui.callback.OnClickSearch;
import com.base.app.utils.DialogSearch;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;

import org.greenrobot.eventbus.EventBus;

public class HomeFragment extends BaseFragment<WorkListFragmentVM, FragmentHomeBinding> {

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment fragment = new HomeFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected Class<WorkListFragmentVM> getViewModel() {
        return WorkListFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        FragmentPagerItems pages = FragmentPagerItems.with(getContext())
                .add(R.string.tv_home_001, WorkListFragment.class)
                .add(R.string.tv_home_002, WorkMapFragment.class)
                .create();
        FragmentStatePagerItemAdapter adapter = new FragmentStatePagerItemAdapter(getFragmentManager(), pages);

        bind.viewpager.setAdapter(adapter);
        bind.viewpagertab.setViewPager(bind.viewpager);
        bind.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSearch mDialogSearch = new DialogSearch(getContext());
                mDialogSearch.onShowSearch(new OnClickSearch() {
                    @Override
                    public void onClickItem(View v, String job_id) {
                        for (int i = 0; i < WorkListFragment.getData().size(); i++) {
                            if (job_id == WorkListFragment.getData().get(i).getJobId()) {
                                EventBus.getDefault().postSticky(WorkListFragment.getData().get(i));
                                Intent intent = new Intent(getContext(), WorkDetailActivity.class);
                                ActivityOptionsCompat options = ActivityOptionsCompat.makeClipRevealAnimation(v, 0, 0, 0, 0);
                                startActivity(intent, options.toBundle());
                                break;
                            }
                        }

                    }
                });
                mDialogSearch.show();
                mDialogSearch.setDataSearch(WorkListFragment.getData());
            }
        });
    }
}
