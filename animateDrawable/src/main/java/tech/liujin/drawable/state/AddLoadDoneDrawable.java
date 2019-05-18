package tech.liujin.drawable.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Rect;
import tech.liujin.drawable.animator.AnimateProgressEvaluator;
import tech.liujin.drawable.progress.ProgressDrawable;
import tech.liujin.drawable.progress.load.ArcChangeRotateDrawable;
/**
 * 该drawable拥有三个状态,add,load,done
 *
 * @author Liujin 2018-11-26:11:48
 */
public class AddLoadDoneDrawable extends StartLoadDoneDrawable {

      private ProgressDrawable         mDrawable;
      private AnimateProgressEvaluator mEvaluator;

      private int mSize;

      public AddLoadDoneDrawable ( ) {

            mEvaluator = new AnimateProgressEvaluator();
            mEvaluator.setCount( Integer.MAX_VALUE );
            mEvaluator.setDuration( 1000 );
            mEvaluator.start( 0 );
            mDrawable = new ArcChangeRotateDrawable();

            mPaint.setStrokeJoin( Join.ROUND );
            mPaint.setStrokeCap( Cap.ROUND );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mDrawable.setBounds( bounds );

            mSize = Math.min( bounds.width(), bounds.height() ) * 4 / 5;
            mPaint.setStrokeWidth( mSize >> 4 );
      }

      @Override
      protected void drawStart ( Canvas canvas ) {

            int dx = getWidth() >> 1;
            int dy = getHeight() >> 1;
            canvas.translate( dx, dy );

            int offset = mSize / 2;

            mPaint.setColor( Color.BLUE );
            canvas.drawLine( 0, -offset, 0, offset, mPaint );
            canvas.drawLine( -offset, 0, offset, 0, mPaint );
      }

      @Override
      protected void drawLoad ( Canvas canvas ) {

            float progress = mEvaluator.calculateProgress();
            mDrawable.setProgress( progress );
            mDrawable.draw( canvas );
            invalidateSelf();
      }

      @Override
      protected void drawDone ( Canvas canvas ) {

            int offset = mSize / 6;
            float dx = ( getWidth() >> 1 ) - offset;
            int dy = getHeight() * 4 / 5;
            canvas.translate( dx, dy );

            int left = ( mSize >> 1 ) - offset;
            int right = mSize - left;

            mPaint.setColor( Color.RED );
            canvas.drawLine( 0, 0, -left, -left, mPaint );
            canvas.drawLine( 0, 0, right, -right, mPaint );
      }
}
