package com.base.app.model.joblasted;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class JobLastDetailItem {

    @SerializedName("job_img")
    private Object jobImg;

    @SerializedName("diffTime")
    private String diffTime;

    @SerializedName("job_id")
    private int jobId;

    @SerializedName("fee")
    private String fee;

    @SerializedName("district")
    private String district;

    @SerializedName("name")
    private String name;

    @SerializedName("sub_job_id")
    private int subJobId;

    public void setJobImg(Object jobImg) {
        this.jobImg = jobImg;
    }

    public Object getJobImg() {
        return jobImg;
    }

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }

    public String getDiffTime() {
        return diffTime;
    }


    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee() {
        return fee;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getSubJobId() {
        return subJobId;
    }

    public void setSubJobId(int subJobId) {
        this.subJobId = subJobId;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "job_img = '" + jobImg + '\'' +
                        ",diffTime = '" + diffTime + '\'' +
                        ",job_id = '" + jobId + '\'' +
                        ",fee = '" + fee + '\'' +
                        ",district = '" + district + '\'' +
                        ",name = '" + name + '\'' +
                        ",sub_job_id = '" + subJobId + '\'' +
                        "}";
    }
}