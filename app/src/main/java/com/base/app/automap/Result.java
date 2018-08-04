package com.base.app.automap;

import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Result {

    @SerializedName("utc_offset")
    private int utcOffset;

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("types")
    private List<String> types;

    @SerializedName("icon")
    private String icon;

    @SerializedName("address_components")
    private List<AddressComponentsItem> addressComponents;

    @SerializedName("url")
    private String url;

    @SerializedName("reference")
    private String reference;

    @SerializedName("scope")
    private String scope;

    @SerializedName("name")
    private String name;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("id")
    private String id;

    @SerializedName("adr_address")
    private String adrAddress;

    @SerializedName("plus_code")
    private PlusCode plusCode;

    @SerializedName("place_id")
    private String placeId;

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setAddressComponents(List<AddressComponentsItem> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public List<AddressComponentsItem> getAddressComponents() {
        return addressComponents;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAdrAddress(String adrAddress) {
        this.adrAddress = adrAddress;
    }

    public String getAdrAddress() {
        return adrAddress;
    }

    public void setPlusCode(PlusCode plusCode) {
        this.plusCode = plusCode;
    }

    public PlusCode getPlusCode() {
        return plusCode;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    @Override
    public String toString() {
        return
                "Result{" +
                        "utc_offset = '" + utcOffset + '\'' +
                        ",formatted_address = '" + formattedAddress + '\'' +
                        ",types = '" + types + '\'' +
                        ",icon = '" + icon + '\'' +
                        ",address_components = '" + addressComponents + '\'' +
                        ",url = '" + url + '\'' +
                        ",reference = '" + reference + '\'' +
                        ",scope = '" + scope + '\'' +
                        ",name = '" + name + '\'' +
                        ",geometry = '" + geometry + '\'' +
                        ",id = '" + id + '\'' +
                        ",adr_address = '" + adrAddress + '\'' +
                        ",plus_code = '" + plusCode + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        "}";
    }
}