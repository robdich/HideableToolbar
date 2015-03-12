package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.view.View;

import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbarsample.R;
import com.robdich.hideabletoolbarsample.fragment.ScrollViewFragment;

/**
 * Created by Robert on 2/27/2015.
 */
public class ToolbarScrollViewActivity extends BaseNavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_scrollview);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ScrollViewFragment fragment = new ScrollViewFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();

        }
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

    @Override
    public void observeScrollable(IScrollable scrollable) {
        super.observeScrollable(scrollable);
    }
}
