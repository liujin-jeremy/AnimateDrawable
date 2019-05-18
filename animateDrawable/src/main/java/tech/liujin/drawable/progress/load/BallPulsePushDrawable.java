package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-19:9:06
 */
public class BallPulsePushDrawable extends ProgressDrawable {

      private float   mRadius;
      private float[] mRadiusArray = new float[ 3 ];

      public BallPulsePushDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );
            mRadius = size * 1f / 8;

            super.onBoundsChange( bounds );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            mRadiusArray[ 0 ] = calculateRadius( calculateProgress( 0, progress ) );
            mRadiusArray[ 1 ] = calculateRadius( calculateProgress( 1, progress ) );
            mRadiusArray[ 2 ] = calculateRadius( calculateProgress( 2, progress ) );

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );

            canvas.drawCircle( -2.5f * mRadius, 0, mRadiusArray[ 0 ], mPaint );
            canvas.drawCircle( 0, 0, mRadiusArray[ 1 ], mPaint );
            canvas.drawCircle( 2.5f * mRadius, 0, mRadiusArray[ 2 ], mPaint );
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
