package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LoginItem {

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("phone")
    private String phone;

    @SerializedName("id")
    private int id;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("email")
    private String email;

    @SerializedName("token")
    private String token;

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return
                "LoginItem{" +
                        "birthday = '" + birthday + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",id = '" + id + '\'' +
                        ",fullname = '" + fullname + '\'' +
                        ",email = '" + email + '\'' +
                        ",token = '" + token + '\'' +
                        "}";
    }
}