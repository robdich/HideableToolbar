package com.robdich.hideabletoolbarsample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.view.ObserveableRecyclerView;
import com.robdich.hideabletoolbarsample.R;

/**
 * Created by Robert on 3/11/2015.
 */
public class RecyclerViewFragment extends Fragment {

    private ObserveableRecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private IScrollObserver mScrollObserver;

    private static final int ITEM_COUNT = 30;
    private static final int[] listItems = new int[ITEM_COUNT];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < ITEM_COUNT; i++) {
            listItems[i] = i;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_recyclerview, container, false);
        mRecyclerView = (ObserveableRecyclerView) rootView.findViewById(R.id.recylerView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new RecylerViewAdapter());

        if (mScrollObserver != null && mRecyclerView != null){
            mScrollObserver.observeScrollable(mRecyclerView);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mScrollObserver = (IScrollObserver) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IScrollObserver");
        }
    }


    private class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {

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
            holder.textView.setText("RecyclerViewItem " + n);
        }

        @Override
        public int getItemCount() {
            return listItems.length;
        }
    }

}
