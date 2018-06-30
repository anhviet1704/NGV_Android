package com.base.app.model;

import com.base.app.entity.UserEntity;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("data")
    private UserEntity userEntity;


    public UserData() {
    }


    public UserData(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
