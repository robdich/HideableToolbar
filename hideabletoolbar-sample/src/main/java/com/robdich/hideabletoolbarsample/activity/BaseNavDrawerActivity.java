package com.robdich.hideabletoolbarsample.activity;

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
import com.robdich.hideabletoolbarsample.R;

/**
 * Created by Robert on 3/9/2015.
 */
public class BaseNavDrawerActivity extends HideableToolbarActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ViewGroup mListItemsContainer;

    protected static final int DRAWER_ITEM_1 = 0;
    protected static final int DRAWER_ITEM_2 = 1;
    protected static final int DRAWER_ITEM_3 = 2;
    protected static final int DRAWER_ITEM_4 = 3;
    protected static final int DRAWER_ITEM_5 = 4;
    protected static final int DRAWER_ITEM_6 = 5;

    private static final int[] ITEM_IDS = new int[]{
            DRAWER_ITEM_1, DRAWER_ITEM_2, DRAWER_ITEM_3,
            DRAWER_ITEM_4, DRAWER_ITEM_5, DRAWER_ITEM_6
    };

    private static final String[] ITEM_TITLES = new String[]{
            "Toolbar ListView",
            "Toolbar RecyclerView",
            "Toolbar ScrollView",
            "Tab ListView",
            "Tab RecyclerView",
            "Tab ScrollView"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        for(int id : ITEM_IDS){
            TextView view = (TextView) getLayoutInflater()
                    .inflate(R.layout.drawer_list_item, mListItemsContainer, false);
            view.setText(ITEM_TITLES[id]);
            final int itemId = id;
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
            case DRAWER_ITEM_1:
                intent = new Intent(this, ToolbarListViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case DRAWER_ITEM_2:
                intent = new Intent(this, ToolbarRecyclerViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case DRAWER_ITEM_3:
                intent = new Intent(this, ToolbarScrollViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case DRAWER_ITEM_4:
                intent = new Intent(this, TabListViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case DRAWER_ITEM_5:
                intent = new Intent(this, TabRecyclerViewActivity.class);
                startActivity(intent);
                finish();
                break;

            case DRAWER_ITEM_6:
                intent = new Intent(this, TabScrollViewActivity.class);
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
