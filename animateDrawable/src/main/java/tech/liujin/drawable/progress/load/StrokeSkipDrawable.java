package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Cap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-23:10:51
 */
public class StrokeSkipDrawable extends ProgressDrawable {

      private int     mStrokeWidth;
      private int[]   mStartXs = new int[ 4 ];
      private float[] mStopYs  = new float[ 4 ];

      public StrokeSkipDrawable ( ) {

            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            mStrokeWidth = bounds.width() / 9;

            mPaint.setStrokeWidth( mStrokeWidth );
            mPaint.setStrokeCap( Cap.SQUARE );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( 0, getHeight() );

            canvas.drawLine( mStartXs[ 0 ], 0, mStartXs[ 0 ], mStopYs[ 0 ], mPaint );
            canvas.drawLine( mStartXs[ 1 ], 0, mStartXs[ 1 ], mStopYs[ 1 ], mPaint );
            canvas.drawLine( mStartXs[ 2 ], 0, mStartXs[ 2 ], mStopYs[ 2 ], mPaint );
            canvas.drawLine( mStartXs[ 3 ], 0, mStartXs[ 3 ], mStopYs[ 3 ], mPaint );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            int height = getHeight();
            int offset = calculateOffset( progress );

            mStartXs[ 0 ] = mStrokeWidth + mStrokeWidth / 2;
            mStartXs[ 1 ] = mStrokeWidth + mStrokeWidth / 2 + 2 * mStrokeWidth;
            mStartXs[ 2 ] = mStrokeWidth + mStrokeWidth / 2 + 2 * mStrokeWidth * 2;
            mStartXs[ 3 ] = mStrokeWidth + mStrokeWidth / 2 + 2 * mStrokeWidth * 3;

            mStopYs[ 0 ] = -height + offset;
            mStopYs[ 1 ] = -height / 2f - offset;
            mStopYs[ 2 ] = -height / 4f - offset * 2;
            mStopYs[ 3 ] = -height * 0.8f + offset * 1.5f;

            invalidateSelf();
      }

      private int calculateOffset ( float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
            }

            return (int) ( getHeight() / 2 * progress );
      }
}
