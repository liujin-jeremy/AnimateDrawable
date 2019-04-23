package tech.liujin.drawable.progress;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;

/**
 * 包装一个{@link ProgressDrawable}使其具有动画效果,他会执行指定次数/指定时长的动画
 *
 * @author Liujin 2018-10-16:9:36
 */
public class AnimateProgressDrawable extends ProgressDrawable implements Animatable {

      /**
       * drawable
       */
      protected ProgressDrawable         mDrawable;
      protected AnimateProgressEvaluator mEvaluator;

      /**
       * 包装一个{@link ProgressDrawable}使其具有动画能力,一帧播放完成之后才播放下一帧
       */
      public AnimateProgressDrawable ( ProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
            mEvaluator = new AnimateProgressEvaluator();
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
      public void changeDrawable ( ProgressDrawable drawable ) {

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
            mDrawable.setBounds( bounds );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            if( mEvaluator.isStopped() ) {
                  mDrawable.draw( canvas );
                  return;
            }

            mDrawable.mProgress = calculateProgress();
            mDrawable.draw( canvas );
            invalidateSelf();
      }

      private float calculateProgress ( ) {

            return mEvaluator.calculateProgress();
      }

      /**
       * 设置播放次数
       */
      public void setCount ( int count ) {

            mEvaluator.setCount( count );
      }

      /**
       * 获取设置的播放次数
       */
      public int getCount ( ) {

            return mEvaluator.getCount();
      }

      /**
       * 设置播放时长
       */
      public void setDuration ( int duration ) {

            mEvaluator.setDuration( duration );
      }

      /**
       * 获取设置的播放时长
       */
      public int getDuration ( ) {

            return mEvaluator.getDuration();
      }

      /**
       * 开始
       */
      @Override
      public void start ( ) {

            mEvaluator.start( mDrawable.mProgress );
            invalidateSelf();
      }

      /**
       * 结束
       */
      @Override
      public void stop ( ) {

            mEvaluator.stop();
      }

      /**
       * 是否正在运行
       */
      @Override
      public boolean isRunning ( ) {

            return mEvaluator.isRunning();
      }

      /**
       * 设置差值器
       */
      public void setInterpolator ( TimeInterpolator interpolator ) {

            mEvaluator.setInterpolator( interpolator );
      }

      /**
       * 获取设置的差值器
       */
      public TimeInterpolator getInterpolator ( ) {

            return mEvaluator.getInterpolator();
      }
}
