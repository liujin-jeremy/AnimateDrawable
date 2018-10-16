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

      public CircleLoadingDrawable ( int size ) {

            super();
            mSize = size;
            mPaint.setStyle( Paint.Style.STROKE );
            mPaint.setStrokeWidth( 5 );

            mSrcPath = new Path();
            mPathMeasure = new PathMeasure();
            mDstPath = new Path();

            initPath();
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
            float strokeWidth = mPaint.getStrokeWidth();

            RectF rectF = new RectF();

            rectF.set(
                strokeWidth,
                strokeWidth,
                size - strokeWidth,
                size - strokeWidth
            );

            mSrcPath.reset();
            mSrcPath.addArc( rectF, -90, 360f );
            mPathMeasure.setPath( mSrcPath, true );

            mLength = mPathMeasure.getLength();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

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

      @Override
      public void setStrokeWidth ( int strokeWidth ) {

            super.setStrokeWidth( strokeWidth );
            initPath();
      }
}
