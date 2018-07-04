package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class JobItem {

    @SerializedName("end_date")
    private String endDate;

    @SerializedName("job_img")
    private Object jobImg;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("address")
    private String address;

    @SerializedName("job_id")
    private int jobId;

    @SerializedName("fee")
    private String fee;

    @SerializedName("name")
    private String name;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("sub_job_id")
    private int subJobId;

    @SerializedName("start_date")
    private String startDate;

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setJobImg(Object jobImg) {
        this.jobImg = jobImg;
    }

    public Object getJobImg() {
        return jobImg;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee() {
        return fee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setSubJobId(int subJobId) {
        this.subJobId = subJobId;
    }

    public int getSubJobId() {
        return subJobId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return
                "JobItem{" +
                        "end_date = '" + endDate + '\'' +
                        ",job_img = '" + jobImg + '\'' +
                        ",start_time = '" + startTime + '\'' +
                        ",address = '" + address + '\'' +
                        ",job_id = '" + jobId + '\'' +
                        ",fee = '" + fee + '\'' +
                        ",name = '" + name + '\'' +
                        ",end_time = '" + endTime + '\'' +
                        ",sub_job_id = '" + subJobId + '\'' +
                        ",start_date = '" + startDate + '\'' +
                        "}";
    }
}