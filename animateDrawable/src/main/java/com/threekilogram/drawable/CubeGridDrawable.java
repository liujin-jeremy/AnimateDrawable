package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-19:9:43
 */
public class CubeGridDrawable extends ProgressDrawable {

      private int mSize;
      private float mHalfRectSize;
      private float mRectSize;

      public CubeGridDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mSize = Math.min( bounds.width(), bounds.height() );
            mRectSize = mSize / 3;
            mHalfRectSize = mRectSize / 2;
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int dx = ( getWidth() - mSize ) / 2;
            int dy = ( getHeight() - mSize ) / 2;

            canvas.translate( dx, dy );

            for( int i = 0; i < 3; i++ ) {

                  float cy = mHalfRectSize + mRectSize * i;
                  for( int j = 0; j < 3; j++ ) {
                        float cx = mHalfRectSize + mRectSize * j;
                        float radius = calculateRadius( calculateProgress( i * 3 + j, progress ) );
                        canvas.drawRect( cx - radius, cy - radius, cx + radius,
                                         cy + radius,
                                         mPaint
                        );
                  }
            }
      }

      private float calculateProgress ( int i, float progress ) {

            int index;
            if( i == 6 ) {
                  index = 0;
            } else if( i == 3 || i == 7 ) {
                  index = 1;
            } else if( i == 0 || i == 4 || i == 8 ) {
                  index = 2;
            } else if( i == 1 || i == 5 ) {
                  index = 3;
            } else {
                  index = 4;
            }

            float low = 2 * index;
            float high = low + 10;

            progress *= 24;

            if( progress < low ) {
                  return 0;
            } else if( progress < high ) {
                  return ( progress - low ) / 10;
            } else {
                  return 1;
            }
      }

      private float calculateRadius ( float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
            }

            return mHalfRectSize * ( 1 - progress );
      }
}
