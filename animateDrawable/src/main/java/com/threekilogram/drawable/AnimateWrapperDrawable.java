package com.threekilogram.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;

/**
 * @author Liujin 2018-10-16:9:36
 */
public class AnimateWrapperDrawable extends Drawable {

      /**
       * drawable
       */
      private BaseProgressDrawable mDrawable;
      /**
       * start time
       */
      private long                 mStartTime    = -1;
      /**
       * start progress
       */
      private float                mStartProgress;
      /**
       * 时长
       */
      private int                  mDuration     = 2000;
      /**
       * 播放总数
       */
      private int                  mCount        = 1;
      /**
       * 差值器
       */
      private TimeInterpolator     mInterpolator = new LinearInterpolator();

      /**
       * 包装一个{@link BaseProgressDrawable}使其具有动画能力,一帧播放完成之后才播放下一帧
       */
      public AnimateWrapperDrawable ( BaseProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
      }

      public BaseProgressDrawable getDrawable ( ) {

            return mDrawable;
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
      public void draw ( @NonNull Canvas canvas ) {

            if( mDrawable == null ) {
                  return;
            }

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

      @Override
      public void setAlpha ( int alpha ) {

            mDrawable.setAlpha( alpha );
      }

      @Override
      public void setColorFilter (
          @Nullable ColorFilter colorFilter ) {

            mDrawable.setColorFilter( colorFilter );
      }

      @Override
      public int getOpacity ( ) {

            return mDrawable.getOpacity();
      }

      public void setCount ( int count ) {

            mCount = count;
      }

      public int getCount ( ) {

            return mCount;
      }

      public void setDuration ( int duration ) {

            mDuration = duration;
      }

      public int getDuration ( ) {

            return mDuration;
      }

      public boolean isRunning ( ) {

            return false;
      }

      public float getProgress ( ) {

            return mDrawable.mProgress;
      }

      public void setInterpolator ( TimeInterpolator interpolator ) {

            mInterpolator = interpolator;
      }

      public TimeInterpolator getInterpolator ( ) {

            return mInterpolator;
      }

      public void start ( ) {

            mStartTime = System.currentTimeMillis();
            mStartProgress = mDrawable.mProgress;
            invalidateSelf();
      }

      public void stop ( ) {

            mStartTime = -1;
      }
}
