package com.robdich.hideabletoolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
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

        ViewTreeObserver vto = mHideableView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mHideableView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mHideableView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                int height = mHideableView.getMeasuredHeight();
                scrollable.setTopClearance(height);
            }
        });

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
