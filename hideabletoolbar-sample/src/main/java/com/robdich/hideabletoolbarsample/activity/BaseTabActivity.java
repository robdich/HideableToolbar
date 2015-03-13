package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.robdich.hideabletoolbarsample.R;
import com.robdich.hideabletoolbarsample.view.SlidingTabLayout;

/**
 * Created by Robert on 3/11/2015.
 */
public class BaseTabActivity extends BaseNavDrawerActivity {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    private Fragment mFragment1;
    private Fragment mFragment2;

    private static final int FRAGMENTS_COUNT = 2;
    private static final String PAGE_ONE = "TAB 1";
    private static final String PAGE_TWO = "TAB 2";
    private static final String TAG_FRAGMENT1 = "fragment1";
    private static final String TAG_FRAGMENT2 = "fragment2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.theme_accent_color));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                showOrHideActionBar(true);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar_actionbar;
    }

    @Override
    protected View getHideableView() {
        return (findViewById(R.id.header_bar));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // orientation changes.
        if (mFragment1 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FRAGMENT1,
                    mFragment1);
        }

        if (mFragment2 != null) {
            getSupportFragmentManager().putFragment(outState, TAG_FRAGMENT2,
                    mFragment2);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mFragment1 == null) {
            mFragment1 = getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FRAGMENT1);
        }

        if (mFragment2 == null) {
            mFragment2 = getSupportFragmentManager()
                    .getFragment(savedInstanceState, TAG_FRAGMENT2);
        }
    }

    protected Fragment getNewFragmentInstance(){
        return null;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return (mFragment1 = getNewFragmentInstance());
                case 1:
                    return (mFragment2 = getNewFragmentInstance());
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
