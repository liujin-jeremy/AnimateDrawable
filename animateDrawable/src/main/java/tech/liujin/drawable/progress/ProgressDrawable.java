package tech.liujin.drawable.progress;

import android.graphics.Canvas;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import tech.liujin.drawable.DrawableProcessConsumer;
import tech.liujin.drawable.PaintDrawable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class ProgressDrawable extends PaintDrawable implements DrawableProcessConsumer {

      /**
       * 当前进度
       */
      protected float mProgress;

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            draw( canvas, mProgress );
      }

      /**
       * 根据进度值{@link #mProgress}绘制内容
       *
       * @param canvas :画布
       * @param progress 进度值
       */
      public abstract void draw ( @NonNull Canvas canvas, float progress );

      /**
       * 设置进度
       *
       * @param progress 进度
       */
      public void setProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            mProgress = progress;
      }

      /**
       * 获取当前进度
       */
      @FloatRange(from = 0f, to = 1f)
      public float getProgress ( ) {

            return mProgress;
      }

      /**
       * 设置进度值,同时重绘
       *
       * @param progress 进度
       */
      public void setDrawProgress ( @FloatRange(from = 0f, to = 1f) float progress ) {

            if( mProgress == progress ) {
                  return;
            }
            mProgress = progress;
            invalidateSelf();
      }
}
