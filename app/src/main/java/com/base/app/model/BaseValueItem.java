package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class BaseValueItem {

    @SerializedName("id:")
    private String id;
    @SerializedName("value")
    private String value;

    public BaseValueItem(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
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