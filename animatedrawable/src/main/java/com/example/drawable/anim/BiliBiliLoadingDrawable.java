package com.example.drawable.anim;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.engine.TimeEngine;

/**
 * @author wuxio 2018-05-12:11:03
 */
@SuppressWarnings("WeakerAccess")
public class BiliBiliLoadingDrawable extends BaseAnimateDrawable {

      private static final String TAG = "BiliBiliLoadingDrawable";

      private TimeEngine mTimeEngine;
      private int        mDuration;
      private int        mRepeat;

      public BiliBiliLoadingDrawable () {

            this(100);
      }

      public BiliBiliLoadingDrawable (int size) {

            mSize = size;
            initSize();
            initPath();
      }

      //============================ 尺寸 ============================

      private int mSize;
      private int mStrokeWidth;

      @Override
      public int getIntrinsicWidth () {

            return mSize;
      }

      @Override
      public int getIntrinsicHeight () {

            return mSize;
      }

      /**
       * 设置默认尺寸
       */
      private void initSize () {

            mStrokeWidth = 5;
            mDuration = 5000;
            mRepeat = 1;
      }

      public void setStrokeWidth (int strokeWidth) {

            mStrokeWidth = strokeWidth;
            mPaint.setStrokeWidth(mStrokeWidth);
      }

      public void setDuration (int defaultDuration) {

            this.mDuration = defaultDuration;
      }

      public void setRepeat (int repeat) {

            mRepeat = repeat;
      }

      //============================ 画笔 ============================

      @Override
      protected void init () {

            super.init();

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
      }

      //============================ 路径动画 ============================

      protected Path        mSrcPath;
      protected PathMeasure mPathMeasure;
      protected Path        mDstPath;

      private void initPath () {

            mSrcPath = new Path();
            mDstPath = new Path();
            mPathMeasure = new PathMeasure();

            int strokeWidth = mStrokeWidth;
            int size = mSize;
            int size20Percent = size / 5;
            int size80Percent = size - size20Percent;

            /* 一个矩形 */

            mSrcPath.moveTo(size20Percent, 0);
            mSrcPath.rLineTo(size / 2 - size20Percent, size20Percent);
            mSrcPath.lineTo(size / 2, size20Percent);
            mSrcPath.lineTo(strokeWidth, size20Percent);
            mSrcPath.rLineTo(0, size80Percent - strokeWidth);
            mSrcPath.rLineTo(size - strokeWidth * 2, 0);
            mSrcPath.rLineTo(0, -size80Percent + strokeWidth);
            mSrcPath.lineTo(size / 2 + 2, size20Percent);
            mSrcPath.lineTo(size - size20Percent, 0);

            mPathMeasure.setPath(mSrcPath, false);
      }

      //============================ 绘制 ============================

      private int mCurrentState;
      private final int STATE2 = 2;
      private final int STATE4 = 4;
      private final int STATE5 = 5;
      private final int STATE6 = 6;
      private float mStateFraction;

      @Override
      protected void calculate () {

            float fraction = mTimeEngine.getFraction();

            /* 电视外廓 */

            final float flag01 = 0.6f;
            if(fraction <= flag01) {

                  mCurrentState = STATE2;

                  mStateFraction = (fraction) * 2.5f;
                  invalidateSelf();
                  return;
            }

            final float flag03 = 0.73f;
            if(fraction <= flag03) {

                  mCurrentState = STATE4;
                  mStateFraction = (fraction - flag01) * 5;
                  invalidateSelf();
                  return;
            }

            final float flag04 = 0.86f;
            if(fraction <= flag04) {

                  mCurrentState = STATE5;
                  mStateFraction = (fraction - flag03) * 5;
                  invalidateSelf();
                  return;
            }

            final float flag05 = 1f;
            if(fraction < flag05) {

                  mCurrentState = STATE6;
                  mStateFraction = (fraction - flag04) * 5;
                  invalidateSelf();
            } else {

                  /* == 1 */

                  mCurrentState = STATE6;
                  mStateFraction = 1;
            }
      }

      @Override
      public void draw (@NonNull Canvas canvas) {

            int size = mSize;
            int size20Percent = size / 5;

            if(mCurrentState == STATE2) {

                  /* 电视轮廓 */

                  drawState2(canvas, size, size20Percent, mStateFraction);
                  calculate();
                  return;
            }

            if(mCurrentState == STATE4) {

                  /* 第一个点 */

                  drawState2(canvas, size, size20Percent, 1);
                  drawState4(canvas, size, mStateFraction);
                  calculate();
                  return;
            }

            if(mCurrentState == STATE5) {

                  /* 第二个点 */

                  drawState2(canvas, size, size20Percent, 1);
                  drawState4(canvas, size, 1);
                  drawState5(canvas, size, mStateFraction);
                  calculate();
                  return;
            }

            if(mCurrentState == STATE6) {

                  /* 第三个点 */

                  drawState2(canvas, size, size20Percent, 1);
                  drawState4(canvas, size, 1);
                  drawState5(canvas, size, 1);
                  drawState6(canvas, size, mStateFraction);
                  calculate();
            }
      }

      private void drawState2 (
          @NonNull Canvas canvas, int size, int size20Percent, float stateFraction) {

            mPaint.setStyle(Paint.Style.STROKE);

            final float length = mPathMeasure.getLength();

            /* 防止bug */
            mDstPath.reset();
            mDstPath.moveTo(size20Percent, 0);

            float d = length * stateFraction;
            mPathMeasure.getSegment(0, d, mDstPath, false);
            canvas.drawPath(mDstPath, mPaint);
      }

      private void drawState4 (@NonNull Canvas canvas, int size, float stateFraction) {

            int rX = size / 2 - size / 4;
            int rY = size * 60 / 100;

            int radius = mStrokeWidth << 1;

            mPaint.setStyle(Paint.Style.FILL);
            int alpha = (int) (255 * stateFraction);
            mPaint.setAlpha(alpha);
            canvas.drawCircle(rX, rY, radius, mPaint);
            mPaint.setAlpha(255);
      }

      private void drawState5 (@NonNull Canvas canvas, int size, float stateFraction) {

            int rX = size / 2;
            int rY = size * 60 / 100;

            int radius = mStrokeWidth << 1;

            int alpha = (int) (255 * stateFraction);
            mPaint.setAlpha(alpha);
            canvas.drawCircle(rX, rY, radius, mPaint);
            mPaint.setAlpha(255);
      }

      private void drawState6 (@NonNull Canvas canvas, int size, float stateFraction) {

            int rX = size / 2 + size / 4;
            int rY = size * 60 / 100;

            int radius = mStrokeWidth << 1;

            int alpha = (int) (255 * stateFraction);
            mPaint.setAlpha(alpha);
            canvas.drawCircle(rX, rY, radius, mPaint);
            mPaint.setAlpha(255);
      }

      //============================ 开始/结束 ============================

      @Override
      public void start () {

            if(mTimeEngine == null) {
                  mTimeEngine = new TimeEngine();
            }

            if(!mTimeEngine.isRunning()) {
                  mTimeEngine.setDuration(mDuration).setRepeat(mRepeat).start();
                  calculate();
            }
      }

      @Override
      public void stop () {

            mTimeEngine.stop();
      }

      @Override
      public boolean isRunning () {

            if(mTimeEngine == null) {
                  mTimeEngine = new TimeEngine();
            }
            return mTimeEngine.isRunning();
      }
}
