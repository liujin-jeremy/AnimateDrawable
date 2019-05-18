package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:15:37:53
 */
public class FillOutCircleProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mRadius;
      private int   mCircleColor = Color.parseColor( "#6A1B17" );
      private int   mFillColor   = Color.RED;

      public FillOutCircleProgressDrawable ( ) {

            mRectF = new RectF();

            mPaint.setStyle( Style.FILL );
            mTextPaint.setColor( Color.WHITE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );
            mRadius = size * 3f / 8;

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            float radius = size * 1f / 2 - 2;

            mRectF.set(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius
            );
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();

            mPaint.setColor( mCircleColor );
            canvas.drawCircle( width >> 1, height >> 1, mRadius, mPaint );

            float v = 180 * progress;
            float start = 90 - v;
            float end = 90 + v;
            mPaint.setColor( mFillColor );
            canvas.drawArc( mRectF, start, end - start, false, mPaint );

            super.draw( canvas, progress );
      }

      public void setCircleColor ( @ColorInt int circleColor ) {

            mCircleColor = circleColor;
      }

      public void setFillColor ( @ColorInt int fillColor ) {

            mFillColor = fillColor;
      }
}
