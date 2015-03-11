package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.robdich.hideabletoolbarsample.fragment.ListViewFragment;

/**
 * Created by Robert on 3/11/2015.
 */
public class TabListViewActivity extends BaseTabActivity {

    private ListViewFragment mListFragment1;
    private ListViewFragment mListFragment2;

    private static final int FRAGMENTS_COUNT = 2;
    private static final String PAGE_ONE = "TAB 1";
    private static final String PAGE_TWO = "TAB 2";
    private static final String TAG_FEED = "fragment_list1";
    private static final String TAG_NOTIFICATIONS = "fragment_list2";

    @Override
    protected int getDrawerItemPostion() {
        return DRAWER_ITEM_4;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // orientation changes.
        if (mListFragment1 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FEED,
                    mListFragment1);
        }

        if (mListFragment2 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_NOTIFICATIONS,
                    mListFragment2);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mListFragment1 == null) {
            mListFragment1 = (ListViewFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FEED);
        }

        if (mListFragment2 == null) {
            mListFragment2 = (ListViewFragment) getSupportFragmentManager()
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
                    return (mListFragment1 = new ListViewFragment());
                case 1:
                    return (mListFragment2 = new ListViewFragment());
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
