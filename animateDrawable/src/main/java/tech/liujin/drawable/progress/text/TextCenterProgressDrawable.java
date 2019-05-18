package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import java.util.Locale;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2019/5/13:15:28:29
 */
public abstract class TextCenterProgressDrawable extends ProgressDrawable {

      /**
       * 绘制文字
       */
      protected TextPaint mTextPaint;
      /**
       * 文字纵向中线距离
       */
      protected float     mDY;

      public TextCenterProgressDrawable ( ) {

            mTextPaint = new TextPaint( TextPaint.ANTI_ALIAS_FLAG );
            mTextPaint.setTextAlign( Align.CENTER );
      }

      public TextPaint getTextPaint ( ) {

            return mTextPaint;
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );
            mTextPaint.setTextSize( size * 1f / 6 );

            FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            mDY = ( getHeight() - fontMetrics.bottom + fontMetrics.top ) / 2 - fontMetrics.top;

            super.onBoundsChange( bounds );
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int show = (int) ( mProgress * 100 );
            canvas.drawText( String.format( Locale.getDefault(), "%d%%", show ), getBounds().width() >> 1, mDY, mTextPaint );
      }

      public void setTextColor ( @ColorInt int textColor ) {

            mTextPaint.setColor( textColor );
      }
}
