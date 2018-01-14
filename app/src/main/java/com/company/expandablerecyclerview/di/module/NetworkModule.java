package com.company.expandablerecyclerview.di.module;

import com.company.expandablerecyclerview.network.ItemsApi;
import com.company.expandablerecyclerview.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andrew on 14.01.2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }


    @Provides
    @Singleton
    ItemsApi provideItemsApi(GsonConverterFactory gsonConverterFactory,
                                          RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.API)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build()
                .create(ItemsApi.class);
    }

}
