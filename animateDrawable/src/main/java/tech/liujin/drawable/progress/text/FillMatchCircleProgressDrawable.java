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
public class FillMatchCircleProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mRadius;
      private int   mCircleColor = Color.parseColor( "#6A1B17" );
      private int   mFillColor   = Color.RED;

      public FillMatchCircleProgressDrawable ( ) {

            mRectF = new RectF();

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
            mTextPaint.setColor( Color.WHITE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );

            float rx = width * 1f / 2;
            float ry = height * 1f / 2;
            mRadius = size * 1f / 2 - 2;

            mRectF.set(
                rx - mRadius,
                ry - mRadius,
                rx + mRadius,
                ry + mRadius
            );
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            Rect bounds = getBounds();
            mPaint.setColor( mCircleColor );
            canvas.drawCircle( bounds.width() >> 1, bounds.height() >> 1, mRadius, mPaint );

            float v = 180 * progress;
            float start = 90 - v;
            float end = 90 + v;
            mPaint.setColor( mFillColor );
            canvas.drawArc( mRectF, start, end - start, false, mPaint );

            super.draw( canvas, progress );
      }
}
