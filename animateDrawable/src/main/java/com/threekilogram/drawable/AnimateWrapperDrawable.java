package com.threekilogram.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

/**
 * 包装一个{@link ProgressDrawable}使其具有动画效果,他会执行指定次数/指定时长的动画
 *
 * @author Liujin 2018-10-16:9:36
 */
public class AnimateWrapperDrawable extends ProgressDrawable implements Animatable {

      /**
       * drawable
       */
      protected ProgressDrawable mDrawable;
      /**
       * start time
       */
      protected long             mStartTime    = -1;
      /**
       * start progress
       */
      protected float            mStartProgress;
      /**
       * 时长
       */
      protected int              mDuration     = 1000;
      /**
       * 播放总数
       */
      protected int              mCount        = 1;
      /**
       * 差值器
       */
      protected TimeInterpolator mInterpolator = new LinearInterpolator();

      /**
       * 包装一个{@link ProgressDrawable}使其具有动画能力,一帧播放完成之后才播放下一帧
       */
      public AnimateWrapperDrawable ( ProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
      }

      /**
       * @return 设置的{@link ProgressDrawable}
       */
      public ProgressDrawable getDrawable ( ) {

            return mDrawable;
      }

      /**
       * 设置{@link ProgressDrawable}
       */
      public void setDrawable ( ProgressDrawable drawable ) {

            mDrawable = drawable;
      }

      @Override
      public int getIntrinsicWidth ( ) {

            return mDrawable.getIntrinsicWidth();
      }

      @Override
      public int getIntrinsicHeight ( ) {

            return mDrawable.getIntrinsicHeight();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mDrawable.onBoundsChange( bounds );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            if( mStartTime == -1 ) {
                  mDrawable.draw( canvas );
                  return;
            }

            mDrawable.mProgress = calculateProgress();
            mDrawable.draw( canvas );
            invalidateSelf();
      }

      private float calculateProgress ( ) {

            long l = ( System.currentTimeMillis() );
            if( ( l - mStartTime ) / mDuration >= mCount ) {
                  mStartTime = -1;
                  return 1;
            }
            long l1 = ( l - mStartTime ) % mDuration;
            float input = l1 * 1f / mDuration + mStartProgress;
            if( input > 1 ) {
                  input -= 1;
            }
            return mInterpolator.getInterpolation( input );
      }

      /**
       * 设置播放次数
       */
      public void setCount ( int count ) {

            mCount = count;
      }

      /**
       * 获取设置的播放次数
       */
      public int getCount ( ) {

            return mCount;
      }

      /**
       * 设置播放时长
       */
      public void setDuration ( int duration ) {

            mDuration = duration;
      }

      /**
       * 获取设置的播放时长
       */
      public int getDuration ( ) {

            return mDuration;
      }

      /**
       * 开始
       */
      @Override
      public void start ( ) {

            mStartTime = System.currentTimeMillis();
            mStartProgress = mDrawable.mProgress;
            invalidateSelf();
      }

      /**
       * 结束
       */
      @Override
      public void stop ( ) {

            mStartTime = -1;
      }

      /**
       * 是否正在运行
       */
      @Override
      public boolean isRunning ( ) {

            long l = ( System.currentTimeMillis() );
            return ( l - mStartTime ) / mDuration <= mCount;
      }

      /**
       * 设置差值器
       */
      public void setInterpolator ( TimeInterpolator interpolator ) {

            mInterpolator = interpolator;
      }

      /**
       * 获取设置的差值器
       */
      public TimeInterpolator getInterpolator ( ) {

            return mInterpolator;
      }
}
