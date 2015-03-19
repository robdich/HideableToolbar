package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.robdich.hideabletoolbarsample.R;
import com.robdich.hideabletoolbarsample.fragment.ScrollViewFragment;

/**
 * Created by robert on 3/19/2015.
 */
public class FixedTabScrollViewActivity extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowActionbarOnPageScroll(false);
    }

    /**
     * Sets only the toolbar as the hideableView instead of the entire
     * viewgroup containing the toolbar + tabs
     * @return toolbar view
     */
    @Override
    protected View getHideableView() {
        return (findViewById(R.id.toolbar_actionbar));
    }

    /**
     * Sets the tab view. Doing this creates a fixed tab effect (or sticky tabs)
     * @return tab view
     */
    @Override
    protected View getTabView() {
        return (findViewById(R.id.sliding_tabs));
    }

    @Override
    protected int getDrawerItemPostion() {
        return DRAWER_ITEM_9;
    }

    @Override
    protected Fragment getNewFragmentInstance() {
        return new ScrollViewFragment();
    }

}
