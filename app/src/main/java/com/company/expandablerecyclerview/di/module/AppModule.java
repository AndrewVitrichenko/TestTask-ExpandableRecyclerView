package com.company.expandablerecyclerview.di.module;

import android.content.Context;

import com.company.expandablerecyclerview.util.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 14.01.2018.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext.getApplicationContext();
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager(Context mContext) {
        return new NetworkManager(mContext);
    }
}
