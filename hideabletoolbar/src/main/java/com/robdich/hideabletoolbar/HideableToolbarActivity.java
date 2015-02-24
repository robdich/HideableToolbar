package com.robdich.hideabletoolbar;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;

import com.robdich.hideabletoolbar.scrollobserver.IScrollObserver;
import com.robdich.hideabletoolbar.view.ObserveableRecyclerView;


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
    public void observeScrollable(final ObserveableRecyclerView view) {

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
                view.setTopPadding(hideableToolbarHeight);
            }
        });

        view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollAmount = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean show = dy < 0;
                if(show){
                    scrollAmount = 0;
                } else {
                    scrollAmount += dy;
                    show = scrollAmount < hideableToolbarHeight;
                }
                showOrHideActionBar(show);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
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
