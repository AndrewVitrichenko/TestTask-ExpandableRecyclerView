package com.company.expandablerecyclerview.util.recyclerview;

import java.util.List;

/**
 * Created by Andrew on 14.01.2018.
 */

public interface ParentListItem {

    List<?> getChildItemList();

    boolean isInitiallyExpanded();
}