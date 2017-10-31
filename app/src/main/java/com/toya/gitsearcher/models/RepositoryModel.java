package com.toya.gitsearcher.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class RepositoryModel {

    @SerializedName("name")
    String name;
    @SerializedName("stargazers_count")
    int star;
    @SerializedName("forks_count")
    int forks;
    @SerializedName("pushed_at")
    String date;
    @SerializedName("size")
    int size;

    public String getName() {
        return name;
    }

    public int getStar() {
        return star;
    }

    public int getForks() {
        return forks;
    }

    public String getDate() {
        return date;
    }

    public int getSize() {
        return size;
    }
}
