package com.company.expandablerecyclerview.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrew on 14.01.2018.
 */

public class MainResponse {

    @SerializedName("response")
    List<ParentItem> items;

    MainResponse() {
    }

    public List<ParentItem> getItems() {
        return items;
    }

    public void setItems(List<ParentItem> items) {
        this.items = items;
    }
}
