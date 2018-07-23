package com.example.drawable.anim;

import android.animation.TimeInterpolator;
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
      private int         mSize;

      private TimeEngine                       mTimeEngine;
      private float                            mLength;
      private AccelerateDecelerateInterpolator mInterpolator;

      public CircleLoadingDrawable (int size) {

            super();
            mSize = size;
      }

      @Override
      protected void init () {

            super.init();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);

            mTimeEngine = new TimeEngine();

            mSrcPath = new Path();
            mPathMeasure = new PathMeasure();
            mDstPath = new Path();
      }

      //============================ size ============================

      @Override
      public int getIntrinsicWidth () {

            return mSize;
      }

      @Override
      public int getIntrinsicHeight () {

            return mSize;
      }

      //============================ draw ============================

      private void initPath () {

            int size = mSize;
            int r = size >> 1;
            float strokeWidth = mPaint.getStrokeWidth();

            RectF rectF = new RectF();

            rectF.set(
                strokeWidth,
                strokeWidth,
                size - strokeWidth,
                size - strokeWidth
            );

            mSrcPath.addArc(rectF, -90, 359.9f);
            mPathMeasure.setPath(mSrcPath, true);

            mLength = mPathMeasure.getLength();
      }

      @Override
      public void draw (@NonNull Canvas canvas) {

            if(mTimeEngine.isRunning()) {
                  float fraction = mTimeEngine.getFraction();

                  mDstPath.reset();
                  mDstPath.moveTo(mSize >> 1, 0);

                  int repeated = mTimeEngine.getRepeated();

                  if(repeated % 2 == 0) {

                        mPathMeasure.getSegment(0, mLength * fraction, mDstPath, true);
                  } else {

                        mPathMeasure.getSegment(mLength * fraction, mLength, mDstPath, true);
                  }

                  canvas.drawPath(mDstPath, mPaint);
                  calculate();
            }
      }

      @Override
      protected void calculate () {

            invalidateSelf();
      }

      //============================ 配置 ============================

      public void setStrokeWidth (int strokeWidth) {

            mPaint.setStrokeWidth(strokeWidth);
      }

      public void setStrokeColor (int color) {

            mPaint.setColor(color);
      }

      //============================ cartoon ============================

      @Override
      public void start () {

            int repeat = Integer.MAX_VALUE / 800;
            start(1200, repeat);
      }

      public void start (int duration) {

            int repeat = Integer.MAX_VALUE / duration;
            start(duration, repeat);
      }

      public void start (int duration, int repeat) {

            if(mInterpolator == null) {

                  mInterpolator = new AccelerateDecelerateInterpolator();
            }

            start(duration, repeat, mInterpolator);
      }

      public void start (int duration, int repeat, TimeInterpolator timeInterpolator) {

            if(!mTimeEngine.isRunning()) {

                  initPath();
                  mTimeEngine.setDuration(duration);
                  mTimeEngine.setInterpolator(timeInterpolator);
                  mTimeEngine.setRepeat(repeat).start();
                  invalidateSelf();
            }
      }

      @Override
      public void stop () {

            mTimeEngine.stop();
      }

      @Override
      public boolean isRunning () {

            return mTimeEngine.isRunning();
      }
}
