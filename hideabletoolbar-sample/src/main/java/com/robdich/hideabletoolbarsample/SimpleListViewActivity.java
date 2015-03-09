package com.robdich.hideabletoolbarsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.robdich.hideabletoolbar.view.ObserveableListView;

/**
 * Created by Robert on 2/26/2015.
 */
public class SimpleListViewActivity extends BaseNavDrawerActivity{

    private ObserveableListView mListView;

    private static final int ITEM_COUNT = 30;
    private static final int[] listItems = new int[ITEM_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_listview);

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
        return getResources().getString(R.string.activity_simple_listview);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hideable_toolbar_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
