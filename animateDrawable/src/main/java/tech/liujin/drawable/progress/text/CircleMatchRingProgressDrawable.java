package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:11:48:33
 */
public class CircleMatchRingProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF = new RectF();
      private float mRadius;

      private int mRingColor = Color.parseColor( "#174A4A" );
      private int mArcColor  = Color.parseColor( "#4DFDFF" );

      public CircleMatchRingProgressDrawable ( ) {

            mPaint.setStyle( Style.STROKE );
            mTextPaint.setColor( mArcColor );
            mTextPaint.setTextAlign( Align.CENTER );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {


            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );

            float strokeWidth = size * 1f / 12;
            mTextPaint.setTextSize( size * 1f / 6 );
            mPaint.setStrokeWidth( strokeWidth );

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            float radius = mRadius = size * 1f / 2 - strokeWidth / 2 - 2;
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
            int height = bounds.height();

            mPaint.setColor( mRingColor );
            canvas.drawCircle( width >> 1, height >> 1, mRadius, mPaint );

            mPaint.setColor( mArcColor );
            canvas.drawArc( mRectF, 90, 360 * mProgress, false, mPaint );

            super.draw( canvas );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      public void setColor ( @ColorInt int color ) {

            setTextColor( color );
            setCircleColor( color );
      }

      public void setCircleColor ( @ColorInt int color ) {

            mPaint.setColor( color );
      }
}
