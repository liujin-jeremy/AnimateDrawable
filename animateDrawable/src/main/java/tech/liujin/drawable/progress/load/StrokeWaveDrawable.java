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
public class StrokeWaveDrawable extends ProgressDrawable {

      private static final String TAG = StrokeWaveDrawable.class.getSimpleName();

      private int     mStrokeWidth;
      private int     mStrokeHigh;
      private int     mStrokeLow;
      private float[] mXs = new float[ 5 ];
      private float[] mYs = new float[ 5 ];

      public StrokeWaveDrawable ( ) {

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

            for( int i = 0; i < 5; i++ ) {
                  canvas.drawLine( mXs[ i ], -mYs[ i ], mXs[ i ], mYs[ i ], mPaint );
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            int strokeWidth = mStrokeWidth;
            float half = strokeWidth / 2f;
            int dY = mStrokeHigh - mStrokeLow;
            for( int i = 0; i < 5; i++ ) {
                  mXs[ i ] = strokeWidth + half + ( strokeWidth * 2 * i );
                  mYs[ i ] = calculateY( dY, calculateProgress( i, progress ) );
            }

            invalidateSelf();
      }

      private float calculateProgress ( int i, float progress ) {

            progress -= i * 0.1f;
            if( progress < 0 ) {
                  progress = -progress;
            }
            return progress;
      }

      private float calculateY ( int dY, float progress ) {

            if( progress <= 0.5f ) {

                  progress = progress * 2;
                  return mStrokeHigh - dY * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  return mStrokeLow + dY * progress;
            }
      }
}
