package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author wuxio 2018-05-12:23:15
 */
public class CirclePathDrawable extends ProgressDrawable {

      private Path        mSrcPath;
      private PathMeasure mPathMeasure;
      private Path        mDstPath;
      private int         mSize;
      private float       mLength;

      public CirclePathDrawable ( ) {

            super();
            mPaint.setStyle( Paint.Style.STROKE );

            mSrcPath = new Path();
            mDstPath = new Path();

            mPathMeasure = new PathMeasure();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            mSize = Math.min( bounds.width(), bounds.height() );

            float strokeWidth = mSize / 20;
            mPaint.setStrokeWidth( strokeWidth );

            RectF rectF = new RectF();

            rectF.set(
                strokeWidth / 2,
                strokeWidth / 2,
                mSize - strokeWidth / 2,
                mSize - strokeWidth / 2
            );

            mSrcPath.reset();
            mSrcPath.addArc( rectF, -90, 359.9f );
            mPathMeasure.setPath( mSrcPath, true );

            mLength = mPathMeasure.getLength();
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int width = getWidth();
            int height = getHeight();
            int dX = ( width - mSize ) >> 1;
            int dY = ( height - mSize ) >> 1;
            canvas.translate( dX, dY );

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
