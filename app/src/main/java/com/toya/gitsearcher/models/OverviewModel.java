package com.toya.gitsearcher.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class OverviewModel {

    @SerializedName("name")
    String name;
    @SerializedName("login")
    String login;
    @SerializedName("bio")
    String bio;
    @SerializedName("followers")
    int followers;
    @SerializedName("following")
    int following;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("location")
    String location;
    @SerializedName("avatar_url")
    String avatarUrl;
    @SerializedName("blog")
    String blog;

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getBio() {
        return bio;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLocation() {
        return location;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBlog() {
        return blog;
    }
}
