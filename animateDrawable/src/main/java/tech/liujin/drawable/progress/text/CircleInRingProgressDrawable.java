package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/13:13:10:15
 */
public class CircleInRingProgressDrawable extends TextCenterProgressDrawable {

      private RectF mRectF;
      private float mRadius;
      private float mRingWidth;
      private float mArcWidth;

      private int mRingColor = Color.parseColor( "#174A4A" );
      private int mArcColor  = Color.parseColor( "#4DFDFF" );

      public CircleInRingProgressDrawable ( ) {

            mRectF = new RectF();
            mPaint.setStyle( Style.STROKE );
            mPaint.setStrokeCap( Cap.SQUARE );
            mTextPaint.setTextAlign( Align.CENTER );
            mTextPaint.setColor( mArcColor );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );
            mRingWidth = size * 1f / 8;
            mArcWidth = size * 1f / 16;
            mRadius = size * 1f / 2 - mRingWidth / 2 - 2;
            mTextPaint.setTextSize( size * 1f / 6 );
            mPaint.setStrokeWidth( mArcWidth );

            float cx = width * 1f / 2;
            float cy = height * 1f / 2;
            float radius = mRadius;
            mRectF.set(
                cx - radius,
                cy - radius,
                cx + radius,
                cy + radius
            );
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();

            mPaint.setColor( mRingColor );
            mPaint.setStrokeWidth( mRingWidth );
            canvas.drawCircle( width >> 1, height >> 1, mRadius, mPaint );

            mPaint.setColor( mArcColor );
            mPaint.setStrokeWidth( mArcWidth );
            canvas.drawArc( mRectF, 90, 360 * progress, false, mPaint );

            super.draw( canvas, progress );
      }

      public void setRingColor ( @ColorInt int ringColor ) {

            mRingColor = ringColor;
      }

      public void setArcColor ( @ColorInt int arcColor ) {

            mArcColor = arcColor;
      }
}
