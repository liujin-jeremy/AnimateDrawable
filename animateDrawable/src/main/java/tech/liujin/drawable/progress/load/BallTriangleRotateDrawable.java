package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:12:23
 */
public class BallTriangleRotateDrawable extends ProgressDrawable {

      private float mDis;
      private float mRadius;
      private float mDegree;
      private float mCy;

      public BallTriangleRotateDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {


            int size = Math.min( bounds.width(), bounds.height() );

            mDis = size * 1f / 6;
            mRadius = size * 1f / 7;

            super.onBoundsChange( bounds );

      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            mDegree = 360 * progress;
            if( progress <= 0.5f ) {
                  progress *= 2;
                  mCy = mDis + mDis * ( 1 - progress );
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  mCy = mDis + mDis * ( progress );
            }

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int cX = getWidth() >> 1;
            int cY = getHeight() >> 1;
            canvas.translate( cX, cY );

            canvas.rotate( mDegree );
            for( int i = 0; i < 3; i++ ) {
                  int save = canvas.save();
                  canvas.rotate( 120 * i );
                  canvas.drawCircle( 0, -mCy, mRadius, mPaint );
                  canvas.restoreToCount( save );
            }
      }
}
