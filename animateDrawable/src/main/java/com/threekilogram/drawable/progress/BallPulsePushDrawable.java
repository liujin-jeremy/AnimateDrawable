package com.threekilogram.drawable.progress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-19:9:06
 */
public class BallPulsePushDrawable extends ProgressDrawable {

      private float mRadius;

      public BallPulsePushDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int size = Math.min( bounds.width(), bounds.height() );
            mRadius = size / 8;
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            canvas.translate( getWidth() / 2, getHeight() / 2 );

            float radius = calculateRadius( calculateProgress( 0, progress ) );
            canvas.drawCircle( -2.5f * mRadius, 0, radius, mPaint );
            radius = calculateRadius( calculateProgress( 1, progress ) );
            canvas.drawCircle( 0, 0, radius, mPaint );
            radius = calculateRadius( calculateProgress( 2, progress ) );
            canvas.drawCircle( 2.5f * mRadius, 0, radius, mPaint );
      }

      protected float calculateProgress ( int i, float progress ) {

            progress *= 20;

            float low = 3 * i;
            float high = low + 10;

            if( progress < low ) {
                  return 0;
            } else if( progress < high ) {
                  return ( progress - low ) / 10;
            } else {
                  return 1;
            }
      }

      protected float calculateRadius ( float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2f;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
            }

            return mRadius * progress;
      }
}
