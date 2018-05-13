package com.example.animatedrawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.engine.TimeEngine;

/**
 * @author wuxio 2018-05-12:23:15
 */
public class CircleLoadingDrawable extends BaseAnimateDrawable {

    private static final String TAG = "CircleLoadingDrawable";

    private Path        mSrcPath;
    private PathMeasure mPathMeasure;
    private Path        mDstPath;
    private RectF       mRectF;

    private TimeEngine mTimeEngine;
    private float      mLength;


    public CircleLoadingDrawable(int size) {

        mSize = size;
    }


    @Override
    protected void init() {

        super.init();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mTimeEngine = new TimeEngine(new AccelerateDecelerateInterpolator());

        mSrcPath = new Path();
        mPathMeasure = new PathMeasure();
        mDstPath = new Path();
        mRectF = new RectF();
    }

    //============================ size ============================

    private int mSize;


    @Override
    public int getIntrinsicWidth() {

        return mSize;
    }


    @Override
    public int getIntrinsicHeight() {

        return mSize;
    }

    //============================ draw ============================


    private void initPath() {

        int size = mSize;
        int r = size >> 1;
        float strokeWidth = mPaint.getStrokeWidth();

        mRectF.set(
                strokeWidth,
                strokeWidth,
                size - strokeWidth,
                size - strokeWidth
        );

        mSrcPath.addArc(mRectF, -90, 359.9f);
        mPathMeasure.setPath(mSrcPath, true);

        mLength = mPathMeasure.getLength();
    }


    @Override
    public void draw(@NonNull Canvas canvas) {

        if (mTimeEngine.isRunning()) {
            float fraction = mTimeEngine.getFraction();

            mDstPath.reset();
            mDstPath.lineTo(0, 0);

            int repeated = mTimeEngine.getRepeated();

            if (repeated % 2 == 0) {

                mPathMeasure.getSegment(0, mLength * fraction, mDstPath, true);
            } else {

                mPathMeasure.getSegment(mLength * fraction, mLength, mDstPath, true);
            }

            canvas.drawPath(mDstPath, mPaint);
            calculate();
        }
    }


    @Override
    protected void calculate() {

        invalidateSelf();
    }


    @Override
    public void start() {

        start(800, 4000);
    }


    public void start(int duration, int repeat) {

        if (!mTimeEngine.isRunning()) {

            initPath();
            mTimeEngine.setDuration(duration).setRepeat(repeat).start();
            invalidateSelf();
        }
    }


    @Override
    public void stop() {

        mTimeEngine.stop();
    }


    @Override
    public boolean isRunning() {

        return mTimeEngine.isRunning();
    }
}
