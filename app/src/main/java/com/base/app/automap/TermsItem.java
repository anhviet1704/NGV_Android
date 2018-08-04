package com.base.app.automap;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TermsItem {

    @SerializedName("offset")
    private int offset;

    @SerializedName("value")
    private String value;

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return
                "TermsItem{" +
                        "offset = '" + offset + '\'' +
                        ",value = '" + value + '\'' +
                        "}";
    }
}