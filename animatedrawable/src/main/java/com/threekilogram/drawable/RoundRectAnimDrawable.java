package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 根据时间进度绘制圆角矩形,一般作为倒计时背景
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-07-23
 * @time: 10:57
 */
public class RoundRectAnimDrawable extends BaseProgressDrawable {

      public static final int CLOCK_WISE_ADD         = 0;
      public static final int COUNTER_CLOCK_WISE_ADD = 1;
      public static final int CLOCK_WISE_SUB         = 2;
      public static final int COUNTER_CLOCK_WISE_SUB = 3;

      private float mStrokeWidth = 10;
      private int   mDuration    = 3000;

      private int         mWidth;
      private int         mHeight;
      private Path        mSrc;
      private Path        mDst;
      private PathMeasure mPathMeasure;
      private float       mTotalLength;
      private int         mMode;
      private float       mProgress;

      public RoundRectAnimDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mPaint.setStrokeWidth( mStrokeWidth );
      }

      @Override
      public int getIntrinsicWidth ( ) {

            Rect bounds = getBounds();
            mWidth = bounds.right - bounds.left;
            mHeight = bounds.bottom - bounds.top;
            return mWidth;
      }

      @Override
      public int getIntrinsicHeight ( ) {

            Rect bounds = getBounds();
            mHeight = bounds.bottom - bounds.top;
            mWidth = bounds.right - bounds.left;
            return mHeight;
      }

      public void setStrokeWidth ( float strokeWidth ) {

            mStrokeWidth = strokeWidth;
            mPaint.setStrokeWidth( mStrokeWidth );
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            if( mPathMeasure == null ) {

                  mSrc = new Path();
                  mDst = new Path();
                  mPathMeasure = new PathMeasure();

                  Rect bounds = getBounds();
                  RectF rectF = new RectF(
                      bounds.left + mStrokeWidth / 2,
                      bounds.top + mStrokeWidth / 2,
                      bounds.right - mStrokeWidth / 2,
                      bounds.bottom - mStrokeWidth / 2
                  );
                  mSrc.addRoundRect(
                      rectF,
                      10000,
                      10000,
                      Direction.CW
                  );

                  mPathMeasure.setPath( mSrc, true );
                  mTotalLength = mPathMeasure.getLength();

                  if( mMode > 1 ) {
                        canvas.drawPath( mSrc, mPaint );
                  }

                  return;
            }

            float start = 0;
            float end = 0;

            float fraction = mProgress;

            if( mMode == CLOCK_WISE_SUB ) {

                  start = mTotalLength * fraction;
                  end = mTotalLength;
            } else if( mMode == COUNTER_CLOCK_WISE_ADD ) {

                  start = mTotalLength * ( 1 - fraction );
                  end = mTotalLength;
            } else if( mMode == COUNTER_CLOCK_WISE_SUB ) {

                  start = 0;
                  end = mTotalLength * ( 1 - fraction );
            } else {

                  start = 0;
                  end = mTotalLength * fraction;
            }

            mDst.reset();

            mPathMeasure.getSegment( start, end, mDst, true );

            canvas.drawPath( mDst, mPaint );
      }

      public void setMode ( @Mode int mode ) {

            mMode = mode;
      }

      public void setDuration ( int duration ) {

            mDuration = duration;
      }

      @Override
      public void setProgress ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      @IntDef(value = { CLOCK_WISE_ADD,
                        CLOCK_WISE_SUB,
                        COUNTER_CLOCK_WISE_ADD,
                        COUNTER_CLOCK_WISE_SUB })
      @Target(ElementType.PARAMETER)
      public @interface Mode { }
}
