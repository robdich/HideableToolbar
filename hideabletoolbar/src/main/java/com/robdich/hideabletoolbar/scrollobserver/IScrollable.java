package com.robdich.hideabletoolbar.scrollobserver;

/**
 * Created by Robert on 2/24/2015.
 */
public interface IScrollable {
    /*
     * Set scrollable call backs for scroll up and down events.
     */
    public void setScrollableCallbacks(IScrollableCallbacks scrollableCallbacks);

    /*
     * Sets additional padding at the top of Scrollable object.
     * Also sets the amount of scroll before hiding toolbar view during scroll up motion.
     */
    public void setTopClearance(int clearance);

}
