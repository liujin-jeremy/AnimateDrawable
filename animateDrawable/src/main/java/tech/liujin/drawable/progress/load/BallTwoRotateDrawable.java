package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-19:8:30
 */
public class BallTwoRotateDrawable extends ProgressDrawable {

      private int   mTotalRadius;
      private int   mSpace;
      private float mDegrees;
      private float mLeft;
      private float mRight;

      public BallTwoRotateDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );
            mTotalRadius = size / 5;
            mSpace = size / 40;

            super.onBoundsChange( bounds );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            mDegrees = 360 * progress;
            mLeft = mTotalRadius * calculateProgress( progress );
            mRight = mTotalRadius - mLeft;

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );

            canvas.rotate( mDegrees );
            canvas.drawCircle( -mSpace - mLeft, 0, mLeft, mPaint );
            canvas.drawCircle( mSpace + mRight, 0, mRight, mPaint );
      }

      private float calculateProgress ( float progress ) {

            if( progress <= 0.5f ) {

                  progress *= 2;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
            }
            return progress;
      }
}
