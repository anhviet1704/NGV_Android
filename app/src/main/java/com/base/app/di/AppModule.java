package com.base.app.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.base.app.BuildConfig;
import com.base.app.data.ApiServices;
import com.base.app.model.LoginItem;
import com.base.app.module.ViewModelModule;
import com.base.app.utils.PrefHelper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = {ViewModelModule.class})
public class AppModule {

    static String mToken = "";
    static LoginItem mLoginItem;

    @Provides
    @Singleton
    @NonNull
    String provideToken() {
        return mToken;
    }

    public void setToken(String newToken) {
        mToken = newToken;
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    ApiServices provideApiServices(Retrofit retrofit) {
        return retrofit.create(ApiServices.class);
    }

    @Singleton
    @Provides
    OkHttpClient provideRetrofit(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("token", mToken)
                                .method(original.method(), original.body()).build();
                        return chain.proceed(request);
                    }
                }).build();
    }

    @Singleton
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    PrefHelper providesPrefHelper(Application application) {
        return new PrefHelper(application);
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    @Provides
    LoginItem providesLoginItem() {
        if (mLoginItem == null)
            return new LoginItem();
        else
            return mLoginItem;
    }

    public static void setLoginItem(LoginItem session) {
        mLoginItem = session;
        if (mLoginItem != null)
            mToken = mLoginItem.getToken();
    }

}
