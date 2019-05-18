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

      private static final String TAG = StrokePulsePushDrawable.class.getSimpleName();

      private int mStrokeWidth;
      private int mStrokeHigh;
      private int mStrokeLow;

      public StrokePulsePushDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mPaint.setColor( Color.RED );
            mPaint.setStrokeCap( Cap.SQUARE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mStrokeWidth = bounds.width() / 11;
            mStrokeHigh = bounds.height() / 5 * 2;
            mStrokeLow = bounds.height() / 5;

            mPaint.setStrokeWidth( mStrokeWidth );
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            canvas.translate( 0, getHeight() >> 1 );

            int strokeWidth = mStrokeWidth;
            float half = strokeWidth / 2;

            int dY = mStrokeHigh - mStrokeLow;

            for( int i = 0; i < 5; i++ ) {

                  float x = strokeWidth + half + ( strokeWidth * 2 * i );
                  float y = calculateY( dY, calculateProgress( i, progress ) );
                  canvas.drawLine( x, -y, x, y, mPaint );
            }
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
