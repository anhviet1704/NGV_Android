package com.base.app.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.base.app.R;
import com.base.app.base.BaseFragment;
import com.base.app.databinding.FragmentJobBinding;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.ivankocijan.magicviews.views.MagicTextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class JobFragment extends BaseFragment<WorkListFragmentVM, FragmentJobBinding> {

    @BindView(R.id.tv_work_time)
    MagicTextView tvWorkTime;
    @BindView(R.id.tv_time)
    MagicTextView tvTime;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_job_name)
    MagicTextView tvJobName;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.tv_job_place)
    MagicTextView tvJobPlace;
    @BindView(R.id.tv_job_list)
    MagicTextView tvJobList;
    @BindView(R.id.tv_job_register)
    MagicTextView tvJobRegister;
    @BindView(R.id.tv_job_finish)
    MagicTextView tvJobFinish;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.view_content)
    FrameLayout viewContent;
    Unbinder unbinder;
    public static ViewGroup viewRoot;
    @BindView(R.id.tv_working)
    MagicTextView tvWorking;

    public static JobFragment newInstance() {
        Bundle bundle = new Bundle();
        JobFragment fragment = new JobFragment();
        //fragment.setArguments(bundle);
        return fragment;
    }

    public static ViewGroup getRoot() {
        return viewRoot;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_job;
    }

    @Override
    protected Class<WorkListFragmentVM> getViewModel() {
        return WorkListFragmentVM.class;
    }

    @Override
    protected void onInit(Bundle instance) {
        viewRoot = bind.viewRoot;
        /*viewModel.getUser("JakeWharton").observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                updateUI(binding, user);
            }
        });*/
        onSetupView();

    }

    private void onSetupView() {
        getChildFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.view_content, JobListFragment.newInstance(), null)
                .commit();

    }

    private void onClearSelectBottomBar() {
        tvJobList.setTextColor(Color.parseColor("#E5F0FF"));
        tvJobRegister.setTextColor(Color.parseColor("#E5F0FF"));
        tvJobFinish.setTextColor(Color.parseColor("#E5F0FF"));
        tvJobList.setBackgroundColor(Color.parseColor("#B8CBE5"));
        tvJobRegister.setBackgroundColor(Color.parseColor("#B8CBE5"));
        tvJobFinish.setBackgroundColor(Color.parseColor("#B8CBE5"));
    }

    @OnClick({R.id.tv_job_list, R.id.tv_job_register, R.id.tv_job_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_job_list:
                onClearSelectBottomBar();
                tvJobList.setBackgroundColor(Color.parseColor("#84B8FF"));
                tvJobList.setTextColor(Color.parseColor("#FFFFFF"));
                getChildFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view_content, JobListFragment.newInstance(), null)
                        .commit();
                break;
            case R.id.tv_job_register:
                onClearSelectBottomBar();
                tvJobRegister.setBackgroundColor(Color.parseColor("#84B8FF"));
                tvJobRegister.setTextColor(Color.parseColor("#FFFFFF"));
                getChildFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view_content, JobRegisterFragment.newInstance(), null)
                        .commit();
                break;
            case R.id.tv_job_finish:
                onClearSelectBottomBar();
                tvJobFinish.setBackgroundColor(Color.parseColor("#84B8FF"));
                tvJobFinish.setTextColor(Color.parseColor("#FFFFFF"));
                getChildFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.view_content, JobFinishFragment.newInstance(), null)
                        .commit();
                break;
        }
    }
}
