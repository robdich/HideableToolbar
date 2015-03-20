package com.robdich.hideabletoolbarsample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.view.ObserveableListView;
import com.robdich.hideabletoolbarsample.R;

/**
 * Created by Robert on 3/11/2015.
 */
public class ListViewFragment extends Fragment {

    private ObserveableListView mListView;
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
        View rootView = inflater.inflate(R.layout.layout_listview, container, false);
        mListView = (ObserveableListView) rootView.findViewById(R.id.listView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListViewAdapter adapter = new ListViewAdapter(getActivity());
        mListView.setAdapter(adapter);

        mListView.setClickable(true);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Clicked Item " + position, Toast.LENGTH_SHORT).show();
            }
        });

        if (mScrollObserver != null && mListView != null){
            mScrollObserver.observeScrollable(mListView);
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

    private class ListViewAdapter extends ArrayAdapter<Integer> {

        public ListViewAdapter(Activity context){
            super(context, R.layout.text_item_layout);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;

            if(view == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.text_item_layout, parent, false);
            }

            ((TextView) view.findViewById(R.id.text_item)).setText("ListItem " + listItems[position]);

            return view;
        }

        @Override
        public int getCount() {
            return listItems.length;
        }
    }

}
