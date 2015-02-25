package com.robdich.hideabletoolbar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbar.scrollobserver.IScrollableCallbacks;

/**
 * Created by Robert on 2/25/2015.
 */
public class ObserveableListView extends ListView implements IScrollable{

    private IScrollableCallbacks mScrollableCallbacks;
    private int mTopClearance;

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
