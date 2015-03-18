package com.robdich.hideabletoolbar.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbar.scrollobserver.IScrollableCallbacks;

/**
 * Created by Robert on 2/24/2015.
 */
public class ObserveableRecyclerView extends RecyclerView implements IScrollable{

    private IScrollableCallbacks mScrollableCallbacks;
    private int mTopClearance;
    private int mOriginalTopPadding = -1;

    public ObserveableRecyclerView(Context context){
        super(context);
        init();
    }

    public ObserveableRecyclerView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public ObserveableRecyclerView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    public void setScrollableCallbacks(IScrollableCallbacks scrollableCallbacks){
        this.mScrollableCallbacks = scrollableCallbacks;
    }

    /**
     * Sets the additonal top padding so the first items will not be clipped due the actionbar
     * overlaying the Scrollable object
     * @param clearance amount of additional padding
     */
    public void setTopClearance(int clearance){
        mTopClearance = clearance;
        //This is to make sure were only getting the original top padding the first time.
        if(mOriginalTopPadding == -1) {
            mOriginalTopPadding = getPaddingTop();
        }
        setPadding(getPaddingLeft(),
                mOriginalTopPadding + clearance,
                getPaddingRight(),
                getPaddingBottom());
    }

    private void init(){
        setOnScrollListener(new OnScrollListener() {
            int scrollAmount = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0) {
                    scrollAmount = 0;
                    if(mScrollableCallbacks != null){
                        mScrollableCallbacks.onScrollUp();
                    }
                } else {
                    // Add the amount of scroll.
                    // If the amount is more than the additional top padding call onScrollDown
                    // This is to avoid showing the additional top padding.
                    scrollAmount += dy;
                    if(scrollAmount > mTopClearance){
                        if(mScrollableCallbacks != null){
                            mScrollableCallbacks.onScrollDown();
                        }
                    }
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View firstChild = getChildAt(0);
                int top = firstChild.getTop();
                if(top > 0){
                    if(mScrollableCallbacks != null){
                        mScrollableCallbacks.onScrollUp();
                    }
                }
            }
        });
    }

}
