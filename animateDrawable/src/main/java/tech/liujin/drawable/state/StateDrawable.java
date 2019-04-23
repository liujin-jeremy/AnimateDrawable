package tech.liujin.drawable.state;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class StateDrawable extends Drawable {

      /**
       * 画笔
       */
      protected Paint mPaint;
      /**
       * 当前进度
       */
      protected int   mState;

      public StateDrawable ( ) {

            mPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
      }

      /**
       * 获取画笔
       */
      public Paint getPaint ( ) {

            return mPaint;
      }

      /**
       * 设置画笔样式
       */
      public void setPaintStyle ( Style style ) {

            mPaint.setStyle( style );
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

      public void setState ( int state ) {

            if( state != mState ) {

                  mState = state;
                  invalidateSelf();
            }
      }

      public int getCurrentState ( ) {

            return mState;
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            draw( canvas, mState );
      }

      /**
       * 根据进度值 绘制内容
       *
       * @param canvas : 画布
       * @param state : 状态
       */
      protected abstract void draw ( @NonNull Canvas canvas, int state );

      /**
       * 绘制区域宽度
       *
       * @return 宽度
       */
      protected int getWidth ( ) {

            return getBounds().width();
      }

      /**
       * 绘制区域高度
       *
       * @return 高度
       */
      protected int getHeight ( ) {

            return getBounds().height();
      }
}

