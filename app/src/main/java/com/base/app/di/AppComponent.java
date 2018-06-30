package com.base.app.di;

import android.app.Application;

import com.base.app.MyApp;
import com.base.app.module.ActivityBuilderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules = {
        AppModule.class,
        RoomModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MyApp aaApp);
}
