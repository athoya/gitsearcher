package com.toya.gitsearcher.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class SearchItem {
    @SerializedName("login")
    String login;

    @SerializedName("url")
    String url;

    @SerializedName("avatar_url")
    String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
