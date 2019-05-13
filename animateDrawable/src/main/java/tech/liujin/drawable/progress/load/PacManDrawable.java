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

      public PacManDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );

            mRectF = new RectF();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            int min = Math.min( bounds.width(), bounds.height() );
            mSize = min * 4 / 5;
            mRadius = min / 10;
            float v = mSize / 2;
            mRectF.set( -v, -v, v, v );
      }

      @Override
      protected void draw (
          @NonNull Canvas canvas, float progress ) {

            int height = getHeight();
            float dx = mSize / 2;
            float left = getWidth() - dx;
            canvas.translate( dx, height >> 1 );

            float angle = calculateAngle( progress );
            canvas.drawArc( mRectF, -angle, -360 + angle * 2, true, mPaint );

            float cx = left + mRadius - ( left + mRadius * 2 ) * progress;
            canvas.drawCircle( cx, 0, mRadius, mPaint );
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
