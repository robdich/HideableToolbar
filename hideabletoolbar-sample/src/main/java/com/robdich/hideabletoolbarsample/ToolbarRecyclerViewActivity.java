package com.robdich.hideabletoolbarsample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robdich.hideabletoolbar.view.ObserveableRecyclerView;

/**
 * Created by Robert on 2/24/2015.
 */
public class ToolbarRecyclerViewActivity extends BaseNavDrawerActivity {

    private ObserveableRecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final int ITEM_COUNT = 30;
    private static final int[] listItems = new int[ITEM_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_recyclerview);

        for(int i = 0; i < ITEM_COUNT; i++){
            listItems[i] = i;
        }

        mRecyclerView = (ObserveableRecyclerView) findViewById(R.id.recylerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new SimpleRecylerViewAdapter());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        observeScrollable(mRecyclerView);
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

    private class SimpleRecylerViewAdapter extends RecyclerView.Adapter<SimpleRecylerViewAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(View v) {
                super(v);
                textView = (TextView) v.findViewById(R.id.text_item);
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_item_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int n = listItems[position];
            holder.textView.setText("Item " + n);
        }

        @Override
        public int getItemCount() {
            return listItems.length;
        }
    }

}
