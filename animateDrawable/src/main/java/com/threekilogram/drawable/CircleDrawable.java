package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * @author wuxio 2018-05-12:23:15
 */
public class CircleDrawable extends ProgressDrawable {

      private Path        mSrcPath;
      private PathMeasure mPathMeasure;
      private Path        mDstPath;
      private int         mSize;
      private float       mLength;

      public CircleDrawable ( ) {

            this( 0 );
      }

      public CircleDrawable ( int size ) {

            super();
            mSize = size;
            mPaint.setStyle( Paint.Style.STROKE );
            mPaint.setStrokeWidth( 5 );

            mSrcPath = new Path();
            mDstPath = new Path();
      }

      @Override
      public int getIntrinsicWidth ( ) {

            if( mSize != 0 ) {
                  return mSize;
            }
            return getIntrinsicWidth();
      }

      @Override
      public int getIntrinsicHeight ( ) {

            if( mSize != 0 ) {
                  return mSize;
            }
            return getIntrinsicHeight();
      }

      private void initPath ( Canvas canvas ) {

            if( mPathMeasure == null ) {

                  mPathMeasure = new PathMeasure();

                  int size = 0;
                  if( mSize != 0 ) {
                        size = mSize;
                  } else {
                        Rect bounds = canvas.getClipBounds();
                        size = Math.min( bounds.width(), bounds.height() );
                  }

                  float strokeWidth = mPaint.getStrokeWidth();

                  RectF rectF = new RectF();

                  rectF.set(
                      strokeWidth / 2,
                      strokeWidth / 2,
                      size - strokeWidth / 2,
                      size - strokeWidth / 2
                  );

                  mSrcPath.reset();
                  mSrcPath.addArc( rectF, -90, 359.9f );
                  mPathMeasure.setPath( mSrcPath, true );

                  mLength = mPathMeasure.getLength();
            }
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            initPath( canvas );

            float fraction = mProgress;

            mDstPath.reset();
            mDstPath.moveTo( mSize >> 1, 0 );

            final float middle = 0.5f;
            if( fraction <= middle ) {

                  mPathMeasure.getSegment(
                      0,
                      mLength * fraction * 2,
                      mDstPath,
                      true
                  );
            } else {

                  mPathMeasure.getSegment(
                      mLength * ( fraction - 0.5f ) * 2,
                      mLength,
                      mDstPath,
                      true
                  );
            }

            canvas.drawPath( mDstPath, mPaint );
      }
}
