package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-23:11:57
 */
public class ArcProgressDrawable extends ProgressDrawable {

      private RectF mRectF;
      private int   mSize;

      public ArcProgressDrawable ( ) {

            mPaint.setColor( Color.RED );
            mRectF = new RectF();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {


            mSize = Math.min( bounds.width(), bounds.height() );

            int width = mSize / 24;
            mPaint.setStrokeWidth( width );
            mSize -= width;

            int offset = (int) ( ( mSize >> 1 ) * 0.9f );
            mRectF.set( -offset, -offset, offset, offset );

            super.onBoundsChange( bounds );

      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );

            mPaint.setStyle( Style.STROKE );
            canvas.drawCircle( 0, 0, mSize >> 1, mPaint );

            mPaint.setStyle( Style.FILL );
            canvas.drawArc( mRectF, -90, 360 * mProgress, true, mPaint );
      }

      @Override
      public void onProcessChange ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }
}
