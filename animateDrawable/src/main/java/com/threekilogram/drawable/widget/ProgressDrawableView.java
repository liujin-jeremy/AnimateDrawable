package com.threekilogram.drawable.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.threekilogram.drawable.BaseProgressDrawable;

/**
 * @author Liujin 2018-10-16:13:14
 */
public class ProgressDrawableView extends View {

      private static final int DEFAULT_SIZE = 200;

      private BaseProgressDrawable mDrawable;

      public ProgressDrawableView ( Context context ) {

            this( context, null, 0 );
      }

      public ProgressDrawableView (
          Context context, @Nullable AttributeSet attrs ) {

            this( context, attrs, 0 );
      }

      public ProgressDrawableView (
          Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {

            super( context, attrs, defStyleAttr );
      }

      public void setDrawable ( BaseProgressDrawable progressDrawable ) {

            mDrawable = progressDrawable;
      }

      public BaseProgressDrawable getDrawable ( ) {

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

            mDrawable.draw( canvas );
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
}
