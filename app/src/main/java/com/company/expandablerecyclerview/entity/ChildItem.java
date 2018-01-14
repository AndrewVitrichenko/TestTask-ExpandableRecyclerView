package com.company.expandablerecyclerview.entity;

import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Andrew on 14.01.2018.
 */

public class ChildItem {

    @PrimaryKey
    private long id;
    private String name;
    private boolean checked = false;

    ChildItem(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
