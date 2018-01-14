package com.company.expandablerecyclerview;

import android.app.Application;

import com.company.expandablerecyclerview.di.component.AppComponent;
import com.company.expandablerecyclerview.di.component.DaggerAppComponent;
import com.company.expandablerecyclerview.di.module.AppModule;
import com.company.expandablerecyclerview.di.module.NetworkModule;
import com.company.expandablerecyclerview.di.module.PersistenceModule;
import com.company.expandablerecyclerview.di.module.PresentationModule;

/**
 * Created by Andrew on 14.01.2018.
 */

public class MainApplication extends Application {

    private AppComponent mAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppComponent getComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule())
                    .persistenceModule(new PersistenceModule())
                    .presentationModule(new PresentationModule())
                    .build();
        }
        return mAppComponent;
    }
}
