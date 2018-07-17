package com.base.app.model.joblasted;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class JobNewItem {

	@SerializedName("distance")
	private double distance;

	@SerializedName("diffTime")
	private String diffTime;

	@SerializedName("owner_id")
	private int ownerId;

	@SerializedName("fee")
	private int fee;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("title")
	private String title;

	@SerializedName("osin_job_staus")
	private int osinJobStaus;

	@SerializedName("owner_job_id")
	private int ownerJobId;

	@SerializedName("job_img")
	private List<JobImg> jobImg;

	@SerializedName("job_description")
	private String jobDescription;

	@SerializedName("job_name")
	private String jobName;

	@SerializedName("job_id")
	private String jobId;

	@SerializedName("district")
	private String district;

	@SerializedName("longitude")
	private double longitude;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(String diffTime) {
		this.diffTime = diffTime;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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

	public int getOsinJobStaus() {
		return osinJobStaus;
	}

	public void setOsinJobStaus(int osinJobStaus) {
		this.osinJobStaus = osinJobStaus;
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

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return
				"JobNewDetailItem{" +
						"distance = '" + distance + '\'' +
						",diffTime = '" + diffTime + '\'' +
						",owner_id = '" + ownerId + '\'' +
						",fee = '" + fee + '\'' +
						",latitude = '" + latitude + '\'' +
						",created_at = '" + createdAt + '\'' +
						",title = '" + title + '\'' +
						",osin_job_staus = '" + osinJobStaus + '\'' +
						",owner_job_id = '" + ownerJobId + '\'' +
						",job_img = '" + jobImg + '\'' +
						",job_description = '" + jobDescription + '\'' +
						",job_name = '" + jobName + '\'' +
						",job_id = '" + jobId + '\'' +
						",district = '" + district + '\'' +
						",longitude = '" + longitude + '\'' +
						"}";
	}
}