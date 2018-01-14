package com.company.expandablerecyclerview.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.expandablerecyclerview.R;
import com.company.expandablerecyclerview.entity.ChildItem;
import com.company.expandablerecyclerview.entity.ParentItem;
import com.company.expandablerecyclerview.util.Constants;
import com.company.expandablerecyclerview.util.recyclerview.ExpandableRecyclerAdapter;
import com.company.expandablerecyclerview.util.recyclerview.ParentListItem;
import com.company.expandablerecyclerview.util.recyclerview.ParentViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andrew on 14.01.2018.
 */

public class ExpandableListAdapter extends ExpandableRecyclerAdapter<ExpandableListAdapter.ParentItemViewHolder,
        ExpandableListAdapter.ChildItemViewHolder> {

    private List<ParentItem> parentItems;

    public ExpandableListAdapter(List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.parentItems = (List<ParentItem>) parentItemList;
    }

    @Override
    public ParentItemViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View parentItemView = LayoutInflater.from(parentViewGroup.getContext()).
                inflate(R.layout.item_parent, parentViewGroup, false);
        return new ParentItemViewHolder(parentItemView);
    }

    @Override
    public ChildItemViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View childItemView = LayoutInflater.from(childViewGroup.getContext()).
                inflate(R.layout.item_child, childViewGroup, false);
        return new ChildItemViewHolder(childItemView);
    }

    @Override
    public void onBindParentViewHolder(ParentItemViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        ParentItem parentItem = (ParentItem) parentListItem;
        parentViewHolder.bind(parentItem);
    }

    @Override
    public void onBindChildViewHolder(ChildItemViewHolder childViewHolder, int position, Object childListItem) {
        ChildItem childItem = (ChildItem) childListItem;
        childViewHolder.bind(childItem);
        childViewHolder.mCheckBox.setOnCheckedChangeListener(null);
        childViewHolder.mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                childItem.setChecked(true);
            } else {
                childItem.setChecked(false);
            }
        });
        childViewHolder.mCheckBox.setChecked(childItem.isChecked());
    }

    public class ChildItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_view_child_name)
        TextView mChildNameTextView;
        @BindView(R.id.checkbox)
        CheckBox mCheckBox;

        public ChildItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCheckBox.setClickable(false);
            itemView.setOnClickListener(this);
        }

        public void bind(ChildItem childItem) {
            mChildNameTextView.setText(childItem.getName());
        }

        @Override
        public void onClick(View view) {
            ChildItem childItem = (ChildItem) getListItem(getAdapterPosition());
            if (childItem.isChecked()) {
                childItem.setChecked(false);
                mCheckBox.setChecked(false);
            } else {
                childItem.setChecked(true);
                mCheckBox.setChecked(true);
            }
            setOtherItemsToDefaultState(childItem);
            notifyDataSetChanged();
        }
    }

    public class ParentItemViewHolder extends ParentViewHolder {


        @BindView(R.id.image_view_arrow_expand)
        ImageView mArrowExpandImageView;
        @BindView(R.id.text_view_parent_name)
        TextView mParentNameTextView;

        public ParentItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ParentItem parentItem) {
            mParentNameTextView.setText(parentItem.getName());
        }

        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);

            if (expanded) {
                mArrowExpandImageView.setRotation(Constants.ROTATED_POSITION);
            } else {
                mArrowExpandImageView.setRotation(Constants.INITIAL_POSITION);
            }

        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            super.onExpansionToggled(expanded);

            RotateAnimation rotateAnimation;
            if (expanded) {
                rotateAnimation = new RotateAnimation(Constants.ROTATED_POSITION,
                        Constants.INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else {
                rotateAnimation = new RotateAnimation(-1 * Constants.ROTATED_POSITION,
                        Constants.INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(Constants.ANIMATION_DURATION);
            rotateAnimation.setFillAfter(true);
            mArrowExpandImageView.startAnimation(rotateAnimation);
        }
    }

    public void setOtherItemsToDefaultState(ChildItem item) {
        for (ParentItem parentItem : parentItems) {
            for (ChildItem childItem : parentItem.getChildItems()) {
                if (childItem.getId() != item.getId())
                    childItem.setChecked(false);
            }
        }
    }

}

