package com.base.app.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentHomeBinding;
import com.base.app.model.RoleItem;
import com.base.app.ui.callback.OnClickItem;
import com.base.app.utils.DialogSearch;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;

import java.util.ArrayList;
import java.util.List;

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
        final List<RoleItem> mArr = new ArrayList<>();
        mArr.add(new RoleItem(1, "AAAAA"));
        mArr.add(new RoleItem(1, "BBBB"));
        mArr.add(new RoleItem(1, "cơn mưa"));
        mArr.add(new RoleItem(1, "nhà"));
        mArr.add(new RoleItem(1, "lối về"));
        mArr.add(new RoleItem(1, "asdasdsad"));
        mArr.add(new RoleItem(1, "gfgfsdffsd"));
        mArr.add(new RoleItem(1, "ggbghrg"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "saq"));
        mArr.add(new RoleItem(1, "eewseee"));
        mArr.add(new RoleItem(1, "eaeeee"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "eqw"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "e"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "neeebbbee"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "eeneee"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "mmmmmmmmmmmm"));
        mArr.add(new RoleItem(1, "eeeasjdklajslee"));
        mArr.add(new RoleItem(1, "asdas"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "eeeee"));
        mArr.add(new RoleItem(1, "ewrewrwer"));
        mArr.add(new RoleItem(1, "werwetyty"));
        mArr.add(new RoleItem(1, "oiuojkj"));
        mArr.add(new RoleItem(1, "qweqweqw"));
        mArr.add(new RoleItem(1, "xxzcx"));
        mArr.add(new RoleItem(1, "qqqqqqqqweeeeeew"));
        mArr.add(new RoleItem(1, "eeytryrteee"));
        mArr.add(new RoleItem(1, "uio"));
        mArr.add(new RoleItem(1, "AAAuuuuu"));
        bind.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSearch mDialogSearch = new DialogSearch(getContext());
                mDialogSearch.onShowSearch(new OnClickItem() {
                    @Override
                    public void onClickItem(View v, int pos) {

                    }
                });
                mDialogSearch.show();
                mDialogSearch.setDataSearch(mArr);
            }
        });
    }
}
