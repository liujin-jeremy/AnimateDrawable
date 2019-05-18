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
public class BallGridPulseDrawable extends ProgressDrawable {

      private static final int COUNT = 3;

      private float   mMinScale    = 0.1f;
      private float   mRadius;
      private float   mMinRadius;
      private float   mDRadius;
      private int     mSize;
      private float[] mRadiusArray = new float[ COUNT * COUNT ];
      private float[] mDxArray     = new float[ COUNT * COUNT ];
      private float[] mDyArray     = new float[ COUNT * COUNT ];

      public BallGridPulseDrawable ( ) {

            super();
            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            mSize = Math.min( bounds.width(), bounds.height() );

            mRadius = mSize * 1f / 6;
            mMinRadius = mRadius * mMinScale;
            mDRadius = mRadius - mMinRadius;

            super.onBoundsChange( bounds );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            for( int i = 0; i < COUNT; i++ ) {

                  float y = mRadius + ( mRadius * 2 ) * i;
                  for( int j = 0; j < COUNT; j++ ) {
                        float x = mRadius + ( mRadius * 2 ) * j;
                        int index = i * COUNT + j;
                        float calculateProgress = calculateProgress( index, progress );
                        float r = mMinRadius + mDRadius * calculateProgress;

                        int k = j + COUNT * i;
                        mDxArray[ k ] = x;
                        mDyArray[ k ] = y;
                        mRadiusArray[ k ] = r;
                  }
            }

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int width = getWidth();
            int height = getHeight();

            int dX = ( width - mSize ) >> 1;
            int dY = ( height - mSize ) >> 1;
            canvas.translate( dX, dY );

            for( int i = 0; i < COUNT; i++ ) {
                  for( int j = 0; j < COUNT; j++ ) {
                        int k = j + COUNT * i;
                        canvas.drawCircle( mDxArray[ k ], mDyArray[ k ], mRadiusArray[ k ], mPaint );
                  }
            }
      }

      private float calculateProgress ( int i, float progress ) {

            progress -= i * 0.5f / 8;

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

      public void setMinScale ( float minScale ) {

            mMinScale = minScale;
      }

      public float getMinScale ( ) {

            return mMinScale;
      }
}
