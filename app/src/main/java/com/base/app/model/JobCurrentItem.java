package com.base.app.model;

import com.base.app.model.joblasted.JobImg;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class JobCurrentItem {

    @SerializedName("end_date")
    private String endDate;

    @SerializedName("address")
    private String address;

    @SerializedName("osin_job_id")
    private int osinJobId;

    @SerializedName("owner_id")
    private int ownerId;

    @SerializedName("fee")
    private double fee;

    @SerializedName("end_time")
    private String endTime;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("title")
    private String title;

    @SerializedName("owner_job_id")
    private int ownerJobId;

    @SerializedName("job_img")
    private List<JobImg> jobImg;

    @SerializedName("start_time")
    private String startTime;

    @SerializedName("name")
    private String name;

    @SerializedName("owner_fullname")
    private String ownerFullname;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOsinJobId() {
        return osinJobId;
    }

    public void setOsinJobId(int osinJobId) {
        this.osinJobId = osinJobId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwnerJobId() {
        return ownerJobId;
    }

    public void setOwnerJobId(int ownerJobId) {
        this.ownerJobId = ownerJobId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerFullname() {
        return ownerFullname;
    }

    public void setOwnerFullname(String ownerFullname) {
        this.ownerFullname = ownerFullname;
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
                "JobCurrentItem{" +
                        "end_date = '" + endDate + '\'' +
                        ",address = '" + address + '\'' +
                        ",osin_job_id = '" + osinJobId + '\'' +
                        ",owner_id = '" + ownerId + '\'' +
                        ",fee = '" + fee + '\'' +
                        ",end_time = '" + endTime + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",title = '" + title + '\'' +
                        ",owner_job_id = '" + ownerJobId + '\'' +
                        ",job_img = '" + jobImg + '\'' +
                        ",start_time = '" + startTime + '\'' +
                        ",name = '" + name + '\'' +
                        ",owner_fullname = '" + ownerFullname + '\'' +
                        ",start_date = '" + startDate + '\'' +
                        "}";
    }
}