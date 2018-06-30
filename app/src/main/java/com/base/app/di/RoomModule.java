package com.base.app.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.base.app.dao.UserDao;
import com.base.app.data.ApiServices;
import com.base.app.module.AppDatabase;
import com.base.app.repo.RegisterRepo;
import com.base.app.repo.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RoomModule {

    @Singleton
    @Provides
    AppDatabase providesReauthDb(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "reauth.db").fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    UserDao providesUserDao(AppDatabase db) {
        return db.userDao();
    }

    @Singleton
    @Provides
    UserRepository providesUserRepo(AppDatabase db, ApiServices apiServices) {
        return new UserRepository(apiServices, db);
    }

    @Singleton
    @Provides
    RegisterRepo providesRegisterRepo(Retrofit retrofit, AppDatabase db, ApiServices apiServices) {
        return new RegisterRepo(apiServices, db);
    }
}
