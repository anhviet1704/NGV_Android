package com.base.app.model;

import com.base.app.model.joblasted.JobImg;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class LoginItem {

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("country")
    private String country;

    @SerializedName("address")
    private String address;

    @SerializedName("role")
    private String role;

    @SerializedName("gender")
    private String gender;

    @SerializedName("is_new")
    private int isNew;

    @SerializedName("identity_id")
    private int identityId;

    @SerializedName("last_login")
    private String lastLogin;

    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("office")
    private String office;

    @SerializedName("token")
    private String token;

    @SerializedName("identity_img")
    private List<JobImg> identityImg;

    @SerializedName("score")
    private int score;

    @SerializedName("profile_img")
    private List<JobImg> profileImg;

    @SerializedName("family_register_img")
    private List<JobImg> familyRegisterImg;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("phone")
    private String phone;

    @SerializedName("id")
    private int id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("remember_token")
    private String rememberToken;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private int status;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIdentityId() {
        return identityId;
    }

    public void setIdentityId(int identityId) {
        this.identityId = identityId;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<JobImg> getIdentityImg() {
        return identityImg;
    }

    public void setIdentityImg(List<JobImg> identityImg) {
        this.identityImg = identityImg;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<JobImg> getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(List<JobImg> profileImg) {
        this.profileImg = profileImg;
    }

    public List<JobImg> getFamilyRegisterImg() {
        return familyRegisterImg;
    }

    public void setFamilyRegisterImg(List<JobImg> familyRegisterImg) {
        this.familyRegisterImg = familyRegisterImg;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "LoginItem{" +
                        "birthday = '" + birthday + '\'' +
                        ",country = '" + country + '\'' +
                        ",address = '" + address + '\'' +
                        ",role = '" + role + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",is_new = '" + isNew + '\'' +
                        ",identity_id = '" + identityId + '\'' +
                        ",last_login = '" + lastLogin + '\'' +
                        ",description = '" + description + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",office = '" + office + '\'' +
                        ",token = '" + token + '\'' +
                        ",identity_img = '" + identityImg + '\'' +
                        ",score = '" + score + '\'' +
                        ",profile_img = '" + profileImg + '\'' +
                        ",family_register_img = '" + familyRegisterImg + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",id = '" + id + '\'' +
                        ",fullname = '" + fullname + '\'' +
                        ",remember_token = '" + rememberToken + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}