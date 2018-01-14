package com.company.expandablerecyclerview.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.company.expandablerecyclerview.database.AppDatabase;
import com.company.expandablerecyclerview.database.dao.ItemsDao;
import com.company.expandablerecyclerview.network.ItemsApi;
import com.company.expandablerecyclerview.ui.ExpandableListContract;
import com.company.expandablerecyclerview.ui.ItemsRepository;
import com.company.expandablerecyclerview.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 14.01.2018.
 */

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context mContext) {
        return Room.databaseBuilder(mContext,
                AppDatabase.class, Constants.DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    ItemsDao provideItemsDao(AppDatabase mDatabase) {
        return mDatabase.getItemsDao();
    }


    @Provides
    @Singleton
    ExpandableListContract.Model provideItemsRepository(ItemsDao mDao, ItemsApi mApi) {
        return new ItemsRepository(mDao, mApi);
    }

}
