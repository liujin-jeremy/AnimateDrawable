package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:13:18
 */
public class StrokePulsePushDrawable extends ProgressDrawable {

      private static final int COUNT = 5;

      private int mStrokeWidth;
      private int mStrokeHigh;
      private int mStrokeLow;

      private float[] mXs = new float[ COUNT ];
      private float[] mYs = new float[ COUNT ];

      public StrokePulsePushDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mPaint.setColor( Color.RED );
            mPaint.setStrokeCap( Cap.SQUARE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            mStrokeWidth = bounds.width() / 11;
            mStrokeHigh = bounds.height() / 5 * 2;
            mStrokeLow = bounds.height() / 5;

            mPaint.setStrokeWidth( mStrokeWidth );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( 0, getHeight() >> 1 );

            for( int i = 0; i < COUNT; i++ ) {
                  canvas.drawLine( mXs[ i ], -mYs[ i ], mXs[ i ], mYs[ i ], mPaint );
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            int strokeWidth = mStrokeWidth;
            float half = strokeWidth * 1f / 2;
            int dY = mStrokeHigh - mStrokeLow;

            for( int i = 0; i < COUNT; i++ ) {
                  mXs[ i ] = strokeWidth + half + ( strokeWidth * 2 * i );
                  mYs[ i ] = calculateY( dY, calculateProgress( i, progress ) );
            }

            invalidateSelf();
      }

      private float calculateProgress ( int i, float progress ) {

            progress *= 26;

            float low = i * 4;
            float high = 10 + low;
            if( progress >= low && progress <= high ) {
                  return ( progress - low ) / 10;
            } else {
                  return 0;
            }
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
