package com.toya.gitsearcher.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by AXIOO on 10/30/2017.
 */

public class SearchResultModel {

    @SerializedName("items")
    ArrayList<SearchItem> items;

    public ArrayList<SearchItem> getItems() {
        return items;
    }
}
