package com.threekilogram.drawable.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.threekilogram.drawable.AnimateDrawableUtil;
import com.threekilogram.drawable.BaseProgressDrawable;

/**
 * @author Liujin 2018-10-16:13:14
 */
public class AnimateDrawableView extends View {

      private static final int DEFAULT_SIZE = 200;

      private BaseProgressDrawable mDrawable;
      private AnimateDrawableUtil  mUtil;

      public AnimateDrawableView ( Context context ) {

            this( context, null, 0 );
      }

      public AnimateDrawableView (
          Context context, @Nullable AttributeSet attrs ) {

            this( context, attrs, 0 );
      }

      public AnimateDrawableView (
          Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {

            super( context, attrs, defStyleAttr );
      }

      public void setDrawable ( BaseProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
            mUtil = new AnimateDrawableUtil( this );
      }

      public BaseProgressDrawable getDrawable ( ) {

            return mDrawable;
      }

      @Override
      protected void onMeasure ( int widthMeasureSpec, int heightMeasureSpec ) {

            int widthMode = MeasureSpec.getMode( widthMeasureSpec );
            int widthSize = MeasureSpec.getSize( widthMeasureSpec );

            int heightMode = MeasureSpec.getMode( heightMeasureSpec );
            int heightSize = MeasureSpec.getSize( heightMeasureSpec );

            int width = 0;
            int height = 0;

            if( widthMode == MeasureSpec.EXACTLY ) {
                  width = widthSize;
            } else {
                  width = DEFAULT_SIZE;
            }

            if( heightMode == MeasureSpec.EXACTLY ) {
                  height = heightSize;
            } else {
                  height = DEFAULT_SIZE;
            }

            setMeasuredDimension( width, height );
      }

      @Override
      protected void onDraw ( Canvas canvas ) {

            try {
                  mUtil.onDraw( canvas );
            } catch(Exception e) {
                  /* noting */
            }
      }

      /**
       * 设置进度
       *
       * @param progress 进度
       */
      public void setProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            mDrawable.setProgress( progress );
      }

      /**
       * 获取当前进度
       */
      public float getProgress ( ) {

            return mDrawable.getProgress();
      }

      /**
       * 设置进度值,同时重绘
       *
       * @param progress 进度
       */
      public void setDrawProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            mDrawable.setProgress( progress );
            invalidate();
      }

      /**
       * 设置执行动画次数
       *
       * @param count 次数
       */
      public void setCount ( int count ) {

            mUtil.setCount( count );
      }

      /**
       * 获取设置的执行动画次数
       */
      public int getCount ( ) {

            return mUtil.getCount();
      }

      /**
       * 设置动画时长
       */
      public void setDuration ( int duration ) {

            mUtil.setDuration( duration );
      }

      /**
       * 获取设置的动画时长
       */
      public int getDuration ( ) {

            return mUtil.getDuration();
      }

      /**
       * 测试是否正在进行动画
       *
       * @return true 正在进行动画
       */
      public boolean isRunning ( ) {

            return mUtil.isRunning();
      }

      /**
       * 获取已经进行的次数
       */
      public int getFinishedCount ( ) {

            return mUtil.getFinishedCount();
      }

      /**
       * 设置差值器
       */
      public void setInterpolator ( TimeInterpolator interpolator ) {

            mUtil.setInterpolator( interpolator );
      }

      /**
       * 获取设置的差值器
       */
      public TimeInterpolator getInterpolator ( ) {

            return mUtil.getInterpolator();
      }

      /**
       * 开始动画
       */
      public void start ( ) {

            mUtil.start();
      }

      /**
       * 结束动画
       */
      public void stop ( ) {

            mUtil.stop();
      }
}
