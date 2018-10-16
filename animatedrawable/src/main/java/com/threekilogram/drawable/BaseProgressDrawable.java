package com.threekilogram.drawable;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class BaseProgressDrawable extends Drawable {

      protected Paint mPaint;

      public BaseProgressDrawable ( ) {

            mPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
            mPaint.setStrokeJoin( Paint.Join.ROUND );
            mPaint.setStrokeCap( Paint.Cap.ROUND );
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

      /**
       * 设置进度
       *
       * @param progress 进度
       */
      public abstract void setProgress ( @FloatRange(from = 0f, to = 1f) float progress );
}
