package com.base.app.automap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

public class PlaceItem {

    final String name;
    final String des;
    final String placeId;
    double lat;
    double lng;

    public PlaceItem(String name, String des, String placeId) {
        this.name = name;
        this.placeId = placeId;
        this.des = des;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getPlaceId() {
        return placeId;
    }

    @Override
    public String toString() {
        return name;
    }
}