package com.robdich.hideabletoolbar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbar.scrollobserver.IScrollableCallbacks;

/**
 * Created by Robert on 2/25/2015.
 */
public class ObserveableListView extends ListView implements IScrollable{

    private IScrollableCallbacks mScrollableCallbacks;
    private int mTopClearance;

    private int mScrollAmount = 0;
    private View mPrevFirstVisibleChild;
    private int mPreFirstVisibleChildPosition;
    private int mPrevFirstVisibleChildTop;

    public ObserveableListView(Context context){
        super(context);
    }

    public ObserveableListView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public ObserveableListView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    public void setScrollableCallbacks(IScrollableCallbacks scrollableCallbacks) {
        this.mScrollableCallbacks = scrollableCallbacks;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        int deltaY = 0;

        if (mPrevFirstVisibleChild == null) {
            if (getChildCount() > 0) {
                View child = getChildAt(getFirstVisiblePosition());
                if(child != null) {
                    mPrevFirstVisibleChild = child;
                    mPrevFirstVisibleChildTop = mPrevFirstVisibleChild.getTop();
                    mPreFirstVisibleChildPosition = getPositionForView(mPrevFirstVisibleChild);
                }
            }
        } else {
            if (mPrevFirstVisibleChild.getParent() == this &&
                    getPositionForView(mPrevFirstVisibleChild) == mPreFirstVisibleChildPosition) {
                int top = mPrevFirstVisibleChild.getTop();
                deltaY = mPrevFirstVisibleChildTop - top;
                mPrevFirstVisibleChildTop = top;
            } else {
                mPrevFirstVisibleChild = null;
            }
        }

        //Ignores scroll events from other listviews in case of multiple listviews
        if(deltaY != 0) {
            computeScroll(deltaY);
        }
    }

    private void computeScroll(int deltaY){
        if (deltaY < 0) {
            mScrollAmount = 0;
            if(mScrollableCallbacks != null){
                mScrollableCallbacks.onScrollUp();
            }
        } else {
            // Add the amount of scroll.
            // If the amount is more than the additional top padding call onScrollDown
            // This is to avoid showing the additional top padding.
            mScrollAmount += deltaY;
            if(mScrollAmount > mTopClearance){
                if(mScrollableCallbacks != null){
                    mScrollableCallbacks.onScrollDown();
                }
            }
        }
    }

    /**
     * Sets the additonal top padding so the first items will not be clipped due the actionbar
     * overlaying the Scrollable object
     * @param clearance amount of additional padding
     */
    @Override
    public void setTopClearance(int clearance) {
        mTopClearance = clearance;
        setPadding(getPaddingLeft(),
                getPaddingTop() + clearance,
                getPaddingRight(),
                getPaddingBottom());
    }

}
