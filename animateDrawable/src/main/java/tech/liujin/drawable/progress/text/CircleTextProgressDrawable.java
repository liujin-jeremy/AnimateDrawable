package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:11:48:33
 */
public class CircleTextProgressDrawable extends TextProgressDrawable {

      private int mRadius;

      public CircleTextProgressDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            mRadius = Math.min( width, height ) >> 1;

            mTextPaint.setTextSize( mRadius >> 2 );
            mPaint.setStrokeWidth( mRadius >> 3 );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );

            canvas.drawCircle( width >> 1, height >> 1, size, mPaint );
      }
}
