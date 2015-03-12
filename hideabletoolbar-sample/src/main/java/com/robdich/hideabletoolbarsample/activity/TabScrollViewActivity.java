package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.robdich.hideabletoolbarsample.fragment.ScrollViewFragment;

/**
 * Created by Robert on 3/12/2015.
 */
public class TabScrollViewActivity extends BaseTabActivity{

    private ScrollViewFragment mScrollViewFragment1;
    private ScrollViewFragment mScrollViewFragment2;

    private static final int FRAGMENTS_COUNT = 2;
    private static final String PAGE_ONE = "TAB 1";
    private static final String PAGE_TWO = "TAB 2";
    private static final String TAG_FRAGMENT1 = "fragment1";
    private static final String TAG_FRAGMENT2 = "fragment2";

    @Override
    protected int getDrawerItemPostion() {
        return BaseNavDrawerActivity.DRAWER_ITEM_6;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // orientation changes.
        if (mScrollViewFragment1 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FRAGMENT1,
                    mScrollViewFragment1);
        }

        if (mScrollViewFragment2 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FRAGMENT2,
                    mScrollViewFragment2);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mScrollViewFragment1 == null) {
            mScrollViewFragment1 = (ScrollViewFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FRAGMENT1);
        }

        if (mScrollViewFragment2 == null) {
            mScrollViewFragment2 = (ScrollViewFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FRAGMENT2);
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
                    return (mScrollViewFragment1 = new ScrollViewFragment());
                case 1:
                    return (mScrollViewFragment2 = new ScrollViewFragment());
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
