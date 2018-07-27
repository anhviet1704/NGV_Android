package com.base.app.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class UploadItem {

    @SerializedName("file_name_upload")
    private String fileNameUpload;

    @SerializedName("image_name")
    private String imageName;

    @SerializedName("image_url")
    private String imageUrl;

    public String getFileNameUpload() {
        return fileNameUpload;
    }

    public void setFileNameUpload(String fileNameUpload) {
        this.fileNameUpload = fileNameUpload;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return
                "UploadItem{" +
                        "file_name_upload = '" + fileNameUpload + '\'' +
                        ",image_name = '" + imageName + '\'' +
                        ",image_url = '" + imageUrl + '\'' +
                        "}";
    }
}