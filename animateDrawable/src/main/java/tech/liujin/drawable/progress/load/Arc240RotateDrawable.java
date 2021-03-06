package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-23:11:37
 */
public class Arc240RotateDrawable extends ProgressDrawable {

      private RectF mRectF;
      private float mDegree;

      public Arc240RotateDrawable ( ) {

            mPaint.setColor( Color.RED );
            mPaint.setStyle( Style.STROKE );
            mPaint.setStrokeCap( Cap.SQUARE );
            mRectF = new RectF();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int size = Math.min( bounds.width(), bounds.height() );
            int width = size >> 4;
            mPaint.setStrokeWidth( width );
            size = size - width * 2;
            int offset = size / 2;
            mRectF.set( -offset, -offset, offset, offset );

            super.onBoundsChange( bounds );
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            int dx = getWidth() / 2;
            int dy = getHeight() / 2;
            canvas.translate( dx, dy );

            canvas.rotate( mDegree );
            canvas.drawArc( mRectF, 0, 160, false, mPaint );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;

            mDegree = 360 * progress;

            invalidateSelf();
      }
}
