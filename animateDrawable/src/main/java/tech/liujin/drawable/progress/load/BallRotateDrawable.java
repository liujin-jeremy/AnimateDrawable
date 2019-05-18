package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:10:54
 */
public class BallRotateDrawable extends ProgressDrawable {

      private float mRadius;
      private int   mSpace;
      private float mScale;
      private float mDegrees;

      public BallRotateDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );

            mSpace = size / 9;
            mRadius = ( size * 1f - 3 * mSpace ) / 6;

            super.onBoundsChange( bounds );

      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            mDegrees = 360 * mProgress;
            if( progress <= 0.5f ) {
                  progress *= 2;
                  mScale = 1f - 0.5f * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  mScale = 0.5f + 0.5f * progress;
            }

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int cX = getWidth() >> 1;
            int cY = getHeight() >> 1;
            canvas.translate( cX, cY );

            canvas.rotate( mDegrees );
            canvas.scale( mScale, mScale );

            canvas.drawCircle( 0, 0, mRadius, mPaint );
            canvas.drawCircle( -mSpace - mRadius * 2, 0, mRadius, mPaint );
            canvas.drawCircle( +mSpace + mRadius * 2, 0, mRadius, mPaint );
      }
}
