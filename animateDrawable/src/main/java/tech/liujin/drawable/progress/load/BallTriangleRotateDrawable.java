package tech.liujin.drawable.progress.load;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:12:23
 */
public class BallTriangleRotateDrawable extends ProgressDrawable {

      private float mDis;
      private float mRadius;

      public BallTriangleRotateDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int size = Math.min( bounds.width(), bounds.height() );

            mDis = size / 6;
            mRadius = size / 7;
      }

      @Override
      protected void draw (
          @NonNull Canvas canvas, float progress ) {

            int cX = getWidth() >> 1;
            int cY = getHeight() >> 1;
            canvas.translate( cX, cY );
            canvas.rotate( 360 * progress );

            float dis;
            if( progress <= 0.5f ) {
                  progress *= 2;
                  dis = mDis + mDis * ( 1 - progress );
            } else {
                  progress = ( progress - 0.5f ) * 2;
                  dis = mDis + mDis * ( progress );
            }

            for( int i = 0; i < 3; i++ ) {
                  int save = canvas.save();
                  canvas.rotate( 120 * i );
                  canvas.drawCircle( 0, -dis, mRadius, mPaint );
                  canvas.restoreToCount( save );
            }
      }
}
