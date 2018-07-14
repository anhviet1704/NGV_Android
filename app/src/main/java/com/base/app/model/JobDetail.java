package com.base.app.model;

import com.base.app.model.joblasted.JobImg;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class JobDetail {

    @SerializedName("end_date")
    private String endDate;

    @SerializedName("owner_id")
    private String ownerId;

    @SerializedName("fee")
    private double fee;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("owner_address")
    private String ownerAddress;

    @SerializedName("title")
    private String title;

    @SerializedName("owner_job_id")
    private String ownerJobId;

    @SerializedName("owner_full_name")
    private String ownerFullName;

    @SerializedName("job_address")
    private String jobAddress;

    @SerializedName("job_img")
    private List<JobImg> jobImg;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("osin_job_status")
    private int osinJobStatus = -1;

    @SerializedName("job_name")
    private String jobName;

    @SerializedName("job_description")
    private String jobDescription;

    @SerializedName("osin")
    private List<OsinItem> osin;

    @SerializedName("start_date")
    private String startDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerJobId() {
        return ownerJobId;
    }

    public void setOwnerJobId(String ownerJobId) {
        this.ownerJobId = ownerJobId;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public List<JobImg> getJobImg() {
        return jobImg;
    }

    public void setJobImg(List<JobImg> jobImg) {
        this.jobImg = jobImg;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getOsinJobStatus() {
        return osinJobStatus;
    }

    public void setOsinJobStatus(int osinJobStatus) {
        this.osinJobStatus = osinJobStatus;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<OsinItem> getOsin() {
        return osin;
    }

    public void setOsin(List<OsinItem> osin) {
        this.osin = osin;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return
                "JobDetail{" +
                        "end_date = '" + endDate + '\'' +
                        ",owner_id = '" + ownerId + '\'' +
                        ",fee = '" + fee + '\'' +
                        ",end_time = '" + endTime + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",owner_address = '" + ownerAddress + '\'' +
                        ",title = '" + title + '\'' +
                        ",owner_job_id = '" + ownerJobId + '\'' +
                        ",owner_full_name = '" + ownerFullName + '\'' +
                        ",job_address = '" + jobAddress + '\'' +
                        ",job_img = '" + jobImg + '\'' +
                        ",start_time = '" + startTime + '\'' +
                        ",osin_job_status = '" + osinJobStatus + '\'' +
                        ",job_name = '" + jobName + '\'' +
                        ",job_description = '" + jobDescription + '\'' +
                        ",osin = '" + osin + '\'' +
                        ",start_date = '" + startDate + '\'' +
                        "}";
    }
}