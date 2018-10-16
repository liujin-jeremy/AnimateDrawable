package com.threekilogram.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Liujin 2018-10-16:9:36
 */
public class AnimateWrapperDrawable extends Drawable {

      private BaseProgressDrawable mDrawable;
      private AnimateDrawableUtil  mUtil;

      /**
       * 包装一个{@link BaseProgressDrawable}使其具有动画能力,一帧播放完成之后才播放下一帧
       */
      public AnimateWrapperDrawable ( BaseProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
            mUtil = new AnimateDrawableUtil( this );
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

            mUtil.onDraw( canvas );
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

            mUtil.setCount( count );
      }

      public int getCount ( ) {

            return mUtil.getCount();
      }

      public void setDuration ( int duration ) {

            mUtil.setDuration( duration );
      }

      public int getDuration ( ) {

            return mUtil.getDuration();
      }

      public boolean isRunning ( ) {

            return mUtil.isRunning();
      }

      public float getProgress ( ) {

            return mDrawable.mProgress;
      }

      public int getFinishedCount ( ) {

            return mUtil.getFinishedCount();
      }

      public void setInterpolator ( TimeInterpolator interpolator ) {

            mUtil.setInterpolator( interpolator );
      }

      public TimeInterpolator getInterpolator ( ) {

            return mUtil.getInterpolator();
      }

      public void start ( ) {

            mUtil.start();
      }

      public void stop ( ) {

            mUtil.stop();
      }
}
