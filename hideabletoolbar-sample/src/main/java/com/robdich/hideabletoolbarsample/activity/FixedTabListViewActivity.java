package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.robdich.hideabletoolbarsample.R;
import com.robdich.hideabletoolbarsample.fragment.ListViewFragment;

/**
 * Created by Robert on 3/17/2015.
 */
public class FixedTabListViewActivity extends BaseTabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowActionbarOnPageScroll(false);
    }

    @Override
    protected View getHideableView() {
        return (findViewById(R.id.toolbar_actionbar));
    }

    @Override
    protected View getTabView() {
        return (findViewById(R.id.sliding_tabs));
    }

    @Override
    protected int getDrawerItemPostion() {
        return DRAWER_ITEM_7;
    }

    @Override
    protected Fragment getNewFragmentInstance() {
        return new ListViewFragment();
    }

}

