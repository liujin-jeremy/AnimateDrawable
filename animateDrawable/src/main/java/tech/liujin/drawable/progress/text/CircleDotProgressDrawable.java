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
public class CircleDotProgressDrawable extends TextCenterProgressDrawable {

      private float mDotRadius;
      private float mArcWidth;
      private RectF mRectF;

      private int mDotColor = Color.parseColor( "#174A4A" );
      private int mArcColor = Color.parseColor( "#4DFDFF" );

      public CircleDotProgressDrawable ( ) {

            mRectF = new RectF();
            mTextPaint.setColor( mArcColor );
            mTextPaint.setTextAlign( Align.CENTER );
            mPaint.setStrokeCap( Cap.SQUARE );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {


            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );

            mTextPaint.setTextSize( size * 1f / 6 );

            mDotRadius = size * 3f / 8;
            mArcWidth = size * 1f / 8;

            float arcRadius = size * 1f / 2 - mArcWidth / 2 - 2;
            float rx = width * 1f / 2;
            float ry = height * 1f / 2;
            mRectF.set(
                rx - arcRadius,
                ry - arcRadius,
                rx + arcRadius,
                ry + arcRadius
            );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( mDotColor );
            canvas.drawCircle( width >> 1, height >> 1, mDotRadius, mPaint );

            mPaint.setStyle( Style.STROKE );
            mPaint.setStrokeWidth( mArcWidth );
            mPaint.setColor( mArcColor );
            canvas.drawArc( mRectF, 90, 360 * mProgress, false, mPaint );

            super.draw( canvas );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      public void setArcColor ( @ColorInt int arcColor ) {

            mArcColor = arcColor;
      }

      public void setDotColor ( @ColorInt int dotColor ) {

            mDotColor = dotColor;
      }
}
