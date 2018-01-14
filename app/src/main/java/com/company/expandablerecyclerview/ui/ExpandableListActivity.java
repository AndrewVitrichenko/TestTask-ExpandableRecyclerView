package com.company.expandablerecyclerview.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.company.expandablerecyclerview.MainApplication;
import com.company.expandablerecyclerview.R;
import com.company.expandablerecyclerview.entity.ParentItem;
import com.company.expandablerecyclerview.util.NetworkManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandableListActivity extends AppCompatActivity implements ExpandableListContract.View {

    @BindView(R.id.expandable_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_view_no_items_message)
    TextView mTextViewNoItems;

    @Inject
    Context mContext;

    @Inject
    NetworkManager mNetworkManager;

    @Inject
    ExpandableListContract.Presenter mPresenter;

    private ExpandableListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MainApplication) getApplication()).getComponent().inject(this);
        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.bindView(this);
        if (mAdapter == null) {
            if (mNetworkManager.isOnline()) {
                mPresenter.loadDataFromNetwork();
            } else {
                mPresenter.loadDataFromDatabase();
            }
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }


    @Override
    public void showData(List<ParentItem> items) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mTextViewNoItems.setVisibility(View.GONE);
        mAdapter = new ExpandableListAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showNoItemsMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mTextViewNoItems.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

}
