package com.base.app.automap;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DetailResponse {

    @SerializedName("result")
    private Result result;

    @SerializedName("html_attributions")
    private List<Object> htmlAttributions;

    @SerializedName("status")
    private String status;

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
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
                "DetailResponse{" +
                        "result = '" + result + '\'' +
                        ",html_attributions = '" + htmlAttributions + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}