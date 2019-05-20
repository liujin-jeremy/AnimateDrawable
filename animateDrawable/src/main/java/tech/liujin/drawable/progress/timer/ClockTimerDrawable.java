package tech.liujin.drawable.progress.timer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import tech.liujin.drawable.animator.AnimateProgressEvaluator;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2019/5/19:18:00:57
 */
public class ClockTimerDrawable extends ProgressDrawable {

      private float                    mRadius;
      private int                      mHatOffsetWidth;
      private int                      mStrokeWidth;
      private RectF                    mInnerCircle = new RectF();
      private AnimateProgressEvaluator mAnimateProgressEvaluator;

      public ClockTimerDrawable ( ) {

            mPaint.setColor( Color.parseColor( "#FFA500" ) );
            mAnimateProgressEvaluator = new AnimateProgressEvaluator();
            mAnimateProgressEvaluator.setDuration( 400 );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            int width = bounds.width();
            int height = bounds.height();

            int size = Math.min( width, height );

            mStrokeWidth = size >> 4;
            mPaint.setStrokeWidth( mStrokeWidth );
            mHatOffsetWidth = size >> 3;

            mRadius = size / 2f - mStrokeWidth * 3;
            float innerRadius = mRadius - mStrokeWidth * 1.2f;
            mInnerCircle.set(
                width / 2f - innerRadius,
                height / 2f - innerRadius,
                width / 2f + innerRadius,
                height / 2f + innerRadius
            );

            super.onBoundsChange( bounds );
      }

      @SuppressWarnings("SuspiciousNameCombination")
      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( 0, mStrokeWidth );

            mPaint.setStyle( Style.STROKE );
            int widthCenter = getWidth() >> 1;
            int heightCenter = getHeight() >> 1;
            float y = calculateHatY();
            canvas.drawLine(
                widthCenter - mHatOffsetWidth,
                y,
                widthCenter + mHatOffsetWidth,
                y,
                mPaint
            );
            canvas.drawCircle( widthCenter, heightCenter, mRadius, mPaint );

            mPaint.setStyle( Style.FILL );
            canvas.drawArc( mInnerCircle, -90, 360 * mProgress, true, mPaint );

            if( mAnimateProgressEvaluator.isRunning() ) {
                  invalidateSelf();
            }
      }

      @Override
      public void onProcessChange ( float progress ) {

            if( mProgress == 0 || mProgress == 1 ) {
                  if( mAnimateProgressEvaluator.isStopped() ) {
                        mAnimateProgressEvaluator.start( 0 );
                  }
            }
            mProgress = progress;
            invalidateSelf();
      }

      private float calculateHatY ( ) {

            float progress = mAnimateProgressEvaluator.calculateProgress() * 4;
            if( progress < 1 ) {
                  return mStrokeWidth - progress * mStrokeWidth;
            }
            if( progress < 2 ) {
                  return ( progress - 1 ) * mStrokeWidth;
            }

            if( progress < 3 ) {
                  return mStrokeWidth + ( progress - 2 ) * mStrokeWidth;
            }
            return 2 * mStrokeWidth - ( progress - 3 ) * mStrokeWidth;
      }
}
