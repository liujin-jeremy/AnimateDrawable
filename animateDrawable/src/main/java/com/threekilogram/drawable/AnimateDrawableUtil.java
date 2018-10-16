package com.threekilogram.drawable;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.threekilogram.drawable.widget.AnimateDrawableView;

/**
 * 辅助{@link BaseProgressDrawable}执行动画效果,一帧播放完成之后才播放下一帧
 *
 * @author Liujin 2018-10-16:13:29
 */
public class AnimateDrawableUtil {

      /**
       * 真正进行绘制
       */
      private BaseProgressDrawable        mDrawable;
      /**
       * 时长
       */
      private int                         mDuration     = 2000;
      /**
       * 播放总数
       */
      private int                         mCount        = 1;
      /**
       * 开始时间,用于计算是否已经完成
       */
      private long                        mStartTime    = System.currentTimeMillis();
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

      public AnimateDrawableUtil ( AnimateDrawableView view ) {

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
                  return;
            }

            long current = System.currentTimeMillis();
            long diff = current - mStartTime;
            if( diff / mDuration < mCount ) {

                  isRunning = true;
                  float progress = ( diff % mDuration ) * 1f / mDuration;
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

      /**
       * 设置执行动画次数
       *
       * @param count 次数
       */
      public void setCount ( int count ) {

            mCount = count;
      }

      /**
       * 获取设置的执行动画次数
       */
      public int getCount ( ) {

            return mCount;
      }

      /**
       * 设置动画时长
       */
      public void setDuration ( int duration ) {

            mDuration = duration;
      }

      /**
       * 获取设置的动画时长
       */
      public int getDuration ( ) {

            return mDuration;
      }

      /**
       * 测试是否正在进行动画
       *
       * @return true 正在进行动画
       */
      public boolean isRunning ( ) {

            return isRunning;
      }

      /**
       * 当前进度
       */
      public float getProgress ( ) {

            return mDrawable.mProgress;
      }

      /**
       * 获取已经进行的次数
       */
      public int getFinishedCount ( ) {

            long current = System.currentTimeMillis();
            long diff = current - mStartTime;

            int result = (int) ( diff / mDuration );

            if( result > mCount ) {
                  return mCount;
            }

            return result;
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

      /**
       * 开始动画
       */
      public void start ( ) {

            mStartTime = System.currentTimeMillis();
            if( isForceStop ) {
                  isForceStop = false;
            }
            isRunning = true;
            mInvalidateListener.onRequestInvalidate();
      }

      /**
       * 结束动画
       */
      public void stop ( ) {

            isForceStop = true;
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
