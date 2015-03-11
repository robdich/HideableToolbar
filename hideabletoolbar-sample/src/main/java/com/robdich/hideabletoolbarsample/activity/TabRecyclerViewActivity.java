package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.robdich.hideabletoolbarsample.fragment.RecyclerViewFragment;

/**
 * Created by Robert on 3/11/2015.
 */
public class TabRecyclerViewActivity extends BaseTabActivity{

    private RecyclerViewFragment mRecyclerViewFragment1;
    private RecyclerViewFragment mRecyclerViewFragment2;

    private static final int FRAGMENTS_COUNT = 2;
    private static final String PAGE_ONE = "TAB 1";
    private static final String PAGE_TWO = "TAB 2";
    private static final String TAG_FEED = "fragment_list1";
    private static final String TAG_NOTIFICATIONS = "fragment_list2";

    @Override
    protected int getDrawerItemPostion() {
        return DRAWER_ITEM_5;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // orientation changes.
        if (mRecyclerViewFragment1 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FEED,
                    mRecyclerViewFragment1);
        }

        if (mRecyclerViewFragment2 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_NOTIFICATIONS,
                    mRecyclerViewFragment2);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mRecyclerViewFragment1 == null) {
            mRecyclerViewFragment1 = (RecyclerViewFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FEED);
        }

        if (mRecyclerViewFragment2 == null) {
            mRecyclerViewFragment2 = (RecyclerViewFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_NOTIFICATIONS);
        }
    }

    @Override
    protected FragmentPagerAdapter getPagerAdapter() {
        return new ViewPagerAdapter(getSupportFragmentManager());
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return (mRecyclerViewFragment1 = new RecyclerViewFragment());
                case 1:
                    return (mRecyclerViewFragment2 = new RecyclerViewFragment());
            }

            return null;
        }

        @Override
        public int getCount() {
            return FRAGMENTS_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0: return PAGE_ONE;
                case 1: return PAGE_TWO;
            }

            return null;
        }
    }

}
