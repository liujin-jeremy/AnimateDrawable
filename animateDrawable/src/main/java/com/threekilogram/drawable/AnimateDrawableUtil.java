package com.threekilogram.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.threekilogram.drawable.widget.ProgressDrawableView;

/**
 * 辅助{@link BaseProgressDrawable}执行动画效果
 *
 * @author Liujin 2018-10-16:13:29
 */
public class AnimateDrawableUtil {

      private BaseProgressDrawable        mDrawable;
      /**
       * 时长
       */
      private int                         mDuration     = 2048;
      /**
       * 播放总数
       */
      private int                         mCount        = 1;
      /**
       * 开始时间,用于计算是否已经完成
       */
      private long                        mStartTime;
      /**
       * 记录是否正在运行
       */
      private boolean                     isRunning;
      /**
       * 强制停止
       */
      private boolean                     isForceStop   = true;
      /**
       * 差值器
       */
      private TimeInterpolator            mInterpolator = new LinearInterpolator();
      /**
       * 使用该类进行刷新界面
       */
      private OnRequestInvalidateListener mInvalidateListener;

      public AnimateDrawableUtil ( AnimateWrapperDrawable wrapperDrawable ) {

            mDrawable = wrapperDrawable.getDrawable();
            mInvalidateListener = new OnDrawableRequestInvalidateListener( wrapperDrawable );
      }

      public AnimateDrawableUtil ( ProgressDrawableView view ) {

            mDrawable = view.getDrawable();
            mInvalidateListener = new OnViewRequestInvalidateListener( view );
      }

      public AnimateDrawableUtil (
          BaseProgressDrawable drawable,
          OnRequestInvalidateListener invalidateListener ) {

            mDrawable = drawable;
            mInvalidateListener = invalidateListener;
      }

      /**
       * 绘制{@link #mDrawable},如果返回值为true,那么继续绘制
       *
       * @param canvas canvas
       */
      public void onDraw ( Canvas canvas ) {

            mDrawable.draw( canvas );

            if( isForceStop ) {
                  isRunning = false;
            }

            long current = System.currentTimeMillis();
            long diff = current - mStartTime;
            if( diff / mDuration < mCount ) {

                  isRunning = true;
                  float progress = diff * 1f / mDuration;
                  mDrawable.mProgress = mInterpolator.getInterpolation( progress );
                  mInvalidateListener.onRequestInvalidate();
            } else {

                  if( mDrawable.mProgress < 1 ) {

                        isRunning = true;
                        mDrawable.mProgress = 1;
                        mInvalidateListener.onRequestInvalidate();
                  } else {
                        isRunning = false;
                  }
            }
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

            return mDrawable.mProgress;
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
            mInvalidateListener.onRequestInvalidate();
      }

      public void stop ( ) {

            isForceStop = true;
      }

      public void resume ( ) {

            isForceStop = false;
            mInvalidateListener.onRequestInvalidate();
      }

      /**
       * 辅助进行请求重绘操作
       */
      public interface OnRequestInvalidateListener {

            /**
             * 请求重绘
             */
            void onRequestInvalidate ( );
      }

      /**
       * {@link OnRequestInvalidateListener}的{@link Drawable}实现类
       */
      public static class OnDrawableRequestInvalidateListener implements
                                                              OnRequestInvalidateListener {

            private Drawable mDrawable;

            public OnDrawableRequestInvalidateListener ( Drawable drawable ) {

                  mDrawable = drawable;
            }

            @Override
            public void onRequestInvalidate ( ) {

                  mDrawable.invalidateSelf();
            }
      }

      /**
       * {@link OnRequestInvalidateListener}的{@link View}实现类
       */
      public static class OnViewRequestInvalidateListener implements
                                                          OnRequestInvalidateListener {

            private View mView;

            public OnViewRequestInvalidateListener ( View view ) {

                  mView = view;
            }

            @Override
            public void onRequestInvalidate ( ) {

                  mView.invalidate();
            }
      }
}
