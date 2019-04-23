package tech.liujin.drawable.progress;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author Liujin 2018-11-23:12:20
 */
public class ArcChangeRotateDrawable extends ProgressDrawable {

      private RectF            mRectF;
      private TimeInterpolator mInterpolator = new AccelerateDecelerateInterpolator();

      private float mStart;
      private float mStop;
      private float mLastProgress;

      public ArcChangeRotateDrawable ( ) {

            mPaint.setColor( Color.RED );
            mPaint.setStyle( Style.STROKE );
            mRectF = new RectF();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            int size = Math.min( bounds.width(), bounds.height() );
            int width = size >> 4;
            mPaint.setStrokeWidth( width );
            size = size - width * 2;
            int offset = size / 2;
            mRectF.set( -offset, -offset, offset, offset );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            int dx = getWidth() / 2;
            int dy = getHeight() / 2;
            canvas.translate( dx, dy );

            float sweep = 256;
            float changeAngle = 128;

            if( progress <= 0.5f ) {

                  progress *= 2;
                  /* 每转一圈起点增加240° */
                  float angel = Math.abs( progress - mLastProgress ) * changeAngle;
                  mLastProgress = progress;
                  progress = mInterpolator.getInterpolation( progress );
                  /* 弧度角度最大240° */
                  sweep = sweep * progress;
                  mStart += angel;
                  mStop = mStart + sweep;
            } else {
                  progress = 1 - ( progress - 0.5f ) * 2;
                  float angel = Math.abs( progress - mLastProgress ) * changeAngle;
                  mLastProgress = progress;
                  progress = mInterpolator.getInterpolation( progress );
                  sweep = sweep * progress;
                  mStop += angel;
                  mStart = mStop - sweep;
            }

            canvas.drawArc( mRectF, mStart, mStop - mStart, false, mPaint );
      }
}
