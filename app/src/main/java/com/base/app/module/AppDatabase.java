package com.base.app.module;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.base.app.dao.UserDao;
import com.base.app.entity.UserEntity;


@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
