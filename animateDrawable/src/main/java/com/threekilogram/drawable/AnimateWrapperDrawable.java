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

      private BaseProgressDrawable mProgressDrawable;

      /**
       * 时长
       */
      private int              mDuration     = 2048;
      /**
       * 播放总数
       */
      private int              mCount        = 1;
      /**
       * 开始时间,用于计算是否已经完成
       */
      private long             mStartTime;
      /**
       * 记录是否正在运行
       */
      private boolean          isRunning;
      /**
       * 强制停止
       */
      private boolean          isForceStop   = true;
      /**
       * 差值器
       */
      private TimeInterpolator mInterpolator = new LinearInterpolator();

      /**
       * 包装一个{@link BaseProgressDrawable}使其具有动画能力,一帧播放完成之后才播放下一帧
       */
      public AnimateWrapperDrawable ( BaseProgressDrawable progressDrawable ) {

            mProgressDrawable = progressDrawable;
      }

      @Override
      public int getIntrinsicWidth ( ) {

            return mProgressDrawable.getIntrinsicWidth();
      }

      @Override
      public int getIntrinsicHeight ( ) {

            return mProgressDrawable.getIntrinsicHeight();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            mProgressDrawable.draw( canvas );

            if( isForceStop ) {
                  isRunning = false;
                  return;
            }

            long current = System.currentTimeMillis();
            long diff = current - mStartTime;
            if( diff / mDuration < mCount ) {

                  isRunning = true;
                  float progress = diff * 1f / mDuration;
                  mProgressDrawable.mProgress = mInterpolator.getInterpolation( progress );
                  invalidateSelf();
            } else {

                  if( mProgressDrawable.mProgress < 1 ) {

                        isRunning = true;
                        mProgressDrawable.mProgress = 1;
                        invalidateSelf();
                  } else {
                        isRunning = false;
                  }
            }
      }

      @Override
      public void setAlpha ( int alpha ) {

            mProgressDrawable.setAlpha( alpha );
      }

      @Override
      public void setColorFilter (
          @Nullable ColorFilter colorFilter ) {

            mProgressDrawable.setColorFilter( colorFilter );
      }

      @Override
      public int getOpacity ( ) {

            return mProgressDrawable.getOpacity();
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

            return isRunning;
      }

      public float getProgress ( ) {

            return mProgressDrawable.mProgress;
      }

      public int getFinishedCount ( ) {

            long current = System.currentTimeMillis();
            long diff = current - mStartTime;

            int result = (int) ( diff / mDuration );

            if( result > mCount ) {
                  return mCount;
            }

            return result;
      }

      public void setInterpolator ( TimeInterpolator interpolator ) {

            mInterpolator = interpolator;
      }

      public TimeInterpolator getInterpolator ( ) {

            return mInterpolator;
      }

      public void start ( ) {

            mStartTime = System.currentTimeMillis();
            if( isForceStop ) {
                  isForceStop = false;
            }
            isRunning = true;
            invalidateSelf();
      }

      public void stop ( ) {

            isForceStop = true;
      }

      public void resume ( ) {

            isForceStop = false;
            invalidateSelf();
      }
}
