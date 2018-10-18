package com.threekilogram.drawable.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.threekilogram.drawable.BaseProgressDrawable;

/**
 * 该view全部示例共享一个动画drawable
 *
 * @author Liujin 2018-10-16:13:14
 */
public class StaticAnimateDrawableView extends View {

      private static final int DEFAULT_SIZE = 200;

      private static final String TAG = StaticAnimateDrawableView.class.getSimpleName();

      private static BaseProgressDrawable sDrawable;
      /**
       * 时长
       */
      private static int                  sDuration     = 3000;
      /**
       * 开始时间,用于计算是否已经完成
       */
      private static long                 sStartTime    = System.currentTimeMillis();
      private static long                 sSetTime      = System.currentTimeMillis();
      /**
       * 差值器
       */
      private static TimeInterpolator     sInterpolator = new LinearInterpolator();

      public StaticAnimateDrawableView ( Context context ) {

            this( context, null, 0 );
      }

      public StaticAnimateDrawableView (
          Context context, @Nullable AttributeSet attrs ) {

            this( context, attrs, 0 );
      }

      public StaticAnimateDrawableView (
          Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {

            super( context, attrs, defStyleAttr );
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

            if( sDrawable == null ) {
                  return;
            }

            sDrawable.draw( canvas );

            long current = System.currentTimeMillis();
            long diff = current - sStartTime;
            float progress = ( diff % sDuration ) * 1f / sDuration;
            sDrawable.setProgress( sInterpolator.getInterpolation( progress ) );
            invalidate();
      }

      public static void setDrawable ( BaseProgressDrawable drawable ) {

            sDrawable = drawable;
      }

      public static BaseProgressDrawable getDrawable ( ) {

            return sDrawable;
      }

      /**
       * 获取当前进度
       */
      public static float getProgress ( ) {

            return sDrawable.getProgress();
      }

      /**
       * 设置动画时长
       */
      public static void setDuration ( int duration ) {

            sDuration = duration;
      }

      /**
       * 获取设置的动画时长
       */
      public static int getDuration ( ) {

            return sDuration;
      }

      /**
       * 设置差值器
       */
      public static void setInterpolator ( TimeInterpolator interpolator ) {

            sInterpolator = interpolator;
      }

      /**
       * 获取设置的差值器
       */
      public static TimeInterpolator getInterpolator ( ) {

            return sInterpolator;
      }
}
