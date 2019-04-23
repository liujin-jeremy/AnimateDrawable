package tech.liujin.drawable.state;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import com.threekilogram.drawable.progress.AnimateProgressEvaluator;
import com.threekilogram.drawable.progress.ArcChangeRotateDrawable;
import com.threekilogram.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2018-11-27:10:14
 */
public class TextLoadDoneDrawable extends StartLoadDoneDrawable {

      private String mStartText;
      private String mDoneText;

      private ProgressDrawable         mDrawable;
      private AnimateProgressEvaluator mEvaluator;

      public TextLoadDoneDrawable ( ) {

            mEvaluator = new AnimateProgressEvaluator();
            mEvaluator.setCount( Integer.MAX_VALUE );
            mEvaluator.setDuration( 1000 );
            mEvaluator.start( 0 );
            mDrawable = new ArcChangeRotateDrawable();
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            mDrawable.setBounds( bounds );

            int size = Math.min( bounds.width(), bounds.height() ) / 2;
            mPaint.setTextSize( size );
      }

      @Override
      protected void drawStart ( Canvas canvas ) {

            //使用画笔测量宽度
            float measureText = mPaint.measureText( mStartText, 0, mStartText.length() );
            //view的宽度 减去 文字宽度 的 一半就是X轴偏移
            float dX = ( getWidth() - measureText ) / 2;
            FontMetrics fontMetrics = mPaint.getFontMetrics();
            float dY = ( getHeight() - fontMetrics.bottom + fontMetrics.top ) / 2 - fontMetrics.top;
            canvas.drawText( mStartText, dX, dY, mPaint );
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

            //使用画笔测量宽度
            float measureText = mPaint.measureText( mDoneText, 0, mDoneText.length() );
            //view的宽度 减去 文字宽度 的 一半就是X轴偏移
            float dX = ( getWidth() - measureText ) / 2;
            FontMetrics fontMetrics = mPaint.getFontMetrics();
            float dY = ( getHeight() - fontMetrics.bottom + fontMetrics.top ) / 2 - fontMetrics.top;
            canvas.drawText( mDoneText, dX, dY, mPaint );
      }

      public void setText ( String startText, String doneText ) {

            mStartText = startText;
            mDoneText = doneText;
      }

      public void setTextSize ( float textSize ) {

            mPaint.setTextSize( textSize );
      }
}
