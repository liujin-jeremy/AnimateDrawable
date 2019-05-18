package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:15:37:53
 */
public class PieMatchCircleProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mRadius;
      private int   mCircleColor = Color.parseColor( "#6A1B17" );
      private int   mPieColor    = Color.RED;

      public PieMatchCircleProgressDrawable ( ) {

            mRectF = new RectF();

            mPaint.setStyle( Style.FILL );
            mTextPaint.setColor( Color.WHITE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            mRadius = size * 1f / 2 - 2;

            mRectF.set(
                cx - mRadius,
                cy - mRadius,
                cx + mRadius,
                cy + mRadius
            );
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();

            mPaint.setColor( mCircleColor );
            canvas.drawCircle( width >> 1, height >> 1, mRadius, mPaint );

            mPaint.setColor( mPieColor );
            canvas.drawArc( mRectF, 90, 360 * progress, true, mPaint );

            super.draw( canvas, progress );
      }

      public void setCircleColor ( int circleColor ) {

            mCircleColor = circleColor;
      }

      public void setPieColor ( int pieColor ) {

            mPieColor = pieColor;
      }
}
