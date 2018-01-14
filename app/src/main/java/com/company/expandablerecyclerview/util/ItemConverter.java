package com.company.expandablerecyclerview.util;

import android.arch.persistence.room.TypeConverter;

import com.company.expandablerecyclerview.entity.ChildItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Andrew on 14.01.2018.
 */

public class ItemConverter {

    @TypeConverter
    public static String toStr(List<ChildItem> childItems) {
        Gson gson = new Gson();
        return gson.toJson(childItems);
    }

    @TypeConverter
    public static List<ChildItem> fromStr(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<ChildItem>>() {
        }.getType());
    }
}
