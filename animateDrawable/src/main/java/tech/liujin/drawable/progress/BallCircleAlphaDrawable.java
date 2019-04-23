package tech.liujin.drawable.progress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-19:10:17
 */
public class BallCircleAlphaDrawable extends ProgressDrawable {

      private float mRadius;
      private int   mSize;

      public BallCircleAlphaDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mSize = Math.min( bounds.width(), bounds.height() );
            mRadius = mSize / 14;
      }

      @Override
      protected void draw (
          @NonNull Canvas canvas, float progress ) {

            int width = getWidth();
            int height = getHeight();
            canvas.translate( width >> 1, height >> 1 );

            for( int i = 0; i < 12; i++ ) {

                  mPaint.setAlpha( calculateAlpha( calculateProgress( i, progress ) ) );
                  canvas.drawCircle( 0, -mSize / 2 + mRadius,
                                     mRadius, mPaint
                  );
                  canvas.rotate( 30 );
            }
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
}
