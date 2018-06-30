package com.base.app.model.postobj;

public class RegisterObj {
    private String phone;
    private String fullname;
    private String email;
    private String address;
    private String birthday;
    private int gender;
    private String identity_id;
    private String role;
    private String description;
    private String country;
    private String avatar;
    private int score;
    private String office;
    private String profile_img;
    private String identity_img;
    private String family_register_img;
    private int status;

    public RegisterObj() {
    }

    public RegisterObj(String phone, String fullname, String email, String address, String birthday, int gender, String identity_id, String role, String description, String country, String avatar, int score, String office, String profile_img, String identity_img, String family_register_img, int status) {
        this.phone = phone;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.identity_id = identity_id;
        this.role = role;
        this.description = description;
        this.country = country;
        this.avatar = avatar;
        this.score = score;
        this.office = office;
        this.profile_img = profile_img;
        this.identity_img = identity_img;
        this.family_register_img = family_register_img;
        this.status = status;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(String identity_id) {
        this.identity_id = identity_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getIdentity_img() {
        return identity_img;
    }

    public void setIdentity_img(String identity_img) {
        this.identity_img = identity_img;
    }

    public String getFamily_register_img() {
        return family_register_img;
    }

    public void setFamily_register_img(String family_register_img) {
        this.family_register_img = family_register_img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
