package com.robdich.hideabletoolbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.scrollobserver.IScrollable;
import com.robdich.hideabletoolbar.scrollobserver.IScrollableCallbacks;


public class HideableToolbarActivity extends BaseActivity implements IScrollObserver {

    private View mHideableView;
    private int mHideableViewHeight = 0;
    private boolean mActionBarShown = true;

    private View mTabView;

    private static final int HEADER_HIDE_ANIM_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mHideableView = getHideableView();
        mTabView = getTabView();
    }

    protected View getHideableView(){
        return null;
    }

    protected View getTabView() {
        return null;
    }

    @Override
    public void observeScrollable(final IScrollable scrollable) {

        int height = getHideableToolbarHeight();
        scrollable.setTopClearance(height);

        scrollable.setScrollableCallbacks(new IScrollableCallbacks() {

            /**
             * During onScrollUp show toolbar
             */
            @Override
            public void onScrollUp() {
                showOrHideActionBar(true);
            }

            /**
             * During onScrollDown hide toolbar
             */
            @Override
            public void onScrollDown() {
                showOrHideActionBar(false);
            }
        });

    }

    /**
     * Gets the height of the toolbar
     * This can be overriden in the inheriting class if custom logic for getting
     * toolbar height is required. Ex. getting dimension defined in xml file.
     * @return height of the toolbar that serves as an additional padding for
     * the overlayed scrollable view
     */
    protected int getHideableToolbarHeight(){
        int tabHeight = 0;

        if(mHideableView != null) {
            mHideableView.measure(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mHideableViewHeight = mHideableView.getMeasuredHeight();
        }

        if(mTabView != null) {
            mTabView.measure(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            tabHeight = mTabView.getMeasuredHeight();
        }

        return mHideableViewHeight + tabHeight;
    }

    protected void showOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        onActionBarShowOrHide(show);
    }

    protected void onActionBarShowOrHide(boolean shown) {
        if(mHideableView != null) {
            animateToolbar(shown);
        }

        if(mTabView != null){
            animateTabs(shown);
        }
    }

    private void animateToolbar(boolean show){
        int translation = show ? 0 : -mHideableView.getBottom();
        animateView(mHideableView, translation);
    }

    private void animateTabs(boolean show){
        int translation = show ? 0 : -mHideableViewHeight;
        animateView(mTabView, translation);
    }

    private void animateView(View view, int translation){
        view.animate()
                .translationY(translation)
                        //.alpha(1)
                .setDuration(HEADER_HIDE_ANIM_DURATION)
                .setInterpolator(new DecelerateInterpolator());
    }

}
