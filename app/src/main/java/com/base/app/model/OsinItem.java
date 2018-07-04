package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class OsinItem {

    @SerializedName("osin_full_name")
    private String osinFullName;

    @SerializedName("osin_id")
    private String osinId;

    public void setOsinFullName(String osinFullName) {
        this.osinFullName = osinFullName;
    }

    public String getOsinFullName() {
        return osinFullName;
    }

    public void setOsinId(String osinId) {
        this.osinId = osinId;
    }

    public String getOsinId() {
        return osinId;
    }

    @Override
    public String toString() {
        return
                "OsinItem{" +
                        "osin_full_name = '" + osinFullName + '\'' +
                        ",osin_id = '" + osinId + '\'' +
                        "}";
    }
}