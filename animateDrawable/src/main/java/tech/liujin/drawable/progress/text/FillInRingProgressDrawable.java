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
public class FillInRingProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mRadius;
      private float mRingWidth;

      public FillInRingProgressDrawable ( ) {

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

            mRingWidth = size * 1f / 16;
            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            mRadius = size * 1f / 2 - 2 - mRingWidth / 2;

            float radius = mRadius - mRingWidth;
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

            mPaint.setStyle( Style.STROKE );
            mPaint.setStrokeWidth( mRingWidth );
            canvas.drawCircle( width >> 1, height >> 1, mRadius, mPaint );

            mPaint.setStyle( Style.FILL );
            float v = 180 * progress;
            float start = 90 - v;
            float end = 90 + v;
            canvas.drawArc( mRectF, start, end - start, false, mPaint );

            super.draw( canvas, progress );
      }

      public void setColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }
}
