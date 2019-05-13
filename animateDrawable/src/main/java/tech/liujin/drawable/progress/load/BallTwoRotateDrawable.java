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

      private int mTotalRadius;
      private int mSpace;

      public BallTwoRotateDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            canvas.translate( getWidth() / 2, getHeight() / 2 );
            float left = mTotalRadius * calculateProgress( progress );
            float right = mTotalRadius - left;

            canvas.rotate( 360 * progress );

            canvas.drawCircle( -mSpace - left, 0, left, mPaint );
            canvas.drawCircle( mSpace + right, 0, right, mPaint );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int size = Math.min( bounds.width(), bounds.height() );
            mTotalRadius = size / 5;
            mSpace = size / 40;
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
