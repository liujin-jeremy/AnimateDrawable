package com.example.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author wuxio 2018-05-25:6:59
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseDrawable extends Drawable {

      private static final String TAG = BaseDrawable.class.getSimpleName();

      protected Paint mPaint;

      public BaseDrawable () {

            Log.e(TAG, "BaseDrawable : ");
            init();
      }

      @CallSuper
      protected void init () {

            Log.e(TAG, "init : ");

            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
      }

      /**
       * draw there
       *
       * @param canvas canvas
       */
      @Override
      public abstract void draw (@NonNull Canvas canvas);

      public void setColor (@ColorInt int color) {

            mPaint.setColor(color);
      }

      @Override
      public void setAlpha (int alpha) {

            mPaint.setAlpha(alpha);
      }

      @Override
      public void setColorFilter (@Nullable ColorFilter colorFilter) {

            mPaint.setColorFilter(colorFilter);
      }

      @Override
      public int getOpacity () {

            return PixelFormat.TRANSPARENT;
      }
}
