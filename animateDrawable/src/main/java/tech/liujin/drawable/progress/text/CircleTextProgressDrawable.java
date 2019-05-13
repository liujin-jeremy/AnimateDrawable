package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:11:48:33
 */
public class CircleTextProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF = new RectF();

      public CircleTextProgressDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mPaint.setColor( Color.parseColor( "#4DFDFF" ) );
            mTextPaint.setColor( Color.parseColor( "#4DFDFF" ) );
            mTextPaint.setTextAlign( Align.CENTER );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );
            float strokeWidth = size * 1f / 12;
            mTextPaint.setTextSize( size * 1f / 6 );
            mPaint.setStrokeWidth( strokeWidth );

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            float radius = size * 1f / 2 - strokeWidth / 2 - 2;
            mRectF.set(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius
            );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            canvas.drawArc( mRectF, 90, 360 * progress, false, mPaint );
            super.draw( canvas, progress );
      }

      public void setColor ( @ColorInt int color ) {

            setTextColor( color );
            setCircleColor( color );
      }

      public void setCircleColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }
}
