package com.base.app.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class OsinItem {

    @SerializedName("osin_full_name")
    private String osinFullName;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("osin_id")
    private String osinId;

    public String getOsinFullName() {
        return osinFullName;
    }

    public void setOsinFullName(String osinFullName) {
        this.osinFullName = osinFullName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOsinId() {
        return osinId;
    }

    public void setOsinId(String osinId) {
        this.osinId = osinId;
    }

    @Override
    public String toString() {
        return
                "OsinItem{" +
                        "osin_full_name = '" + osinFullName + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",osin_id = '" + osinId + '\'' +
                        "}";
    }
}