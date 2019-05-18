package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:16:52
 */
public class PacManDrawable extends ProgressDrawable {

      private static final float MIN_ANGLE = 4;
      private static final float MAX_ANGLE = 36;
      private static final float D_ANGLE   = MAX_ANGLE - MIN_ANGLE;

      private RectF mRectF;
      private float mSize;
      private float mRadius;
      private float mAngle;
      private float mCx;

      public PacManDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );

            mRectF = new RectF();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int min = Math.min( bounds.width(), bounds.height() );
            mSize = min * 4f / 5;
            mRadius = min * 1f / 10;
            float v = mSize / 2;
            mRectF.set( -v, -v, v, v );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );

            canvas.drawArc( mRectF, -mAngle, -360 + mAngle * 2, true, mPaint );
            canvas.drawCircle( mCx, 0, mRadius, mPaint );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            float left = getWidth() - mSize / 2;
            mAngle = calculateAngle( progress );
            mCx = left + mRadius - ( left + mRadius * 2 ) * progress;

            invalidateSelf();
      }

      private float calculateAngle ( float progress ) {

            if( progress < 0.5f ) {
                  progress *= 2;
                  return MIN_ANGLE + ( D_ANGLE ) * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  return MAX_ANGLE - ( D_ANGLE ) * progress;
            }
      }
}
