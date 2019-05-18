package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:16:24:35
 */
public class FillCircleProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mStart;
      private float mEnd;

      public FillCircleProgressDrawable ( ) {

            mRectF = new RectF();

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
            mTextPaint.setColor( Color.WHITE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {


            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );

            float rx = width * 1f / 2;
            float ry = height * 1f / 2;
            float radius = size * 1f / 2 - 2;

            mRectF.set(
                rx - radius,
                ry - radius,
                rx + radius,
                ry + radius
            );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.drawArc( mRectF, mStart, mEnd - mStart, false, mPaint );
            super.draw( canvas );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            float v = 180 * mProgress;
            mStart = 90 - v;
            mEnd = 90 + v;

            invalidateSelf();
      }
}
