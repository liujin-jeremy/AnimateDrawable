package tech.liujin.drawable.progress;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2018-11-27:11:02
 */
public class WrongRightDrawable extends ProgressDrawable {

      private float[] mStartLineLeft;
      private float[] mStartLineRight;
      private float[] mEndLineLeft;
      private float[] mEndLineRight;

      private int           mStartColor    = Color.BLUE;
      private int           mStopColor     = Color.RED;
      private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            int size = Math.min( bounds.width(), bounds.height() ) * 4 / 5;

            int offset = size / 2;
            mStartLineLeft = new float[]{ -offset, -offset, offset, offset };
            mStartLineRight = new float[]{ -offset, offset, offset, -offset };

            offset = size / 6;
            int startX = -offset;
            int startY = bounds.height() * 3 / 10;
            int left = ( size >> 1 ) - offset;
            int right = size - left;

            mEndLineLeft = new float[]{ -left + startX, -left + startY, startX, startY };
            mEndLineRight = new float[]{ startX, startY, right + startX, -right + startY };

            mPaint.setStrokeWidth( size >> 3 );
      }

      @Override
      protected void draw (
          @NonNull Canvas canvas, float progress ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );

            int color = (Integer) mArgbEvaluator.evaluate( progress, mStartColor, mStopColor );
            mPaint.setColor( color );

            float startX =
                mStartLineLeft[ 0 ] + ( mEndLineLeft[ 0 ] - mStartLineLeft[ 0 ] ) * progress;
            float startY =
                mStartLineLeft[ 1 ] + ( mEndLineLeft[ 1 ] - mStartLineLeft[ 1 ] ) * progress;
            float stopX =
                mStartLineLeft[ 2 ] + ( mEndLineLeft[ 2 ] - mStartLineLeft[ 2 ] ) * progress;
            float stopY =
                mStartLineLeft[ 3 ] + ( mEndLineLeft[ 3 ] - mStartLineLeft[ 3 ] ) * progress;
            canvas.drawLine( startX, startY, stopX, stopY, mPaint );

            startX =
                mStartLineRight[ 0 ] + ( mEndLineRight[ 0 ] - mStartLineRight[ 0 ] ) * progress;
            startY =
                mStartLineRight[ 1 ] + ( mEndLineRight[ 1 ] - mStartLineRight[ 1 ] ) * progress;
            stopX = mStartLineRight[ 2 ] + ( mEndLineRight[ 2 ] - mStartLineRight[ 2 ] ) * progress;
            stopY = mStartLineRight[ 3 ] + ( mEndLineRight[ 3 ] - mStartLineRight[ 3 ] ) * progress;
            canvas.drawLine( startX, startY, stopX, stopY, mPaint );
      }
}
