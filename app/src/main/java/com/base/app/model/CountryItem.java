package com.base.app.model;

public class CountryItem {
    private String id;
    private String name;

    public CountryItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
