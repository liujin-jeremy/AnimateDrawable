package com.example.drawable.progress.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author wuxio 2018-05-25:11:10
 */
public class ProgressColorTextView extends android.support.v7.widget.AppCompatTextView {

    private int   mStartColor;
    private int   mEndColor;
    private float mProgress;
    private ArgbEvaluator mEvaluator = new ArgbEvaluator();


    public ProgressColorTextView(Context context) {

        super(context);
    }


    public ProgressColorTextView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
    }


    public ProgressColorTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    public void setTextColor(@ColorInt int startColor, @ColorInt int endColor) {

        mStartColor = startColor;
        mEndColor = endColor;

        setProgressColor(mProgress, startColor, endColor);
    }


    public void setProgress(@FloatRange(from = 0f, to = 1f) float progress) {

        mProgress = progress;

        setProgressColor(progress, mStartColor, mEndColor);
    }


    private void setProgressColor(float progress, int startColor, int endColor) {

        int color = (Integer) mEvaluator.evaluate(progress, startColor, endColor);
        setTextColor(color);
    }
}
