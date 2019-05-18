package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:13:18
 */
public class StrokePulseDrawable extends ProgressDrawable {

      private static final int COUNT = 3;

      private int mStrokeWidth;
      private int mStrokeHigh;
      private int mStrokeLow;

      private float[] mX1s = new float[ COUNT ];
      private float[] mY1s = new float[ COUNT ];
      private float[] mX2s = new float[ COUNT ];
      private float[] mY2s = new float[ COUNT ];

      public StrokePulseDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            mStrokeWidth = bounds.width() / 11;
            mStrokeHigh = bounds.height() / 5 * 2;
            mStrokeLow = bounds.height() / 8;

            mPaint.setStrokeWidth( mStrokeWidth );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( 0, getHeight() >> 1 );

            for( int i = 0; i < COUNT; i++ ) {
                  canvas.drawLine( mX1s[ i ], -mY1s[ i ], mX1s[ i ], mY1s[ i ], mPaint );
                  int j = 4 - i;
                  if( j != i ) {
                        canvas.drawLine( mX2s[ i ], -mY2s[ i ], mX2s[ i ], mY2s[ i ], mPaint );
                  }
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            int strokeWidth = mStrokeWidth;
            float half = strokeWidth * 1f / 2;
            int dY = mStrokeHigh - mStrokeLow;

            for( int i = 0; i < COUNT; i++ ) {

                  mX1s[ i ] = strokeWidth + half + ( strokeWidth * 2 * i );
                  mY1s[ i ] = calculateY( dY, calculateProgress( i, progress ) );

                  int j = 4 - i;
                  if( j != i ) {
                        mX2s[ i ] = strokeWidth + half + ( strokeWidth * 2 * j );
                        mY2s[ i ] = calculateY( dY, calculateProgress( i, progress ) );
                  }
            }

            invalidateSelf();
      }

      private float calculateProgress ( int i, float progress ) {

            progress -= i * 0.5f / 2;
            if( progress < 0 ) {
                  progress = -progress;
            }
            return progress;
      }

      private float calculateY ( int dY, float progress ) {

            if( progress <= 0.5f ) {

                  progress = progress * 2;
                  return mStrokeLow + dY * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  return mStrokeHigh - dY * progress;
            }
      }
}
