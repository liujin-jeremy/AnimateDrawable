package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import java.util.Locale;

/**
 * @author Liujin 2019/5/13:15:28:29
 */
public abstract class TextCenterProgressDrawable extends TextProgressDrawable {

      protected float mDY;

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );
            mTextPaint.setTextSize( size * 1f / 6 );

            FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mDY = ( getHeight() - fontMetrics.bottom + fontMetrics.top ) / 2 - fontMetrics.top;
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int show = (int) ( progress * 100 );
            canvas.drawText( String.format( Locale.getDefault(), "%d%%", show ), getBounds().width() >> 1, mDY, mTextPaint );
      }

      public void setTextColor ( @ColorInt int textColor ) {

            mTextPaint.setColor( textColor );
      }
}
