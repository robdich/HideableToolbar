package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentPagerAdapter pagerAdapter = getPagerAdapter(); //= new ViewPagerAdapter(getSupportFragmentManager());
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

    protected FragmentPagerAdapter getPagerAdapter(){
        return null;
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar_actionbar;
    }

    @Override
    protected View getHideableView() {
        return (findViewById(R.id.header_bar));
    }

}
