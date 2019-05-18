package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-19:10:17
 */
public class BallCircleAlphaDrawable extends ProgressDrawable {

      private static final int COUNT = 12;

      private float mRadius;
      private float mCy;
      private int[] mAlphas = new int[ 12 ];

      public BallCircleAlphaDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );
            mRadius = size * 1f / 14;
            mCy = ( -size * 1f / 2 ) + mRadius;

            super.onBoundsChange( bounds );

      }

      private float calculateProgress ( int i, float progress ) {

            progress -= 1f / 12 * i;
            if( progress < 0 ) {
                  progress = -progress;
            }
            if( progress > 1 ) {
                  progress = 0;
            }
            return progress;
      }

      private int calculateAlpha ( float progress ) {

            progress *= 24;
            if( progress < 2 ) {
                  return 0;
            } else if( progress < 12 ) {

                  return (int) ( ( progress - 2 ) / 10 * 255 );
            } else if( progress < 22 ) {
                  return (int) ( ( 1 - ( progress - 12 ) / 10 ) * 255 );
            } else {
                  return 0;
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            for( int i = 0; i < COUNT; i++ ) {
                  mAlphas[ i ] = calculateAlpha( calculateProgress( i, progress ) );
            }
            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int width = getWidth();
            int height = getHeight();
            canvas.translate( width >> 1, height >> 1 );

            for( int i = 0; i < COUNT; i++ ) {

                  mPaint.setAlpha( mAlphas[ i ] );
                  canvas.drawCircle( 0, mCy, mRadius, mPaint );
                  canvas.rotate( 30 );
            }
      }
}
