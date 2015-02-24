package com.robdich.hideabletoolbar.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Robert on 2/24/2015.
 */
public class ObserveableRecyclerView extends RecyclerView{

    public ObserveableRecyclerView(Context context){
        super(context);
    }

    public ObserveableRecyclerView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public ObserveableRecyclerView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    public void setTopPadding(int paddingTop){
        setPadding(getPaddingLeft(),
                getPaddingTop() + paddingTop,
                getPaddingRight(),
                getPaddingBottom());
        scrollToPosition(0);
    }
}
