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
public class CubeTwoRotateDrawable extends ProgressDrawable {

      private float mRadius;
      private int   mSize;
      private int   mSpace;

      public CubeTwoRotateDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            mSize = Math.min( bounds.width(), bounds.height() );

            mSpace = mSize / 9;
            mRadius = ( mSize - 3 * mSpace ) / 6;
      }

      @Override
      protected void draw (
          @NonNull Canvas canvas, float progress ) {

            int cX = getWidth() >> 1;
            int cY = getHeight() >> 1;
            canvas.translate( cX, cY );
            float degrees = -45 + 360 * progress;
            canvas.rotate( degrees );

            float s;
            if( progress <= 0.5f ) {
                  progress *= 2;
                  s = 1f - 0.5f * progress;
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  s = 0.5f + 0.5f * progress;
            }
            canvas.scale( s, s );

            int save = canvas.save();
            canvas.translate( -mSpace - mRadius * 2, 0 );
            canvas.rotate( -degrees * 2 + 45 );
            canvas.drawRect( -mRadius, -mRadius, mRadius, mRadius, mPaint );
            canvas.restoreToCount( save );

            save = canvas.save();
            canvas.translate( mSpace + mRadius * 2, 0 );
            canvas.rotate( -degrees * 2 + 45 );
            canvas.drawRect( -mRadius, -mRadius, mRadius, mRadius, mPaint );
            canvas.restoreToCount( save );
      }
}
