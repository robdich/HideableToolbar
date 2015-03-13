package com.robdich.hideabletoolbarsample.activity;

import android.support.v4.app.Fragment;

import com.robdich.hideabletoolbarsample.fragment.ScrollViewFragment;

/**
 * Created by Robert on 3/12/2015.
 */
public class TabScrollViewActivity extends BaseTabActivity{

    @Override
    protected int getDrawerItemPostion() {
        return BaseNavDrawerActivity.DRAWER_ITEM_6;
    }

    @Override
    protected Fragment getNewFragmentInstance() {
        return new ScrollViewFragment();
    }
}
