package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import java.util.Locale;

/**
 * @author Liujin 2019/5/13:11:48:33
 */
public class CircleTextProgressDrawable extends TextProgressDrawable {

      private RectF mRectF = new RectF();
      private float mDY;

      public CircleTextProgressDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mTextPaint.setTextAlign( Align.CENTER );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );
            float strokeWidth = size * 1f / 16;
            mTextPaint.setTextSize( size * 1f / 6 );
            mPaint.setStrokeWidth( strokeWidth );

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            float radius = size * 1f / 2 - strokeWidth;
            mRectF.set(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius
            );

            FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mDY = ( getHeight() - fontMetrics.bottom + fontMetrics.top ) / 2 - fontMetrics.top;
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            canvas.drawArc( mRectF, 90, 360 * progress, false, mPaint );
            Rect bounds = getBounds();
            int width = bounds.width();
            int show = (int) ( progress * 100 );
            canvas.drawText( String.format( Locale.getDefault(), "%d%%", show ), width >> 1, mDY, mTextPaint );
      }

      public void setColor ( @ColorInt int color ) {

            setTextColor( color );
            setCircleColor( color );
      }

      public void setCircleColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }

      public void setTextColor ( @ColorInt int color ) {

            mTextPaint.setColor( color );
      }
}
