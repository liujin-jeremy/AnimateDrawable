package tech.liujin.drawable.progress;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.FloatRange;
import tech.liujin.drawable.PaintDrawable;
import tech.liujin.drawable.ProcessConsumer;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class ProgressDrawable extends PaintDrawable implements ProcessConsumer {

      private final String TAG = getClass().getSimpleName();

      /**
       * 当前进度
       */
      protected float mProgress;
      /**
       * 用于初始化调用{@link #onProcessChange(float)}
       */
      protected int   mBoundsChangeCount;

      /**
       * 当进度改变时,准备{@link #draw(Canvas)}所需要的数据,准备完毕后调用{@link #invalidateSelf()},进行绘制,
       * 该步骤主要进行绘制计算和绘制的分离,提高绘制效率
       *
       * @param progress 进度值
       */
      @Override
      public abstract void onProcessChange ( float progress );

      public void setProgress ( float progress ) {

            if( mProgress != progress ) {
                  mProgress = progress;
            }
            onProcessChange( progress );
      }

      /**
       * 获取当前进度
       */
      @FloatRange(from = 0f, to = 1f)
      public float getProgress ( ) {

            return mProgress;
      }

      @Override
      protected void onBoundsChange ( Rect bounds ) {

            super.onBoundsChange( bounds );
            mBoundsChangeCount++;
            if( mBoundsChangeCount == 1 ) {
                  onProcessChange( mProgress );
            }
      }
}
