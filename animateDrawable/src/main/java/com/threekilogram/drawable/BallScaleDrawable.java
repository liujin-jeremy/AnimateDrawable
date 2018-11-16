package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-15:22:10
 */
public class BallScaleDrawable extends ProgressDrawable {

      private float mSpace;
      private float mMinScale = 0.5f;
      private float mHalfSpace;
      private float mRadius;
      private float mMinRadius;
      private float mDRadius;

      public BallScaleDrawable ( ) {

            super();
            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int y = getHeight() / 2;

            for( int i = 0; i < 3; i++ ) {

                  float x = mHalfSpace + mRadius + ( mRadius * 2 + mSpace ) * i;
                  float r = mMinRadius + mDRadius * calculateProgress( i, progress );
                  canvas.drawCircle( x, y, r, mPaint );
            }
      }

      private float calculateProgress ( int i, float progress ) {

            progress = progress + 0.2f * i;
            if( progress > 1f ) {
                  progress = 1f - ( progress - 1f );
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
            int width = bounds.width();

            mSpace = width / 9;
            mHalfSpace = (int) ( mSpace / 2 );
            mRadius = ( width - 3 * mSpace ) / 6;
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
