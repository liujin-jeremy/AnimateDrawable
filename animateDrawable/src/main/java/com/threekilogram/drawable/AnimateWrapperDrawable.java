package com.threekilogram.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

/**
 * @author Liujin 2018-10-16:9:36
 */
public class AnimateWrapperDrawable extends ProgressDrawable {

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

      public ProgressDrawable getDrawable ( ) {

            return mDrawable;
      }

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
            requestInvalidate();
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

      public void requestInvalidate ( ) {

            invalidateSelf();
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

      public void start ( ) {

            mStartTime = System.currentTimeMillis();
            mStartProgress = mDrawable.mProgress;
            invalidateSelf();
      }

      public void stop ( ) {

            mStartTime = -1;
      }

      public boolean isRunning ( ) {

            long l = ( System.currentTimeMillis() );
            return ( l - mStartTime ) / mDuration <= mCount;
      }

      public void setInterpolator ( TimeInterpolator interpolator ) {

            mInterpolator = interpolator;
      }

      public TimeInterpolator getInterpolator ( ) {

            return mInterpolator;
      }
}
