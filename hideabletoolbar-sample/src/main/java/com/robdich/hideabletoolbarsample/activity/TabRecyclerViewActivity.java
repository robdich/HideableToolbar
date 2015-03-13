package com.robdich.hideabletoolbarsample.activity;

import android.support.v4.app.Fragment;

import com.robdich.hideabletoolbarsample.fragment.RecyclerViewFragment;

/**
 * Created by Robert on 3/11/2015.
 */
public class TabRecyclerViewActivity extends BaseTabActivity{

    @Override
    protected int getDrawerItemPostion() {
        return DRAWER_ITEM_5;
    }

    @Override
    protected Fragment getNewFragmentInstance() {
        return new RecyclerViewFragment();
    }
}
