package com.threekilogram.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-25:6:58
 */
public class AlphaProgressDrawable extends BaseProgressDrawable {

      private Bitmap mNormalBitmap;
      private Bitmap mSelectedBitmap;
      private float  mProgress;
      private Paint  mSelectPaint;

      public AlphaProgressDrawable ( Bitmap normalBitmap, Bitmap selectedBitmap ) {

            mNormalBitmap = normalBitmap;
            mSelectedBitmap = selectedBitmap;

            mSelectPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int normalAlpha = (int) ( 255 * ( 1 - mProgress ) );
            int selectedAlpha = 255 - normalAlpha;

            mPaint.setAlpha( normalAlpha );
            canvas.drawBitmap( mNormalBitmap, 0, 0, mPaint );
            mSelectPaint.setAlpha( selectedAlpha );
            canvas.drawBitmap( mSelectedBitmap, 0, 0, mSelectPaint );
      }

      @Override
      public void setProgress ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      @Override
      public int getIntrinsicWidth ( ) {

            return mNormalBitmap.getWidth();
      }

      @Override
      public int getIntrinsicHeight ( ) {

            return mNormalBitmap.getHeight();
      }

      public void setSelectColorFilter (
          @Nullable ColorFilter colorFilter ) {

            mSelectPaint.setColorFilter( colorFilter );
      }

      public void setNormalColorFilter (
          @Nullable ColorFilter colorFilter ) {

            mPaint.setColorFilter( colorFilter );
      }

      public Paint getSelectPaint ( ) {

            return mSelectPaint;
      }
}
