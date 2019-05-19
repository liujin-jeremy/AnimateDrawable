package tech.liujin.drawable.progress.state;

import android.graphics.Rect;
import android.support.annotation.FloatRange;
import tech.liujin.drawable.PaintDrawable;
import tech.liujin.drawable.ProcessConsumer;
import tech.liujin.drawable.StateConsumer;

/**
 * @author Liujin 2019/5/19:18:05:17
 */
public abstract class ProcessStateDrawable extends PaintDrawable implements ProcessConsumer, StateConsumer {

      /**
       * 当前状态
       */
      protected int   mState;
      /**
       * 当前进度
       */
      protected float mProgress;
      /**
       * 用于初始化调用{@link #onProcessChange(float)}
       */
      protected int   mBoundsChangeCount;

      /**
       * @param state 改变状态
       */
      public void setState ( int state ) {

            if( state != mState ) {

                  mState = state;
                  onStateChange( state );
            }
      }

      /**
       * @return 获取当前状态
       */
      public int getCurrentState ( ) {

            return mState;
      }

      /**
       * @param progress 改变进度值,会触发{@link #onProcessChange(float)}
       */
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
