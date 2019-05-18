package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:15:16:30
 */
public class PieTextProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;

      public PieTextProgressDrawable ( ) {

            mRectF = new RectF();

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
            mTextPaint.setColor( Color.WHITE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {


            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            float radius = size * 1f / 2 - 2;

            mRectF.set(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius
            );
            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            Rect bounds = getBounds();
            int width = bounds.width();

            canvas.drawArc( mRectF, 90, 360 * mProgress, true, mPaint );

            super.draw( canvas );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      public void setColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }
}
