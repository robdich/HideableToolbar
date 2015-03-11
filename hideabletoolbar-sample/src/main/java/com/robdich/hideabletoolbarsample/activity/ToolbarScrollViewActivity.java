package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.robdich.hideabletoolbar.view.ObserveableScrollView;
import com.robdich.hideabletoolbarsample.R;

/**
 * Created by Robert on 2/27/2015.
 */
public class ToolbarScrollViewActivity extends BaseNavDrawerActivity {

    private ObserveableScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_scrollview);
        mScrollView = (ObserveableScrollView) findViewById(R.id.scrolView);
        LinearLayout container = (LinearLayout) findViewById(R.id.content_container);

        for(int i = 0; i < 30; i++) {
            View view;
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.text_item_layout, null);
            container.addView(view);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        observeScrollable(mScrollView);
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar_actionbar;
    }

    @Override
    protected View getHideableView() {
        return findViewById(R.id.toolbar_actionbar);
    }

    protected int getDrawerItemPostion(){
        return DRAWER_ITEM_3;
    }

}
