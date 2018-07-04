package com.base.app.model;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class JobDetail {

    @SerializedName("end_date")
    private String endDate;

    @SerializedName("fee")
    private String fee;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("description")
    private String description;

    @SerializedName("owner_address")
    private String ownerAddress;

    @SerializedName("sub_job_id")
    private String subJobId;

    @SerializedName("owner_full_name")
    private String ownerFullName;

    @SerializedName("job_address")
    private String jobAddress;

    @SerializedName("job_img")
    private String jobImg;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("job_name")
    private String jobName;

    @SerializedName("job_id")
    private String jobId;

    @SerializedName("osin")
    private List<OsinItem> osin;

    @SerializedName("start_date")
    private String startDate;

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee() {
        return fee;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setSubJobId(String subJobId) {
        this.subJobId = subJobId;
    }

    public String getSubJobId() {
        return subJobId;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobImg(String jobImg) {
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

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setOsin(List<OsinItem> osin) {
        this.osin = osin;
    }

    public List<OsinItem> getOsin() {
        return osin;
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
                "JobDetail{" +
                        "end_date = '" + endDate + '\'' +
                        ",fee = '" + fee + '\'' +
                        ",end_time = '" + endTime + '\'' +
                        ",description = '" + description + '\'' +
                        ",owner_address = '" + ownerAddress + '\'' +
                        ",sub_job_id = '" + subJobId + '\'' +
                        ",owner_full_name = '" + ownerFullName + '\'' +
                        ",job_address = '" + jobAddress + '\'' +
                        ",job_img = '" + jobImg + '\'' +
                        ",start_time = '" + startTime + '\'' +
                        ",job_name = '" + jobName + '\'' +
                        ",job_id = '" + jobId + '\'' +
                        ",osin = '" + osin + '\'' +
                        ",start_date = '" + startDate + '\'' +
                        "}";
    }
}