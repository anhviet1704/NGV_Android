package com.base.app.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.base.app.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @SuppressWarnings("unused")
    @Query("SELECT * FROM UserEntity")
    Flowable<List<UserEntity>> getAllUser();

    @Query("SELECT * FROM UserEntity WHERE userId = :userId")
    Flowable<UserEntity> getUserDetail(String userId);

    @Insert
    long[] addUser(UserEntity... userEntity);
}
