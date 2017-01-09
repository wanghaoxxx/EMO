/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manuelpeinado.quickreturnheader;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.manuelpeinado.quickreturnheader.ListViewScrollObserver.OnListViewScrollListener;

public class QuickReturnHeaderHelper {
    protected static final String TAG = "QuickReturnHeaderHelper";
    private View realHeader;
    private RelativeLayout.LayoutParams titleParams;
    private int headerHeight;
    private int headerTop;
    private View dummyHeader;
    private ListView listView;
    private boolean snapped = true;
    private OnSnappedChangeListener onSnappedChangeListener;
    private Animation animation;
    /**
     * True if the last scroll movement was in the "up" direction.
     */
    private boolean scrollingUp;
    /**
     * Maximum time it takes the show/hide animation to complete. Maximum because it will take much less time if the
     * header is already partially hidden or shown.
     * <p>
     * In milliseconds.
     */
    private static final long ANIMATION_DURATION = 400;

    public interface OnSnappedChangeListener {
        void onSnappedChange(boolean snapped);
    }

    public QuickReturnHeaderHelper(View titleView,ListView listView,int titleHeight) {
        this.realHeader=titleView;
        this.listView=listView;
        this.headerHeight=titleHeight;
    }

    public void setOnSnappedChangeListener(OnSnappedChangeListener onSnapListener) {
        this.onSnappedChangeListener = onSnapListener;
    }
    
    public void setup(){
        titleParams=(LayoutParams) realHeader.getLayoutParams();
        ListViewScrollObserver observer = new ListViewScrollObserver(listView);
        observer.setOnScrollUpAndDownListener(new OnListViewScrollListener() {
            @Override
            public void onScrollUpDownChanged(int delta, int scrollPosition, boolean exact) {
                onNewScroll(delta);
                snap(headerTop == scrollPosition);
                Log.d(TAG, "delta = "+delta+" scrollPosition = "+scrollPosition+"  exact = "+exact+"  headerTop = "+headerTop);
            }
            @Override
            public void onScrollIdle() {
                QuickReturnHeaderHelper.this.onScrollIdle();
            }
        });
        dummyHeader = new View(listView.getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, headerHeight);
        dummyHeader.setLayoutParams(params);
        listView.addHeaderView(dummyHeader);
    }
    
    public ListView getListView(){
        return listView;
    }

    /**
     * Invoked when the user stops scrolling the content. In response we might start an animation to leave the header in
     * a fully open or fully closed state.
     */
    private void onScrollIdle() {
        if (snapped) {
            // Only animate when header is out of its natural position (truly over the content).
            return;
        }
        if (headerTop > 0 || headerTop <= -headerHeight) {
            // Fully hidden, to need to animate.
            return;
        }
        if (scrollingUp) {
            hideHeader();
        } else {
            showHeader();
        }
    }

    /**
     * Shows the header using a simple downwards translation animation.
     */
    private void showHeader() {
        animateHeader(headerTop, 0);
    }

    /**
     * Hides the header using a simple upwards translation animation.
     */
    private void hideHeader() {
        animateHeader(headerTop, -headerHeight);
    }

    /**
     * Animates the marginTop property of the header between two specified values.
     * @param startTop Initial value for the marginTop property.
     * @param endTop End value for the marginTop property.
     */
    private void animateHeader(final float startTop, float endTop) {
        Log.v(TAG, "animateHeader");
        cancelAnimation();
        final float deltaTop = endTop - startTop;
        animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                headerTop = (int) (startTop + deltaTop * interpolatedTime);
                titleParams.topMargin = headerTop;
                realHeader.setLayoutParams(titleParams);
            }
        };
        long duration = (long) (deltaTop / (float) headerHeight * ANIMATION_DURATION);
        animation.setDuration(Math.abs(duration));
        realHeader.startAnimation(animation);
    }

    private void cancelAnimation() {
        if (animation != null) {
            realHeader.clearAnimation();
            animation = null;
        }
    }

    private void onNewScroll(int delta) {
        cancelAnimation(); 
        if (delta > 0) {  //ÏÂ»¬
            if (headerTop + delta > 0) {
                delta = -headerTop;
            }
        } else if (delta < 0) {
            if (headerTop + delta < -headerHeight) {
                delta = -(headerHeight + headerTop);
            }
        } else {
            return;
        }
        scrollingUp = delta < 0;
        Log.v(TAG, "delta=" + delta);
        headerTop += delta;
        if (titleParams.topMargin != headerTop) {
            titleParams.topMargin = headerTop;
            Log.v(TAG, "topMargin=" + headerTop);
            realHeader.setLayoutParams(titleParams);
        }
    }

    private void snap(boolean newValue) {
        if (snapped == newValue) {
            return;
        }
        snapped = newValue;
        if (onSnappedChangeListener != null) {
            onSnappedChangeListener.onSnappedChange(snapped);
        }
        Log.v(TAG, "snapped=" + snapped);
    }
}
