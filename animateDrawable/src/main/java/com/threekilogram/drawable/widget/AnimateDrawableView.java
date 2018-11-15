package com.threekilogram.drawable.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.threekilogram.drawable.ProgressDrawable;

/**
 * @author Liujin 2018-10-16:13:14
 */
public class AnimateDrawableView extends View {

      private static final int DEFAULT_SIZE = 200;

      public  ProgressDrawable mDrawable;
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
      private int              mDuration     = 2000;
      /**
       * 播放总数
       */
      private int              mCount        = 1;
      /**
       * 差值器
       */
      private TimeInterpolator mInterpolator = new LinearInterpolator();

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

      public void setDrawable ( ProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
      }

      public ProgressDrawable getDrawable ( ) {

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

            if( mDrawable == null ) {
                  return;
            }

            if( mStartTime == -1 ) {
                  mDrawable.draw( canvas );
                  return;
            }

            mDrawable.setProgress( calculateProgress() );
            mDrawable.draw( canvas );
            invalidate();
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

      public void setInterpolator ( TimeInterpolator interpolator ) {

            mInterpolator = interpolator;
      }

      public TimeInterpolator getInterpolator ( ) {

            return mInterpolator;
      }

      public void start ( ) {

            mStartTime = System.currentTimeMillis();
            mStartProgress = mDrawable.getProgress();
            invalidate();
      }

      public void stop ( ) {

            mStartTime = -1;
      }
}
