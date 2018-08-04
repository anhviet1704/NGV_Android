package com.base.app.automap;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AddressResponse {

    @SerializedName("predictions")
    private List<PredictionsItem> predictions;

    @SerializedName("status")
    private String status;

    public void setPredictions(List<PredictionsItem> predictions) {
        this.predictions = predictions;
    }

    public List<PredictionsItem> getPredictions() {
        return predictions;
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
                "AddressResponse{" +
                        "predictions = '" + predictions + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}