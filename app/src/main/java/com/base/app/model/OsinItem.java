package com.base.app.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class OsinItem {

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("role_name")
    private String roleName;

    @SerializedName("osin_full_name")
    private String osinFullName;

    @SerializedName("role_id")
    private String roleId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("osin_id")
    private String osinId;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOsinFullName() {
        return osinFullName;
    }

    public void setOsinFullName(String osinFullName) {
        this.osinFullName = osinFullName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
                        "birthday = '" + birthday + '\'' +
                        ",role_name = '" + roleName + '\'' +
                        ",osin_full_name = '" + osinFullName + '\'' +
                        ",role_id = '" + roleId + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",osin_id = '" + osinId + '\'' +
                        "}";
    }
}