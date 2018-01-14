package com.company.expandablerecyclerview.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.company.expandablerecyclerview.entity.ParentItem;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Andrew on 14.01.2018.
 */

@Dao
public interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(List<ParentItem> parentItems);

    @Query("SELECT * FROM items")
    Single<List<ParentItem>> getItems();

}
