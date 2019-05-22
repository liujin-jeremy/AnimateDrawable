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
 * @author Liujin 2019/5/20:22:59:12
 */
public class HourglassDrawable extends ProgressDrawable {

      private RectF mClipRect             = new RectF();
      private RectF mClipTopRect          = new RectF();
      private RectF mClipBottomRect       = new RectF();
      private RectF mClipTempRect         = new RectF();
      private RectF mTopRoundRect         = new RectF();
      private RectF mTopInnerRoundRect    = new RectF();
      private RectF mBottomRoundRect      = new RectF();
      private RectF mBottomInnerRoundRect = new RectF();
      private float mR;
      private float mHalfSize;
      private float mLineSize;
      private int   mHourglassColor       = Color.GRAY;
      private int   mSandColor            = Color.parseColor( "#FFA500" );

      private AnimateProgressEvaluator mAnimateProgressEvaluator = new AnimateProgressEvaluator();

      public HourglassDrawable ( ) {

            mAnimateProgressEvaluator.setDuration( 400 );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            float size = Math.min( width, height ) * 3f / 4;

            int halfWidth = width >> 1;
            int halfHeight = height >> 1;
            mHalfSize = size / 2;

            float strokeWidth = size / 16;
            mPaint.setStrokeWidth( strokeWidth );
            mLineSize = mHalfSize * 1 / 2;

            mClipRect.set(
                -mHalfSize,
                -mHalfSize,
                mHalfSize,
                mHalfSize
            );

            mTopRoundRect.set(
                -mHalfSize * 0.8f / 2,
                -size,
                mHalfSize * 0.8f / 2,
                0
            );

            mTopInnerRoundRect.set(
                mTopRoundRect.left + strokeWidth,
                mTopRoundRect.top + strokeWidth,
                mTopRoundRect.right - strokeWidth,
                mTopRoundRect.bottom - strokeWidth
            );

            mBottomRoundRect.set(
                -mHalfSize * 0.8f / 2,
                0,
                mHalfSize * 0.8f / 2,
                size
            );

            mBottomInnerRoundRect.set(
                mBottomRoundRect.left + strokeWidth,
                mBottomRoundRect.top + strokeWidth,
                mBottomRoundRect.right - strokeWidth,
                mBottomRoundRect.bottom - strokeWidth
            );

            mR = mTopRoundRect.width() / 2 + 2;

            float clipHeight = mTopInnerRoundRect.height() / 2;

            mClipTopRect.set(
                -mHalfSize,
                -mTopInnerRoundRect.bottom - clipHeight - strokeWidth,
                mHalfSize,
                mTopInnerRoundRect.bottom
            );

            mClipBottomRect.set(
                -mHalfSize,
                mBottomInnerRoundRect.top,
                mHalfSize,
                mBottomInnerRoundRect.top + clipHeight - strokeWidth
            );
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

      public void shake ( ) {

            if( mAnimateProgressEvaluator.isStopped() ) {
                  mAnimateProgressEvaluator.start( 0 );
                  invalidateSelf();
            }
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            canvas.translate( getWidth() >> 1, getHeight() >> 1 );
            canvas.rotate( calculateRotate() );
            canvas.clipRect( mClipRect );

            mPaint.setColor( mHourglassColor );
            mPaint.setStyle( Style.STROKE );
            canvas.drawLine( -mLineSize, -mHalfSize, mLineSize, -mHalfSize, mPaint );
            canvas.drawRoundRect( mTopRoundRect, mR, mR, mPaint );
            canvas.drawLine( -mLineSize, mHalfSize, mLineSize, mHalfSize, mPaint );
            canvas.drawRoundRect( mBottomRoundRect, mR, mR, mPaint );

            mPaint.setColor( mSandColor );
            mPaint.setStyle( Style.FILL );

            int save = canvas.save();
            mClipTempRect.set( mClipTopRect );
            mClipTempRect.top = mClipTempRect.bottom - mClipTopRect.height() * ( 1 - mProgress );
            canvas.clipRect( mClipTempRect );
            canvas.drawRoundRect( mTopInnerRoundRect, mR, mR, mPaint );
            canvas.restoreToCount( save );

            mClipTempRect.set( mClipBottomRect );
            mClipTempRect.bottom = mClipTempRect.top + mClipBottomRect.height() * ( mProgress );
            canvas.clipRect( mClipTempRect );
            canvas.drawRoundRect( mBottomInnerRoundRect, mR, mR, mPaint );

            if( mAnimateProgressEvaluator.isRunning() ) {
                  invalidateSelf();
            }
      }

      private float calculateRotate ( ) {

            float progress = mAnimateProgressEvaluator.calculateProgress() * 4;
            if( progress < 1 ) {
                  return -15 * progress;
            }
            if( progress < 2 ) {
                  return -15 + 15 * ( progress - 1 );
            }

            if( progress < 3 ) {
                  return 15 * ( progress - 2 );
            }

            return ( 1 - ( progress - 3 ) ) * 15;
      }
}
