package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:16:04
 */
public class BallCircleScaleDrawable extends ProgressDrawable {

      private float mRadius;
      private int   mSize;
      private float mMinScale = 0.4f;
      private float mMinRadius;
      private float mDRadius;

      public BallCircleScaleDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mSize = Math.min( bounds.width(), bounds.height() );
            mRadius = mSize / 10;
            mMinRadius = mRadius * mMinScale;
            mDRadius = mRadius - mMinRadius;
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            int width = getWidth();
            int height = getHeight();
            canvas.translate( width >> 1, height >> 1 );

            for( int i = 0; i < 12; i++ ) {

                  canvas.rotate( 30 );
                  float v = calculateProgress( i, progress );
                  canvas.drawCircle( 0, -mSize / 2 + mRadius,
                                     calculateRadius( v ), mPaint
                  );
            }
      }

      private float calculateProgress ( int i, float progress ) {

            progress = progress - i * ( 1f / 11 );
            if( progress < 0 ) {
                  progress = -progress;
            }
            return progress;
      }

      private float calculateRadius ( float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2;
                  mPaint.setAlpha( (int) ( 205 * progress ) );
                  return mMinRadius + mDRadius * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  mPaint.setAlpha( (int) ( 205 * ( 1 - progress ) ) );
                  return mRadius - mDRadius * progress;
            }
      }
}
