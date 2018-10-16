package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * @author wuxio 2018-05-12:23:15
 */
public class CircleLoadingDrawable extends BaseProgressDrawable {

      private Path        mSrcPath;
      private PathMeasure mPathMeasure;
      private Path        mDstPath;
      private int         mSize;
      private float       mLength;
      private float       mProgress;
      private int         mDrawCount;

      public CircleLoadingDrawable ( int size ) {

            super();
            mSize = size;
            mPaint.setStyle( Paint.Style.STROKE );
            mPaint.setStrokeWidth( 5 );

            mSrcPath = new Path();
            mPathMeasure = new PathMeasure();
            mDstPath = new Path();
      }

      @Override
      public int getIntrinsicWidth ( ) {

            return mSize;
      }

      @Override
      public int getIntrinsicHeight ( ) {

            return mSize;
      }

      private void initPath ( ) {

            int size = mSize;
            int r = size >> 1;
            float strokeWidth = mPaint.getStrokeWidth();

            RectF rectF = new RectF();

            rectF.set(
                strokeWidth,
                strokeWidth,
                size - strokeWidth,
                size - strokeWidth
            );

            mSrcPath.addArc( rectF, -90, 359.9f );
            mPathMeasure.setPath( mSrcPath, true );

            mLength = mPathMeasure.getLength();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            float fraction = mProgress;

            mDstPath.reset();
            mDstPath.moveTo( mSize >> 1, 0 );

            if( mDrawCount % 2 == 0 ) {

                  mPathMeasure.getSegment( 0, mLength * fraction, mDstPath, true );
            } else {

                  mPathMeasure.getSegment( mLength * fraction, mLength, mDstPath, true );
            }
            mDrawCount++;

            canvas.drawPath( mDstPath, mPaint );
      }

      public void setStrokeWidth ( int strokeWidth ) {

            mPaint.setStrokeWidth( strokeWidth );
      }

      public void setStrokeColor ( int color ) {

            mPaint.setColor( color );
      }

      @Override
      public void setProgress ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }
}
