package com.threekilogram.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class ProgressDrawable extends Drawable {

      /**
       * 画笔
       */
      @SuppressWarnings("WeakerAccess")
      protected Paint mPaint;
      /**
       * 当前进度
       */
      protected float mProgress;
      /**
       * bounds
       */
      protected Rect  mRect;

      public ProgressDrawable ( ) {

            mPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
            mPaint.setStrokeJoin( Paint.Join.ROUND );
            mPaint.setStrokeCap( Paint.Cap.ROUND );
            mRect = new Rect();
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
       * 设置线宽
       */
      public void setStrokeWidth ( int strokeWidth ) {

            mPaint.setStrokeWidth( strokeWidth );
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

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            draw( canvas, mProgress );
      }

      /**
       * 根据进度值{@link #mProgress}绘制内容
       *
       * @param canvas :画布
       * @param progress 进度值
       */
      protected abstract void draw ( @NonNull Canvas canvas, float progress );

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mRect.set( bounds );
      }

      /**
       * 绘制区域宽度
       *
       * @return 宽度
       */
      protected int getWidth ( ) {

            return mRect.width();
      }

      /**
       * 绘制区域高度
       *
       * @return 高度
       */
      protected int getHeight ( ) {

            return mRect.height();
      }

      /**
       * 设置进度
       *
       * @param progress 进度
       */
      public void setProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            if( mProgress == progress ) {
                  return;
            }
            mProgress = progress;
      }

      /**
       * 获取当前进度
       */
      public float getProgress ( ) {

            return mProgress;
      }

      /**
       * 设置进度值,同时重绘
       *
       * @param progress 进度
       */
      public void setDrawProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            if( mProgress == progress ) {
                  return;
            }
            mProgress = progress;
            invalidateSelf();
      }
}