package com.threekilogram.drawable;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class BaseProgressDrawable extends Drawable {

      /**
       * 画笔
       */
      protected Paint mPaint;
      /**
       * 当前进度
       */
      protected float mProgress;

      public BaseProgressDrawable ( ) {

            mPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
            mPaint.setStrokeJoin( Paint.Join.ROUND );
            mPaint.setStrokeCap( Paint.Cap.ROUND );
      }

      public void setColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }

      public void setStrokeWidth ( int strokeWidth ) {

            mPaint.setStrokeWidth( strokeWidth );
      }

      public void setStrokeColor ( int color ) {

            mPaint.setColor( color );
      }

      public Paint getPaint ( ) {

            return mPaint;
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
      public void setProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            mProgress = progress;
      }

      /**
       * 设置进度同时请求重绘
       *
       * @param progress 进度
       */
      public void setDrawProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            setProgress( progress );
            invalidateSelf();
      }
}
