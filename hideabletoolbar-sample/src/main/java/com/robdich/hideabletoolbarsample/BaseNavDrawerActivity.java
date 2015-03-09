package com.robdich.hideabletoolbarsample;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robdich.hideabletoolbar.HideableToolbarActivity;

/**
 * Created by Robert on 3/9/2015.
 */
public class BaseNavDrawerActivity extends HideableToolbarActivity {

    private String[] mActivityTitles;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ViewGroup mListItemsContainer;

    protected static final int DRAWER_ITEM_1 = 0;
    protected static final int DRAWER_ITEM_2 = 1;
    protected static final int DRAWER_ITEM_3 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityTitles = getResources().getStringArray(R.array.actitivy_titles);
    }

    protected void setupNavDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final CharSequence title = getActivityTitle();
        final CharSequence drawerTitle = getString(R.string.app_name);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                getToolbar(), R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu();
                onNavDrawerStateChanged(false, false);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
                onNavDrawerStateChanged(true, false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                onNavDrawerStateChanged(isNavDrawerOpen(),
                        newState != DrawerLayout.STATE_IDLE);
            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mListItemsContainer = (ViewGroup) findViewById(R.id.drawer_container);
        int i = 0;
        for(String item : mActivityTitles){
            TextView view = (TextView) getLayoutInflater()
                    .inflate(R.layout.drawer_list_item, mListItemsContainer, false);
            view.setText(item);
            final int itemId = i++;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemSelected(itemId);
                }
            });
            mListItemsContainer.addView(view);
        }

    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    private void onItemSelected(int position){
        mDrawerLayout.closeDrawer(Gravity.START);

        if(getDrawerItemPostion() == position) return;

        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(this, SimpleListViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case 1:
                intent = new Intent(this, SimpleRecyclerViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case 2:
                intent = new Intent(this, SimpleScrollViewActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    protected int getDrawerItemPostion(){
        return DRAWER_ITEM_1;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
