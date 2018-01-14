package com.company.expandablerecyclerview.ui;

import com.company.expandablerecyclerview.entity.MainResponse;
import com.company.expandablerecyclerview.entity.ParentItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Andrew on 14.01.2018.
 */

public interface ExpandableListContract {

    interface Model {

        void insertItems(List<ParentItem> parentItems);

        Single<List<ParentItem>> getItemsFromDatabase();

        Observable<MainResponse> getItemsFromNetwork();

    }

    interface View {

        void showData(List<ParentItem> items);

        void showNoItemsMessage();
    }

    interface Presenter {

        void loadDataFromNetwork();

        void loadDataFromDatabase();

        void bindView(ExpandableListContract.View mView);

        void unbindView();
    }

}
