package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.view.View;

import com.robdich.hideabletoolbarsample.R;
import com.robdich.hideabletoolbarsample.fragment.ListViewFragment;

/**
 * Created by Robert on 2/26/2015.
 */
public class ToolbarListViewActivity extends BaseNavDrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_listview);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ListViewFragment fragment = new ListViewFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }

    }

    @Override
    protected CharSequence getActivityTitle() {
        return getResources().getString(R.string.activity_toolbar_listview);
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar_actionbar;
    }

    @Override
    protected View getHideableView() {
        return findViewById(R.id.toolbar_actionbar);
    }

    protected int getDrawerItemPostion(){
        return DRAWER_ITEM_1;
    }

}
