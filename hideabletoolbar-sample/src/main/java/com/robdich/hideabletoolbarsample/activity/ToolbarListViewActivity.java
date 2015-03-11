package com.robdich.hideabletoolbarsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.robdich.hideabletoolbar.view.ObserveableListView;
import com.robdich.hideabletoolbarsample.R;

/**
 * Created by Robert on 2/26/2015.
 */
public class ToolbarListViewActivity extends BaseNavDrawerActivity{

    private ObserveableListView mListView;

    private static final int ITEM_COUNT = 30;
    private static final int[] listItems = new int[ITEM_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_listview);

        for (int i = 0; i < ITEM_COUNT; i++) {
            listItems[i] = i;
        }

        mListView = (ObserveableListView) findViewById(R.id.listView);
        SimpleListAdapter adapter = new SimpleListAdapter(this);
        mListView.setAdapter(adapter);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        observeScrollable(mListView);
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

    private class SimpleListAdapter extends ArrayAdapter<Integer>{

        public SimpleListAdapter(Activity context){
            super(context, R.layout.text_item_layout);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if(view == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.text_item_layout, parent, false);
            }

            ((TextView) view.findViewById(R.id.text_item)).setText("Item " + listItems[position]);

            return view;
        }

        @Override
        public int getCount() {
            return listItems.length;
        }
    }

}
