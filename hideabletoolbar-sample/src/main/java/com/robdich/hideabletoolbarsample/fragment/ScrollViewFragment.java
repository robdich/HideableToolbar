package com.robdich.hideabletoolbarsample.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.view.ObserveableScrollView;
import com.robdich.hideabletoolbarsample.R;

/**
 * Created by Robert on 3/12/2015.
 */
public class ScrollViewFragment extends Fragment {

    private ObserveableScrollView mScrollView;
    private IScrollObserver mScrollObserver;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_scrollview, container, false);
        mScrollView = (ObserveableScrollView) rootView.findViewById(R.id.scrolView);

        LinearLayout contentContainer = (LinearLayout) rootView.findViewById(R.id.content_container);
        for(int i = 1; i <= 30; i++) {
            TextView text;
            text = (TextView) inflater.inflate(R.layout.text_item_layout, null);
            text.setText("ScrollViewItem " + i);
            contentContainer.addView(text);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mScrollObserver != null && mScrollView != null){
            mScrollObserver.observeScrollable(mScrollView);
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

}
