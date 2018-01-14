package com.company.expandablerecyclerview.di.module;

import com.company.expandablerecyclerview.ui.ExpandableListContract;
import com.company.expandablerecyclerview.ui.ExpandableListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Andrew on 14.01.2018.
 */

@Module
public class PresentationModule {

    @Provides
    @Singleton
    ExpandableListContract.Presenter provideExpandableListPresenter(ExpandableListContract.Model mRepository) {
        return new ExpandableListPresenter(mRepository);
    }

}
