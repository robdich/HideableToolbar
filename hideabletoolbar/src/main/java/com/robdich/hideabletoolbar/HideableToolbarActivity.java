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
    private boolean mActionBarShown = true;

    private static final int HEADER_HIDE_ANIM_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mHideableView = getHideableView();
    }

    protected View getHideableView(){
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
     * toolbar logic is required. Ex. getting dimension defined in xml file.
     * @return height of the toolbar that serves as an additional padding for
     * the overlayed scrollable view
     */
    protected int getHideableToolbarHeight(){
        mHideableView.measure(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        return mHideableView.getMeasuredHeight();
    }

    protected void showOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        onActionBarShowOrHide(show);
    }

    protected void onActionBarShowOrHide(boolean shown) {
        if (shown) {
            mHideableView.animate()
                    .translationY(0)
                            //.alpha(1)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        } else {
            mHideableView.animate()
                    .translationY(-mHideableView.getBottom())
                            //.alpha(0)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        }
    }

    protected void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {
        if (isOpen) {
            showOrHideActionBar(true);
        }
    }

}
