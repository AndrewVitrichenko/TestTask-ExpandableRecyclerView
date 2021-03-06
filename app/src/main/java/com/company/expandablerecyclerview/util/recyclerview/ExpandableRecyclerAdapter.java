package com.company.expandablerecyclerview.util.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Andrew on 14.01.2018.
 */

public abstract class ExpandableRecyclerAdapter<PVH extends ParentViewHolder, CVH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ParentViewHolder.ParentListItemExpandCollapseListener {

    private static final int TYPE_PARENT = 0;
    private static final int TYPE_CHILD = 1;

    protected List<Object> mItemList;

    private List<RecyclerView> mAttachedRecyclerViewPool;


    public ExpandableRecyclerAdapter(List<? extends ParentListItem> parentItemList) {
        super();
        mItemList = ExpandableRecyclerAdapterHelper.generateParentChildItemList(parentItemList);
        mAttachedRecyclerViewPool = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_PARENT) {
            PVH pvh = onCreateParentViewHolder(viewGroup);
            pvh.setParentListItemExpandCollapseListener(this);
            return pvh;
        } else if (viewType == TYPE_CHILD) {
            return onCreateChildViewHolder(viewGroup);
        } else {
            throw new IllegalStateException("Incorrect ViewType found");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object listItem = getListItem(position);
        if (listItem instanceof ParentWrapper) {
            PVH parentViewHolder = (PVH) holder;

            if (parentViewHolder.shouldItemViewClickToggleExpansion()) {
                parentViewHolder.setMainItemClickToExpand();
            }

            ParentWrapper parentWrapper = (ParentWrapper) listItem;
            parentViewHolder.setExpanded(parentWrapper.isExpanded());
            onBindParentViewHolder(parentViewHolder, position, parentWrapper.getParentListItem());
        } else if (listItem == null) {
            throw new IllegalStateException("Incorrect ViewHolder found");
        } else {
            onBindChildViewHolder((CVH) holder, position, listItem);
        }
    }

    public abstract PVH onCreateParentViewHolder(ViewGroup parentViewGroup);

    public abstract CVH onCreateChildViewHolder(ViewGroup childViewGroup);

    public abstract void onBindParentViewHolder(PVH parentViewHolder, int position, ParentListItem parentListItem);

    public abstract void onBindChildViewHolder(CVH childViewHolder, int position, Object childListItem);

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    @Override
    public int getItemViewType(int position) {
        Object listItem = getListItem(position);
        if (listItem instanceof ParentWrapper) {
            return TYPE_PARENT;
        } else if (listItem == null) {
            throw new IllegalStateException("Null object added");
        } else {
            return TYPE_CHILD;
        }
    }

    @Override
    public void onParentListItemExpanded(int position) {
        Object listItem = getListItem(position);
        if (listItem instanceof ParentWrapper) {
            expandParentListItem((ParentWrapper) listItem, position);
        }
    }


    @Override
    public void onParentListItemCollapsed(int position) {
        Object listItem = getListItem(position);
        if (listItem instanceof ParentWrapper) {
            collapseParentListItem((ParentWrapper) listItem, position);
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mAttachedRecyclerViewPool.add(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mAttachedRecyclerViewPool.remove(recyclerView);
    }


    protected Object getListItem(int position) {
        boolean indexInRange = position >= 0 && position < mItemList.size();
        if (indexInRange) {
            return mItemList.get(position);
        } else {
            return null;
        }
    }


    private void expandParentListItem(ParentWrapper parentWrapper, int parentIndex) {
        if (!parentWrapper.isExpanded()) {
            parentWrapper.setExpanded(true);

            List<?> childItemList = parentWrapper.getChildItemList();
            if (childItemList != null) {
                int childListItemCount = childItemList.size();
                for (int i = 0; i < childListItemCount; i++) {
                    mItemList.add(parentIndex + i + 1, childItemList.get(i));
                }
                notifyItemRangeInserted(parentIndex + 1, childListItemCount);
            }
        }
    }

    private void collapseParentListItem(ParentWrapper parentWrapper, int parentIndex) {
        if (parentWrapper.isExpanded()) {
            parentWrapper.setExpanded(false);

            List<?> childItemList = parentWrapper.getChildItemList();
            if (childItemList != null) {
                int childListItemCount = childItemList.size();
                for (int i = childListItemCount - 1; i >= 0; i--) {
                    mItemList.remove(parentIndex + i + 1);
                }
                notifyItemRangeRemoved(parentIndex + 1, childListItemCount);
            }
        }
    }

}
