package tech.liujin.drawable.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author wuxio 2018-05-25:6:58
 */
public class ProgressAlphaDrawable extends ProgressDrawable {

      private Bitmap mNormalBitmap;
      private Bitmap mSelectedBitmap;
      private float  mProgress;
      private Paint  mSelectPaint;

      public ProgressAlphaDrawable ( Bitmap normalBitmap, Bitmap selectedBitmap ) {

            mNormalBitmap = normalBitmap;
            mSelectedBitmap = selectedBitmap;

            mSelectPaint = new Paint( Paint.ANTI_ALIAS_FLAG );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int normalAlpha = (int) ( 255 * ( 1 - mProgress ) );
            mPaint.setAlpha( normalAlpha );
            canvas.drawBitmap( mNormalBitmap, 0, 0, mPaint );

            int selectedAlpha = 255 - normalAlpha;
            mSelectPaint.setAlpha( selectedAlpha );
            canvas.drawBitmap( mSelectedBitmap, 0, 0, mSelectPaint );
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
