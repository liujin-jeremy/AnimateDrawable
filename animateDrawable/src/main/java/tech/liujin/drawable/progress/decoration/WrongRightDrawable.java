package tech.liujin.drawable.progress.decoration;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-27:11:02
 */
public class WrongRightDrawable extends ProgressDrawable {

      private float[] mStartLineLeft;
      private float[] mStartLineRight;
      private float[] mEndLineLeft;
      private float[] mEndLineRight;
      private float   mFirstStartX;
      private float   mFirstStartY;
      private float   mFirstStopX;
      private float   mFirstStopY;
      private float   mSecondStartX;
      private float   mSecondStartY;
      private float   mSecondStopX;
      private float   mSecondStopY;

      private int           mStartColor    = Color.BLUE;
      private int           mStopColor     = Color.RED;
      private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

      @Override
      protected void onBoundsChange ( Rect bounds ) {

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

            super.onBoundsChange( bounds );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            int color = (Integer) mArgbEvaluator.evaluate( progress, mStartColor, mStopColor );
            mPaint.setColor( color );

            mFirstStartX = mStartLineLeft[ 0 ] + ( mEndLineLeft[ 0 ] - mStartLineLeft[ 0 ] ) * progress;
            mFirstStartY = mStartLineLeft[ 1 ] + ( mEndLineLeft[ 1 ] - mStartLineLeft[ 1 ] ) * progress;
            mFirstStopX = mStartLineLeft[ 2 ] + ( mEndLineLeft[ 2 ] - mStartLineLeft[ 2 ] ) * progress;
            mFirstStopY = mStartLineLeft[ 3 ] + ( mEndLineLeft[ 3 ] - mStartLineLeft[ 3 ] ) * progress;

            mSecondStartX = mStartLineRight[ 0 ] + ( mEndLineRight[ 0 ] - mStartLineRight[ 0 ] ) * progress;
            mSecondStartY = mStartLineRight[ 1 ] + ( mEndLineRight[ 1 ] - mStartLineRight[ 1 ] ) * progress;
            mSecondStopX = mStartLineRight[ 2 ] + ( mEndLineRight[ 2 ] - mStartLineRight[ 2 ] ) * progress;
            mSecondStopY = mStartLineRight[ 3 ] + ( mEndLineRight[ 3 ] - mStartLineRight[ 3 ] ) * progress;

            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );
            canvas.drawLine( mFirstStartX, mFirstStartY, mFirstStopX, mFirstStopY, mPaint );
            canvas.drawLine( mSecondStartX, mSecondStartY, mSecondStopX, mSecondStopY, mPaint );
      }
}
