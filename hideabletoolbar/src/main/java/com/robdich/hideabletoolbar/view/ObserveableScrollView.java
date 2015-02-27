package com.robdich.hideabletoolbar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbar.scrollobserver.IScrollableCallbacks;

/**
 * Created by Robert on 2/27/2015.
 */
public class ObserveableScrollView extends ScrollView implements IScrollable {

    private IScrollableCallbacks mScrollableCallbacks;
    private int mTopClearance;
    private int mScrollAmount;

    public ObserveableScrollView(Context context){
        super(context);
    }

    public ObserveableScrollView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public ObserveableScrollView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    public void setScrollableCallbacks(IScrollableCallbacks scrollableCallbacks) {
        this.mScrollableCallbacks = scrollableCallbacks;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        int deltaY = t - oldt;
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
