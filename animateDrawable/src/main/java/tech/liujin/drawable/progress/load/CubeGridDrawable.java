package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-19:9:43
 */
public class CubeGridDrawable extends ProgressDrawable {

      private static final int COUNT = 3;

      private int     mSize;
      private float   mHalfRectSize;
      private float   mRectSize;
      private float[] mCxArray     = new float[ COUNT * COUNT ];
      private float[] mCyArray     = new float[ COUNT * COUNT ];
      private float[] mRadiusArray = new float[ COUNT * COUNT ];

      public CubeGridDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            mSize = Math.min( bounds.width(), bounds.height() );
            mRectSize = mSize * 1f / 3;
            mHalfRectSize = mRectSize / 2;

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int dx = ( getWidth() - mSize ) / 2;
            int dy = ( getHeight() - mSize ) / 2;
            canvas.translate( dx, dy );

            for( int i = 0; i < COUNT; i++ ) {
                  for( int j = 0; j < COUNT; j++ ) {
                        int k = i * COUNT + j;
                        canvas.drawRect(
                            mCxArray[ k ] - mRadiusArray[ k ],
                            mCyArray[ k ] - mRadiusArray[ k ],
                            mCxArray[ k ] + mRadiusArray[ k ],
                            mCyArray[ k ] + mRadiusArray[ k ],
                            mPaint
                        );
                  }
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            for( int i = 0; i < COUNT; i++ ) {

                  float cy = mHalfRectSize + mRectSize * i;
                  for( int j = 0; j < COUNT; j++ ) {

                        float cx = mHalfRectSize + mRectSize * j;
                        float radius = calculateRadius( calculateProgress( i * 3 + j, progress ) );

                        int k = i * COUNT + j;
                        mCxArray[ k ] = cx;
                        mCyArray[ k ] = cy;
                        mRadiusArray[ k ] = radius;
                  }
            }

            invalidateSelf();
      }

      private float calculateProgress ( int i, float progress ) {

            int index;
            if( i == 6 ) {
                  index = 0;
            } else if( i == 3 || i == 7 ) {
                  index = 1;
            } else if( i == 0 || i == 4 || i == 8 ) {
                  index = 2;
            } else if( i == 1 || i == 5 ) {
                  index = 3;
            } else {
                  index = 4;
            }

            float low = 2 * index;
            float high = low + 10;

            progress *= 24;

            if( progress < low ) {
                  return 0;
            } else if( progress < high ) {
                  return ( progress - low ) / 10;
            } else {
                  return 1;
            }
      }

      private float calculateRadius ( float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
            }

            return mHalfRectSize * ( 1 - progress );
      }
}
