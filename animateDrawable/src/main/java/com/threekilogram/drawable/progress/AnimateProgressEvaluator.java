package com.threekilogram.drawable.progress;

import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * @author Liujin 2018-11-26:12:02
 */
public class AnimateProgressEvaluator {

      /**
       * start time
       */
      private long             mStartTime    = -1;
      /**
       * start progress
       */
      private float            mStartProgress;
      /**
       * 时长
       */
      private int              mDuration     = 1000;
      /**
       * 播放总数
       */
      private int              mCount        = 1;
      /**
       * 差值器
       */
      private TimeInterpolator mInterpolator = new LinearInterpolator();

      public float calculateProgress ( ) {

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
      public void start ( float startProgress ) {

            mStartTime = System.currentTimeMillis();
            mStartProgress = startProgress;
      }

      /**
       * 结束
       */
      public void stop ( ) {

            mStartTime = -1;
      }

      /**
       * 是否正在运行
       */
      public boolean isRunning ( ) {

            long l = ( System.currentTimeMillis() );
            return ( l - mStartTime ) / mDuration <= mCount;
      }

      public boolean isStopped ( ) {

            return mStartTime == -1;
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
