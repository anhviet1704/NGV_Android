package com.base.app.model;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CategoryItem {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private Object description;

    @SerializedName("rank")
    private String rank;

    @SerializedName("id")
    private int id;

    @SerializedName("tool")
    private String tool;

    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return description;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getTool() {
        return tool;
    }

    @Override
    public String toString() {
        return
                "CategoryItem{" +
                        "name = '" + name + '\'' +
                        ",description = '" + description + '\'' +
                        ",rank = '" + rank + '\'' +
                        ",id = '" + id + '\'' +
                        ",tool = '" + tool + '\'' +
                        "}";
    }
}