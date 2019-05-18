package tech.liujin.drawable.progress.load;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-16:10:01
 */
public class CubeFlipDrawable extends ProgressDrawable {

      private int mSize;

      private Camera mCamera;
      private Matrix mMatrix;

      public CubeFlipDrawable ( ) {

            super();

            mPaint.setStyle( Style.FILL );
            mPaint.setColor( Color.RED );
            mCamera = new Camera();
            mMatrix = new Matrix();
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            mMatrix.reset();
            mCamera.save();
            if( progress <= 0.5f ) {
                  mCamera.rotateX( -180 * progress * 2 );
            } else {
                  mCamera.rotateY( 180 * progress * 2 );
            }
            mCamera.getMatrix( mMatrix );
            mCamera.restore();

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );
            canvas.concat( mMatrix );

            int i = mSize >> 1;
            canvas.drawRect( -i, -i, i, i, mPaint );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mSize = Math.min( bounds.width(), bounds.height() ) * 7 / 10;
      }
}
