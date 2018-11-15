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
import java.util.ArrayList;

/**
 * 该view全部示例共享一个动画drawable
 *
 * @author Liujin 2018-10-16:13:14
 */
public class StaticAnimateDrawableView extends View {

      private static final int DEFAULT_SIZE = 200;

      private static ProgressDrawable sDrawable;
      /**
       * start time
       */
      private static long             sStartTime    = -1;
      private static long             sSetTime;
      /**
       * start progress
       */
      private static float            sStartProgress;
      /**
       * 时长
       */
      private static int              sDuration     = 2000;
      /**
       * 播放总数
       */
      private static int              sCount        = 1;
      /**
       * 差值器
       */
      private static TimeInterpolator sInterpolator = new LinearInterpolator();

      private static ArrayList<StaticAnimateDrawableView> sViews = new ArrayList<>();

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
      protected void onAttachedToWindow ( ) {

            super.onAttachedToWindow();
            if( sViews.contains( this ) ) {
                  return;
            }
            sViews.add( this );
      }

      @Override
      protected void onDetachedFromWindow ( ) {

            super.onDetachedFromWindow();
            sViews.remove( this );
      }

      @Override
      protected void onDraw ( Canvas canvas ) {

            if( sDrawable == null ) {
                  return;
            }

            if( sStartTime == -1 ) {
                  sDrawable.draw( canvas );
                  return;
            }

            sDrawable.setProgress( calculateProgress() );
            sDrawable.draw( canvas );
            invalidate();
      }

      private static float calculateProgress ( ) {

            long l = ( System.currentTimeMillis() );
            if( l - sSetTime < 16 ) {
                  return sDrawable.getProgress();
            }
            if( ( l - sStartTime ) / sDuration >= sCount ) {
                  sStartTime = -1;
                  return 1;
            }
            long l1 = ( l - sStartTime ) % sDuration;
            float input = l1 * 1f / sDuration + sStartProgress;
            if( input > 1 ) {
                  input -= 1;
            }
            sSetTime = l;
            return sInterpolator.getInterpolation( input );
      }

      public static void setDrawable ( ProgressDrawable drawable ) {

            sDrawable = drawable;
      }

      public static ProgressDrawable getDrawable ( ) {

            return sDrawable;
      }

      /**
       * 设置进度
       *
       * @param progress 进度
       */
      public static void setProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            sDrawable.setProgress( progress );
      }

      /**
       * 获取当前进度
       */
      public static float getProgress ( ) {

            return sDrawable.getProgress();
      }

      /**
       * 设置进度值,同时重绘
       *
       * @param progress 进度
       */
      public static void setDrawProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            sDrawable.setProgress( progress );
            for( StaticAnimateDrawableView next : sViews ) {
                  next.invalidate();
            }
      }

      public static void setCount ( int count ) {

            sCount = count;
      }

      public static int getCount ( ) {

            return sCount;
      }

      public static void setDuration ( int duration ) {

            sDuration = duration;
      }

      public static int getDuration ( ) {

            return sDuration;
      }

      public static void setInterpolator ( TimeInterpolator interpolator ) {

            sInterpolator = interpolator;
      }

      public static TimeInterpolator getInterpolator ( ) {

            return sInterpolator;
      }

      public static void start ( ) {

            sStartTime = System.currentTimeMillis();
            sStartProgress = sDrawable.getProgress();

            for( StaticAnimateDrawableView next : sViews ) {
                  next.invalidate();
            }
      }

      public static void stop ( ) {

            sStartTime = -1;
      }
}
