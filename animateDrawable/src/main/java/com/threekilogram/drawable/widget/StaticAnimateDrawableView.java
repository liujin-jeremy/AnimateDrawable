package com.threekilogram.drawable.widget;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.threekilogram.drawable.AnimateDrawableUtil;
import com.threekilogram.drawable.AnimateDrawableUtil.OnRequestInvalidateListener;
import com.threekilogram.drawable.BaseProgressDrawable;
import java.util.ArrayList;
import java.util.List;

/**
 * 该view全部示例共享一个动画drawable
 *
 * @author Liujin 2018-10-16:13:14
 */
public class StaticAnimateDrawableView extends View {

      private static final int DEFAULT_SIZE = 200;

      private static BaseProgressDrawable sDrawable;

      private static AnimateDrawableUtil                 sUtil;
      private static OnListViewRequestInvalidateListener sInvalidateListener;

      static {

            sUtil = new AnimateDrawableUtil();
            sInvalidateListener = new OnListViewRequestInvalidateListener( sUtil );
            sUtil.setOnRequestInvalidateListener( sInvalidateListener );
            sUtil.setCount( Integer.MAX_VALUE );
            sUtil.setDuration( 4000 );
      }

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

            sUtil.onDraw( canvas );
      }

      @Override
      protected void onAttachedToWindow ( ) {

            super.onAttachedToWindow();
            if( !sInvalidateListener.containsOf( this ) ) {
                  sInvalidateListener.addView( this );
            }
      }

      @Override
      protected void onDetachedFromWindow ( ) {

            super.onDetachedFromWindow();
            sInvalidateListener.removeView( this );
      }

      @Override
      public void setVisibility ( int visibility ) {

            if( visibility == VISIBLE ) {
                  if( !sInvalidateListener.containsOf( this ) ) {
                        sInvalidateListener.addView( this );
                  }
            } else {
                  sInvalidateListener.removeView( this );
            }
            super.setVisibility( visibility );
      }

      public static void setDrawable ( BaseProgressDrawable drawable ) {

            sDrawable = drawable;
            sUtil.setDrawable( drawable );
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
       * 设置执行动画次数
       *
       * @param count 次数
       */
      public static void setCount ( int count ) {

            sUtil.setCount( count );
      }

      /**
       * 获取设置的执行动画次数
       */
      public static int getCount ( ) {

            return sUtil.getCount();
      }

      /**
       * 设置动画时长
       */
      public static void setDuration ( int duration ) {

            sUtil.setDuration( duration );
      }

      /**
       * 获取设置的动画时长
       */
      public static int getDuration ( ) {

            return sUtil.getDuration();
      }

      /**
       * 测试是否正在进行动画
       *
       * @return true 正在进行动画
       */
      public static boolean isRunning ( ) {

            return sUtil.isRunning();
      }

      /**
       * 获取已经进行的次数
       */
      public static int getFinishedCount ( ) {

            return sUtil.getFinishedCount();
      }

      /**
       * 设置差值器
       */
      public static void setInterpolator ( TimeInterpolator interpolator ) {

            sUtil.setInterpolator( interpolator );
      }

      /**
       * 获取设置的差值器
       */
      public static TimeInterpolator getInterpolator ( ) {

            return sUtil.getInterpolator();
      }

      /**
       * 开始动画
       */
      public static void start ( ) {

            sUtil.start();
      }

      /**
       * 结束动画
       */
      public static void stop ( ) {

            sUtil.stop();
      }

      /**
       * {@link OnRequestInvalidateListener}的{@link View}实现类
       */
      public static class OnListViewRequestInvalidateListener implements
                                                              OnRequestInvalidateListener {

            private List<View>          mViewList = new ArrayList<>();
            private AnimateDrawableUtil mUtil;

            public OnListViewRequestInvalidateListener ( AnimateDrawableUtil util ) {

                  mUtil = util;
            }

            public void addView ( View view ) {

                  mViewList.add( view );
                  if( !mUtil.isRunning() ) {
                        mUtil.start();
                  }
            }

            public void removeView ( View view ) {

                  mViewList.remove( view );
                  if( mViewList.size() == 0 ) {
                        mUtil.stop();
                  }
            }

            public void clearView ( ) {

                  mViewList.clear();
                  mUtil.stop();
            }

            public boolean containsOf ( View view ) {

                  return mViewList.contains( view );
            }

            @Override
            public void onRequestInvalidate ( ) {

                  for( View view : mViewList ) {
                        view.invalidate();
                  }
            }
      }
}
