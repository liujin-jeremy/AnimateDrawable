package tech.liujin.drawable.progress.text;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/17:11:55:36
 */
public class HaloTextProgressDrawable extends TextCenterProgressDrawable {

      /**
       * 光晕
       */
      private RadialGradient     mRadialGradient;
      /**
       * 挖空
       */
      private PorterDuffXfermode mXfermode    = new PorterDuffXfermode( Mode.DST_OUT );
      /**
       * 光晕半径
       */
      private int                mRadius;
      /**
       * 光晕动画进度
       */
      private float              mHaloProcess;
      /**
       * 动画速度
       */
      private float              mCellProcess = 0.016f;
      /**
       * 动画方向
       */
      private int                mOri         = 1;

      /**
       * 主颜色
       */
      private int mColor   = Color.WHITE;
      /**
       * %80 alpha 主颜色
       */
      private int mCCColor = Color.parseColor( "#CCFFFFFF" );
      /**
       * %40 alpha 主颜色
       */
      private int m66Color = Color.parseColor( "#66FFFFFF" );

      public HaloTextProgressDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mTextPaint.setColor( mColor );
      }

      @Override
      public void setColor ( int color ) {

            mColor = ( 0xFFFFFF ) & color;
            mCCColor = mColor | 0xCC000000;
            m66Color = mColor | 0x66000000;
            mTextPaint.setColor( color );
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );

            int width = bounds.width();
            int height = bounds.height();
            int size = Math.min( width, height );
            mRadius = ( size >> 1 ) - 2;
            mRadialGradient = new RadialGradient(
                width >> 1, height >> 1, mRadius,
                new int[]{ Color.TRANSPARENT,
                           mCCColor,
                           m66Color,
                           Color.TRANSPARENT
                },
                new float[]{ 0, 0.8f, 0.9f, 1f },
                TileMode.MIRROR
            );
      }

      @Override
      protected void draw ( @NonNull Canvas canvas, float progress ) {

            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();

            int i = canvas.saveLayer( bounds.left, bounds.top, bounds.right, bounds.bottom, mPaint, Canvas.ALL_SAVE_FLAG );

            /* 绘制光晕 */
            mPaint.setShader( mRadialGradient );
            canvas.drawCircle(
                width >> 1, height >> 1,
                mRadius * 0.8f + mRadius * 0.2f * mHaloProcess,
                mPaint
            );
            mPaint.setShader( null );
            /* 改变光晕进度 */
            if( mHaloProcess > 1f ) {
                  mOri = -1;
            } else if( mHaloProcess < 0f ) {
                  mOri = 1;
            }
            mHaloProcess += ( mCellProcess * mOri );

            /* 挖空中间 */
            mPaint.setXfermode( mXfermode );
            canvas.drawCircle( width >> 1, height >> 1, mRadius * 0.8f, mPaint );
            mPaint.setXfermode( null );
            /* 解决中间黑洞 */
            canvas.restoreToCount( i );

            super.draw( canvas, progress );

            /* 光晕动画 */
            invalidateSelf();
      }
}
