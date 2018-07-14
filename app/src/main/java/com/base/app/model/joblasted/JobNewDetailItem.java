package com.base.app.model.joblasted;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class JobNewDetailItem {

    @SerializedName("job_img")
    private List<JobImg> jobImg;

    @SerializedName("job_name")
    private String jobName;

    @SerializedName("job_description")
    private String jobDescription;

    @SerializedName("diffTime")
    private String diffTime;

    @SerializedName("job_id")
    private String jobId;

    @SerializedName("owner_id")
    private String ownerId;

    @SerializedName("district")
    private String district;

    @SerializedName("fee")
    private String fee;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("title")
    private String title;

    @SerializedName("osin_job_staus")
    private String osinJobStaus;

    @SerializedName("owner_job_id")
    private int ownerJobId;

    public List<JobImg> getJobImg() {
        return jobImg;
    }

    public void setJobImg(List<JobImg> jobImg) {
        this.jobImg = jobImg;
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

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }

    public String getDiffTime() {
        return diffTime;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOsinJobStaus() {
        return osinJobStaus;
    }

    public void setOsinJobStaus(String osinJobStaus) {
        this.osinJobStaus = osinJobStaus;
    }

    public int getOwnerJobId() {
        return ownerJobId;
    }

    public void setOwnerJobId(int ownerJobId) {
        this.ownerJobId = ownerJobId;
    }

    @Override
    public String toString() {
        return
                "JobNewDetailItem{" +
                        "job_img = '" + jobImg + '\'' +
                        ",job_name = '" + jobName + '\'' +
                        ",job_description = '" + jobDescription + '\'' +
                        ",diffTime = '" + diffTime + '\'' +
                        ",job_id = '" + jobId + '\'' +
                        ",owner_id = '" + ownerId + '\'' +
                        ",district = '" + district + '\'' +
                        ",fee = '" + fee + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",title = '" + title + '\'' +
                        ",osin_job_staus = '" + osinJobStaus + '\'' +
                        ",owner_job_id = '" + ownerJobId + '\'' +
                        "}";
    }
}