package com.company.expandablerecyclerview.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.company.expandablerecyclerview.database.dao.ItemsDao;
import com.company.expandablerecyclerview.entity.ParentItem;
import com.company.expandablerecyclerview.util.Constants;
import com.company.expandablerecyclerview.util.ItemConverter;

/**
 * Created by Andrew on 14.01.2018.
 */

@Database(entities = {ParentItem.class}, version = Constants.DATABASE_VERSION, exportSchema = false)
@TypeConverters({ItemConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemsDao getItemsDao();

}
