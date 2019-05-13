package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Cap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-23:10:51
 */
public class StrokeSkipDrawable extends ProgressDrawable {

      private int mStrokeWidth;

      public StrokeSkipDrawable ( ) {

            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mStrokeWidth = bounds.width() / 9;

            mPaint.setStrokeWidth( mStrokeWidth );
            mPaint.setStrokeCap( Cap.SQUARE );
      }

      @Override
      protected void draw (
          @NonNull Canvas canvas, float progress ) {

            int height = getHeight();
            canvas.translate( 0, height );

            int offset = calculateOffset( progress );

            int startX = mStrokeWidth + mStrokeWidth / 2;
            canvas.drawLine( startX, 0, startX, -height + offset, mPaint );

            startX = mStrokeWidth + mStrokeWidth / 2 + 2 * mStrokeWidth;
            canvas.drawLine( startX, 0, startX, -height / 2 - offset, mPaint );

            startX = mStrokeWidth + mStrokeWidth / 2 + 2 * mStrokeWidth * 2;
            canvas.drawLine( startX, 0, startX, -height / 4 - offset * 2, mPaint );

            startX = mStrokeWidth + mStrokeWidth / 2 + 2 * mStrokeWidth * 3;
            canvas.drawLine( startX, 0, startX, -height * 0.8f + offset * 1.5f, mPaint );
      }

      private int calculateOffset ( float progress ) {

            if( progress <= 0.5f ) {
                  progress *= 2;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
            }

            return (int) ( getHeight() / 2 * progress );
      }
}
