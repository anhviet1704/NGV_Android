package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class RegisterItem {

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

    @SerializedName("identity_id")
    private String identityId;

    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("identity_img")
    private String identityImg;

    @SerializedName("score")
    private String score;

    @SerializedName("profile_img")
    private String profileImg;

    @SerializedName("family_register_img")
    private String familyRegisterImg;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("phone")
    private String phone;

    @SerializedName("id")
    private int id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private String status;

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setIdentityImg(String identityImg) {
        this.identityImg = identityImg;
    }

    public String getIdentityImg() {
        return identityImg;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setFamilyRegisterImg(String familyRegisterImg) {
        this.familyRegisterImg = familyRegisterImg;
    }

    public String getFamilyRegisterImg() {
        return familyRegisterImg;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "RegisterItem{" +
                        "birthday = '" + birthday + '\'' +
                        ",country = '" + country + '\'' +
                        ",address = '" + address + '\'' +
                        ",role = '" + role + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",identity_id = '" + identityId + '\'' +
                        ",description = '" + description + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",avatar = '" + avatar + '\'' +
                        ",identity_img = '" + identityImg + '\'' +
                        ",score = '" + score + '\'' +
                        ",profile_img = '" + profileImg + '\'' +
                        ",family_register_img = '" + familyRegisterImg + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",id = '" + id + '\'' +
                        ",fullname = '" + fullname + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}