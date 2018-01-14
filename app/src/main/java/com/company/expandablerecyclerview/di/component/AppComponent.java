package com.company.expandablerecyclerview.di.component;

import com.company.expandablerecyclerview.di.module.AppModule;
import com.company.expandablerecyclerview.di.module.NetworkModule;
import com.company.expandablerecyclerview.di.module.PersistenceModule;
import com.company.expandablerecyclerview.di.module.PresentationModule;
import com.company.expandablerecyclerview.ui.ExpandableListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Andrew on 14.01.2018.
 */
@Component(modules = {AppModule.class, NetworkModule.class, PersistenceModule.class, PresentationModule.class})
@Singleton
public interface AppComponent {

    void inject(ExpandableListActivity target);
}
