package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-15:22:10
 */
public class BallGridPulseDrawable extends ProgressDrawable {

      private float mSpace;
      private float mMinScale = 0.4f;
      private float mHalfSpace;
      private float mRadius;
      private float mMinRadius;
      private float mDRadius;
      private int   mSize;

      public BallGridPulseDrawable ( ) {

            super();
            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int width = getWidth();
            int height = getHeight();
            int dX = ( width - mSize ) >> 1;
            int dY = ( height - mSize ) >> 1;
            canvas.translate( dX, dY );

            for( int i = 0; i < 3; i++ ) {

                  float y = mHalfSpace + mRadius + ( mRadius * 2 + mSpace ) * i;
                  for( int j = 0; j < 3; j++ ) {
                        float x = mHalfSpace + mRadius + ( mRadius * 2 + mSpace ) * j;
                        float calculateProgress = calculateProgress( i * 3 + j, progress );
                        float r =
                            mMinRadius + mDRadius * calculateProgress;
                        canvas.drawCircle( x, y, r, mPaint );
                  }
            }
      }

      private float calculateProgress ( int i, float progress ) {

            progress = progress - 0.055f * i;
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

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            mSize = Math.min( bounds.width(), bounds.height() );

            mSpace = mSize / 9;
            mHalfSpace = mSpace / 2;
            mRadius = ( mSize - 3 * mSpace ) / 6;
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
