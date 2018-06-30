package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BaseValueItem {

    @SerializedName("value")
    private String value;

    @SerializedName("id:")
    private int id;


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


    @Override
    public String toString() {
        return
                "OfficeItem{" +
                        "value = '" + value + '\'' +
                        ",id: = '" + id + '\'' +
                        "}";
    }
}