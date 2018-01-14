package com.company.expandablerecyclerview.util.recyclerview;

import java.util.List;

/**
 * Created by Andrew on 14.01.2018.
 */

public class ParentWrapper {

    private boolean mExpanded;
    private ParentListItem mParentListItem;


    public ParentWrapper(ParentListItem parentListItem) {
        mParentListItem = parentListItem;
        mExpanded = false;
    }

    public ParentListItem getParentListItem() {
        return mParentListItem;
    }


    public boolean isExpanded() {
        return mExpanded;
    }


    public void setExpanded(boolean expanded) {
        mExpanded = expanded;
    }

    public boolean isInitiallyExpanded() {
        return mParentListItem.isInitiallyExpanded();
    }

    public List<?> getChildItemList() {
        return mParentListItem.getChildItemList();
    }
}