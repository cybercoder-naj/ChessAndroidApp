package com.nishant.games.chess.frontend;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class ProgressBarAnim extends Animation {

    private static final String TAG = "ProgressBarAnim";
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    ProgressBarAnim(ProgressBar progressBar, LinearLayout linearLayout) {
        this.progressBar = progressBar;
        this.linearLayout = linearLayout;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Log.d(TAG, "applyTransformation() called with: interpolatedTime = [" + interpolatedTime + "], t = [" + t + "]");
        super.applyTransformation(interpolatedTime, t);
        float value = 100 * interpolatedTime;
        progressBar.setProgress((int) value);

        if (value == 100) {
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
}
