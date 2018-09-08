package com.base.app.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.base.app.di.FactoryViewModel;
import com.base.app.di.ViewModelKey;
import com.base.app.ui.fragment.JobFinishFragment;
import com.base.app.ui.fragment.JobRegisterFragment;
import com.base.app.viewmodel.ChangePassActivityVM;
import com.base.app.viewmodel.ForgotPassActivityVM;
import com.base.app.viewmodel.JobFinishFragmentVM;
import com.base.app.viewmodel.JobListFragmentVM;
import com.base.app.viewmodel.JobRegisterFragmentVM;
import com.base.app.viewmodel.LinkActivityVM;
import com.base.app.viewmodel.WalletActivityVM;
import com.base.app.viewmodel.WorkDetailActivityVM;
import com.base.app.viewmodel.WorkListFragmentVM;
import com.base.app.viewmodel.LoginActivityVM;
import com.base.app.viewmodel.MainActivityVM;
import com.base.app.viewmodel.NewPassActivityVM;
import com.base.app.viewmodel.RegisterActivityVM;
import com.base.app.viewmodel.VerifyActivityVM;
import com.base.app.viewmodel.WorkMapFragmentVM;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LinkActivityVM.class)
    abstract ViewModel LinkActivityVM(LinkActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WalletActivityVM.class)
    abstract ViewModel WalletActivityVM(WalletActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChangePassActivityVM.class)
    abstract ViewModel ChangePassActivityVM(ChangePassActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(JobFinishFragmentVM.class)
    abstract ViewModel JobFinishFragmentVM(JobFinishFragmentVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(JobRegisterFragmentVM.class)
    abstract ViewModel JobRegisterFragmentVM(JobRegisterFragmentVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(JobListFragmentVM.class)
    abstract ViewModel JobListFragmentVM(JobListFragmentVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WorkMapFragmentVM.class)
    abstract ViewModel WorkMapFragmentVM(WorkMapFragmentVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WorkDetailActivityVM.class)
    abstract ViewModel WorkDetailActivityVM(WorkDetailActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(WorkListFragmentVM.class)
    abstract ViewModel HomeFragmentVM(WorkListFragmentVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(VerifyActivityVM.class)
    abstract ViewModel VerifyActivityVM(VerifyActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterActivityVM.class)
    abstract ViewModel RegisterActivityVM(RegisterActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewPassActivityVM.class)
    abstract ViewModel NewPassActivityVM(NewPassActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPassActivityVM.class)
    abstract ViewModel ForgotPassActivityVM(ForgotPassActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityVM.class)
    abstract ViewModel LoginActivityVM(LoginActivityVM viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM.class)
    abstract ViewModel MainActivityVM(MainActivityVM viewModel);


    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(FactoryViewModel movieViewModelFactory);

}
