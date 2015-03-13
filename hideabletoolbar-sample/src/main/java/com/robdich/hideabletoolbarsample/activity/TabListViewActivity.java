package com.robdich.hideabletoolbarsample.activity;

import android.support.v4.app.Fragment;

import com.robdich.hideabletoolbarsample.fragment.ListViewFragment;

/**
 * Created by Robert on 3/11/2015.
 */
public class TabListViewActivity extends BaseTabActivity {

    @Override
    protected int getDrawerItemPostion() {
        return DRAWER_ITEM_4;
    }

    @Override
    protected Fragment getNewFragmentInstance() {
        return new ListViewFragment();
    }
}
