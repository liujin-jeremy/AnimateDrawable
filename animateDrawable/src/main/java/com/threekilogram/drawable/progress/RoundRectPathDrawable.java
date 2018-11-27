package com.threekilogram.drawable.progress;

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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据时间进度绘制圆角矩形,一般作为倒计时背景
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-07-23
 * @time: 10:57
 */
public class RoundRectPathDrawable extends ProgressDrawable {

      public static final int CLOCKWISE_ADD         = 0;
      public static final int COUNTER_CLOCKWISE_ADD = 1;
      public static final int CLOCKWISE_SUB         = 2;
      public static final int COUNTER_CLOCKWISE_SUB = 3;

      private float mStrokeWidth = 10;

      private Path        mDst;
      private PathMeasure mPathMeasure;
      private int         mMode;
      private float       mTotalLength;

      public RoundRectPathDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mPaint.setStrokeWidth( mStrokeWidth );
            mPathMeasure = new PathMeasure();
            mDst = new Path();
      }

      public void setStrokeWidth ( float strokeWidth ) {

            mStrokeWidth = strokeWidth;
      }

      public float getStrokeWidth ( ) {

            return mStrokeWidth;
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            Path src = new Path();
            RectF rectF = new RectF();

            rectF.set(
                0 + mStrokeWidth / 2,
                0 + mStrokeWidth / 2,
                bounds.width() - mStrokeWidth / 2,
                bounds.height() - mStrokeWidth / 2
            );
            src.addRoundRect(
                rectF,
                Integer.MAX_VALUE >> 1,
                Integer.MAX_VALUE >> 1,
                Direction.CW
            );

            mPathMeasure.setPath( src, true );
            mTotalLength = mPathMeasure.getLength();
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            float start = 0;
            float end = 0;

            if( mMode == CLOCKWISE_SUB ) {

                  start = mTotalLength * mProgress;
                  end = mTotalLength;
            } else if( mMode == COUNTER_CLOCKWISE_ADD ) {

                  start = mTotalLength * ( 1 - mProgress );
                  end = mTotalLength;
            } else if( mMode == COUNTER_CLOCKWISE_SUB ) {

                  start = 0;
                  end = mTotalLength * ( 1 - mProgress );
            } else {

                  start = 0;
                  end = mTotalLength * mProgress;
            }

            mDst.reset();
            mPathMeasure.getSegment( start, end, mDst, true );
            canvas.drawPath( mDst, mPaint );
      }

      public void setMode ( @Mode int mode ) {

            mMode = mode;
      }

      @IntDef(value = { CLOCKWISE_ADD,
                        CLOCKWISE_SUB,
                        COUNTER_CLOCKWISE_ADD,
                        COUNTER_CLOCKWISE_SUB })
      @Target(ElementType.PARAMETER)
      @Retention(RetentionPolicy.SOURCE)
      @interface Mode { }
}
