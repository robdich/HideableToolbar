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

    private View hideableToolbar;
    private int hideableToolbarHeight;
    private boolean isActionBarShown = true;

    private static final int HEADER_HIDE_ANIM_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        hideableToolbar = getHideableToolbar();
    }

    protected View getHideableToolbar(){
        return null;
    }

    @Override
    public void observeScrollable(final IScrollable scrollable) {

        ViewTreeObserver vto = hideableToolbar.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    hideableToolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    hideableToolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                hideableToolbarHeight = hideableToolbar.getMeasuredHeight();
                scrollable.setTopClearance(hideableToolbarHeight);
            }
        });

        scrollable.setScrollableCallbacks(new IScrollableCallbacks() {

            /**
             * During onScrollUp hide toolbar
             */
            @Override
            public void onScrollUp() {
                showOrHideActionBar(false);
            }

            /**
             * During onScrollDown show toolbar
             */
            @Override
            public void onScrollDown() {
                showOrHideActionBar(true);
            }
        });

    }

    protected void showOrHideActionBar(boolean show) {
        if (show == isActionBarShown) {
            return;
        }

        isActionBarShown = show;
        onActionBarShowOrHide(show);
    }

    protected void onActionBarShowOrHide(boolean shown) {
        if (shown) {
            hideableToolbar.animate()
                    .translationY(0)
                            //.alpha(1)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        } else {
            hideableToolbar.animate()
                    .translationY(-hideableToolbar.getBottom())
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
