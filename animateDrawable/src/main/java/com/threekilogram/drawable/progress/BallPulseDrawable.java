package com.threekilogram.drawable.progress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-15:22:10
 */
public class BallPulseDrawable extends ProgressDrawable {

      private float mRadius;
      private float mMinScale = 0.1f;
      private float mMinRadius;
      private float mDRadius;

      public BallPulseDrawable ( ) {

            super();
            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int dX = ( getWidth() ) >> 1;
            int dY = ( getHeight() ) >> 1;
            canvas.translate( dX, dY );

            canvas.drawCircle(
                -mRadius * 2,
                0,
                calculateRadius( calculateProgress( progress ) ),
                mPaint
            );
            canvas.drawCircle(
                0,
                0,
                calculateRadius( calculateProgress( progress - 0.2f ) ),
                mPaint
            );
            canvas.drawCircle(
                mRadius * 2,
                0,
                calculateRadius( calculateProgress( progress - 0.4f ) ),
                mPaint
            );
      }

      private float calculateProgress ( float progress ) {

            if( progress < 0f ) {
                  progress = -progress;
            }

            if( progress <= 0.5f ) {
                  progress *= 2;
            } else {
                  progress = ( 1f - progress ) * 2;
            }

            return progress;
      }

      private float calculateRadius ( float progress ) {

            return mMinRadius + mDRadius * ( progress );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            int size = Math.min( bounds.width(), bounds.height() );

            mRadius = size / 6;
            mMinRadius = mRadius * mMinScale;
            mDRadius = mRadius - mMinRadius;
      }

      public void setMinScale ( float minScale ) {

            mMinScale = minScale;
      }

      public float getMinScale ( ) {

            return mMinScale;
      }
}
