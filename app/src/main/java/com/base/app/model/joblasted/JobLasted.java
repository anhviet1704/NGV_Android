package com.base.app.model.joblasted;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class JobLasted {

    @SerializedName("path")
    private String path;

    @SerializedName("per_page")
    private String perPage;

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<JobLastDetailItem> data;

    @SerializedName("last_page")
    private int lastPage;

    @SerializedName("next_page_url")
    private String nextPageUrl;

    @SerializedName("from")
    private int from;

    @SerializedName("to")
    private int to;

    @SerializedName("prev_page_url")
    private Object prevPageUrl;

    @SerializedName("current_page")
    private int currentPage;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setData(List<JobLastDetailItem> data) {
        this.data = data;
    }

    public List<JobLastDetailItem> getData() {
        return data;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public String toString() {
        return
                "JobLasted{" +
                        "path = '" + path + '\'' +
                        ",per_page = '" + perPage + '\'' +
                        ",total = '" + total + '\'' +
                        ",data = '" + data + '\'' +
                        ",last_page = '" + lastPage + '\'' +
                        ",next_page_url = '" + nextPageUrl + '\'' +
                        ",from = '" + from + '\'' +
                        ",to = '" + to + '\'' +
                        ",prev_page_url = '" + prevPageUrl + '\'' +
                        ",current_page = '" + currentPage + '\'' +
                        "}";
    }
}