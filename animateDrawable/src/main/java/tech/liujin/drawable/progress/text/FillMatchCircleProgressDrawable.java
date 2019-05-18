package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:16:24:35
 */
public class FillMatchCircleProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mRadius;
      private int   mCircleColor = Color.parseColor( "#6A1B17" );
      private int   mFillColor   = Color.RED;
      private float mStart;
      private float mEnd;

      public FillMatchCircleProgressDrawable ( ) {

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

            float rx = width * 1f / 2;
            float ry = height * 1f / 2;
            mRadius = size * 1f / 2 - 2;

            mRectF.set(
                rx - mRadius,
                ry - mRadius,
                rx + mRadius,
                ry + mRadius
            );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            Rect bounds = getBounds();
            mPaint.setColor( mCircleColor );
            canvas.drawCircle( bounds.width() >> 1, bounds.height() >> 1, mRadius, mPaint );

            mPaint.setColor( mFillColor );
            canvas.drawArc( mRectF, mStart, mEnd - mStart, false, mPaint );

            super.draw( canvas );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            float v = 180 * progress;
            mStart = 90 - v;
            mEnd = 90 + v;
            invalidateSelf();
      }
}
