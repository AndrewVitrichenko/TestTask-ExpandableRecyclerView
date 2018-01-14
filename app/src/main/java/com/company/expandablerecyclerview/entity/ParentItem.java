package com.company.expandablerecyclerview.entity;

import android.arch.persistence.room.Entity;

import com.company.expandablerecyclerview.util.recyclerview.ParentListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrew on 14.01.2018.
 */

@Entity(tableName = "items")
public class ParentItem extends ChildItem implements ParentListItem {

    @SerializedName("children")
    private List<ChildItem> childItems;

    public ParentItem() {
    }

    public List<ChildItem> getChildItems() {
        return childItems;
    }

    public void setChildItems(List<ChildItem> childItems) {
        this.childItems = childItems;
    }

    @Override
    public List<?> getChildItemList() {
        return childItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
