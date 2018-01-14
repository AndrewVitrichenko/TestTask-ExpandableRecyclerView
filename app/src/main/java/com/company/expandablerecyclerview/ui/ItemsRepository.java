package com.company.expandablerecyclerview.ui;

import com.company.expandablerecyclerview.database.dao.ItemsDao;
import com.company.expandablerecyclerview.entity.MainResponse;
import com.company.expandablerecyclerview.entity.ParentItem;
import com.company.expandablerecyclerview.network.ItemsApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrew on 14.01.2018.
 */

public class ItemsRepository implements ExpandableListContract.Model {

    private ItemsDao mDao;
    private ItemsApi mApi;

    public ItemsRepository(ItemsDao mDao, ItemsApi mApi) {
        this.mDao = mDao;
        this.mApi = mApi;
    }

    @Override
    public void insertItems(List<ParentItem> parentItems) {
        Observable.just(parentItems)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(mDao::insertItems);
    }

    @Override
    public Single<List<ParentItem>> getItemsFromDatabase() {
        return mDao.getItems();
    }

    @Override
    public Observable<MainResponse> getItemsFromNetwork() {
        return mApi.getMainResponse();
    }

}
