package com.robdich.hideabletoolbar.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbar.scrollobserver.IScrollableCallbacks;

/**
 * Created by Robert on 2/24/2015.
 */
public class ObserveableRecyclerView extends RecyclerView implements IScrollable{

    private IScrollableCallbacks scrollableCallbacks;
    private int topClearance;

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
        this.scrollableCallbacks = scrollableCallbacks;
    }

    private void init(){
        setOnScrollListener(new OnScrollListener() {
            int scrollAmount = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0) {
                    scrollAmount = 0;
                    if(scrollableCallbacks != null){
                        scrollableCallbacks.onScrollDown();
                    }
                } else {
                    // Add the amount of scroll.
                    // If the amount is more than the additional top padding call scroll up
                    // This is to avoid showing the additional top padding.
                    scrollAmount += dy;
                    if(scrollAmount > topClearance){
                        if(scrollableCallbacks != null){
                            scrollableCallbacks.onScrollUp();
                        }
                    }
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void setTopClearance(int clearance){
        topClearance = clearance;
        setPadding(getPaddingLeft(),
                getPaddingTop() + clearance,
                getPaddingRight(),
                getPaddingBottom());
        // Call this method to avoid bug that clips the first items because of the additional padding
        // TO DO : Find another solution to the said bug.
        scrollToPosition(0);
    }

}
