package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-15:22:10
 */
public class BallPulseDrawable extends ProgressDrawable {

      private static final int COUNT = 3;

      private float   mRadius;
      private float   mMinScale    = 0.1f;
      private float   mMinRadius;
      private float   mDRadius;
      private float[] mRadiusArray = new float[ 3 ];

      public BallPulseDrawable ( ) {

            super();
            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );

            mRadius = size * 1f / 6;
            mMinRadius = mRadius * mMinScale;
            mDRadius = mRadius - mMinRadius;

            super.onBoundsChange( bounds );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            mRadiusArray[ 0 ] = calculateRadius( calculateProgress( progress ) );
            mRadiusArray[ 1 ] = calculateRadius( calculateProgress( progress - 0.2f ) );
            mRadiusArray[ 2 ] = calculateRadius( calculateProgress( progress - 0.4f ) );

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int dX = ( getWidth() ) >> 1;
            int dY = ( getHeight() ) >> 1;
            canvas.translate( dX, dY );

            canvas.drawCircle(
                -mRadius * 2,
                0,
                mRadiusArray[ 0 ],
                mPaint
            );
            canvas.drawCircle(
                0,
                0,
                mRadiusArray[ 1 ],
                mPaint
            );
            canvas.drawCircle(
                mRadius * 2,
                0,
                mRadiusArray[ 2 ],
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

      public void setMinScale ( float minScale ) {

            mMinScale = minScale;
      }

      public float getMinScale ( ) {

            return mMinScale;
      }
}
