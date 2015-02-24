package com.robdich.hideabletoolbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Robert on 2/24/2015.
 */

// This is a base activity that the HideableToolbarActivity inherits from.
// Add all necessary features here like Navigation Drawers, menus, etc.
public class BaseActivity extends ActionBarActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getToolbar();
        getSupportActionBar().setTitle(getActivityTitle());
    }

    protected Toolbar getToolbar(){
        if(toolbar == null){
            int res = getToolbarResId();
            if(res != -1){
                toolbar = (Toolbar) findViewById(res);
                if(toolbar != null) {
                    setSupportActionBar(toolbar);
                }
            }
        }
        return toolbar;
    }

    protected int getToolbarResId(){
        return -1;
    }

    protected CharSequence getActivityTitle(){
        return getTitle();
    }

    // Call this method when Navigation drawer is opened or closed.
    protected void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {
    }

}
