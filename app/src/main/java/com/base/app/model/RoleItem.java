package com.base.app.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RoleItem {

    @SerializedName("value")
    private String value;

    @SerializedName("id:")
    private int id;

    public RoleItem(int id, String value) {
        this.value = value;
        this.id = id;
    }

    private boolean isCheck = false;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return
                "OfficeItem{" +
                        "value = '" + value + '\'' +
                        ",id: = '" + id + '\'' +
                        "}";
    }
}