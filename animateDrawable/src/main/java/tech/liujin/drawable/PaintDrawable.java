package tech.liujin.drawable;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/**
 * @author Liujin 2019/5/18:10:55:19
 */
public abstract class PaintDrawable extends Drawable {

      /**
       * 画笔
       */
      protected Paint mPaint = new Paint( Paint.ANTI_ALIAS_FLAG );

      public PaintDrawable ( ) {

            mPaint.setStrokeJoin( Paint.Join.ROUND );
            mPaint.setStrokeCap( Paint.Cap.ROUND );
      }

      /**
       * 获取画笔
       */
      public Paint getPaint ( ) {

            return mPaint;
      }

      /**
       * 设置画笔颜色,即:drawable颜色
       *
       * @param color 颜色
       */
      public void setColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }

      /**
       * 设置画笔样式
       */
      public void setPaintStyle ( Style style ) {

            mPaint.setStyle( style );
      }

      /**
       * 绘制区域宽度
       *
       * @return 宽度
       */
      public int getWidth ( ) {

            return getBounds().width();
      }

      /**
       * 绘制区域高度
       *
       * @return 高度
       */
      public int getHeight ( ) {

            return getBounds().height();
      }

      @Override
      public void setAlpha ( int alpha ) {

            mPaint.setAlpha( alpha );
      }

      @Override
      public void setColorFilter ( @Nullable ColorFilter colorFilter ) {

            mPaint.setColorFilter( colorFilter );
      }

      @Override
      public int getOpacity ( ) {

            return PixelFormat.TRANSPARENT;
      }
}
