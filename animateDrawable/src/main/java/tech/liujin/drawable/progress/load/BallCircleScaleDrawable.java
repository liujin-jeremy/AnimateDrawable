package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:16:04
 */
public class BallCircleScaleDrawable extends ProgressDrawable {

      private static final int COUNT = 12;

      private float   mRadius;
      private float   mMinRadius;
      private float   mDRadius;
      private float   mCy;
      private float[] mRadiusArray = new float[ COUNT ];
      private int[]   mAlphaArray  = new int[ COUNT ];

      public BallCircleScaleDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );
            mRadius = size * 1f / 10;
            float minScale = 0.4f;
            mMinRadius = mRadius * minScale;
            mDRadius = mRadius - mMinRadius;
            mCy = -size * 1f / 2 + mRadius;

            super.onBoundsChange( bounds );
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int width = getWidth();
            int height = getHeight();
            canvas.translate( width >> 1, height >> 1 );

            for( int i = 0; i < COUNT; i++ ) {

                  canvas.rotate( 30 );
                  mPaint.setAlpha( mAlphaArray[ i ] );
                  canvas.drawCircle( 0, mCy, mRadiusArray[ i ], mPaint );
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            for( int i = 0; i < COUNT; i++ ) {
                  mRadiusArray[ i ] = calculateRadius( i, calculateProgress( i, progress ) );
            }

            invalidateSelf();
      }

      private float calculateProgress ( int i, float progress ) {

            progress = progress - i * ( 1f / 11 );
            if( progress < 0 ) {
                  progress = -progress;
            }
            return progress;
      }

      private float calculateRadius ( int i, float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2;
                  mAlphaArray[ i ] = (int) ( 205 * progress );
                  return mMinRadius + mDRadius * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  mAlphaArray[ i ] = (int) ( 205 * ( 1 - progress ) );
                  return mRadius - mDRadius * progress;
            }
      }
}
