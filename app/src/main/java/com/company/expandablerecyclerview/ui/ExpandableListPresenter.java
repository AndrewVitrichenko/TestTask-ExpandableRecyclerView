package com.company.expandablerecyclerview.ui;

import com.company.expandablerecyclerview.entity.MainResponse;
import com.company.expandablerecyclerview.entity.ParentItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Andrew on 14.01.2018.
 */

public class ExpandableListPresenter implements ExpandableListContract.Presenter {

    private ExpandableListContract.Model mRepository;
    private ExpandableListContract.View mView;

    public ExpandableListPresenter(ExpandableListContract.Model mRepository) {
        this.mRepository = mRepository;
    }

    public void bindView(ExpandableListContract.View mView) {
        this.mView = mView;
    }

    public void unbindView() {
        this.mView = null;
    }

    @Override
    public void loadDataFromNetwork() {
        Observable<MainResponse> observable = mRepository.getItemsFromNetwork();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainResponse -> {
                            mRepository.insertItems(mainResponse.getItems());
                            showData(mainResponse.getItems());
                        },
                        throwable -> loadDataFromDatabase());
    }

    @Override
    public void loadDataFromDatabase() {
        mRepository.getItemsFromDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showData, throwable -> {
                    if (mView != null) {
                        mView.showNoItemsMessage();
                    }
                });
    }

    private void showData(List<ParentItem> parentItems) {
        if (mView != null) {
            mView.showData(parentItems);
        }
    }
}
