package com.robdich.hideabletoolbarsample.activity;

import android.os.Bundle;
import android.view.View;

import com.robdich.hideabletoolbarsample.R;
import com.robdich.hideabletoolbarsample.fragment.RecyclerViewFragment;

/**
 * Created by Robert on 2/24/2015.
 */
public class ToolbarRecyclerViewActivity extends BaseNavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_recyclerview);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            RecyclerViewFragment fragment = new RecyclerViewFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }
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
        return DRAWER_ITEM_2;
    }

}
