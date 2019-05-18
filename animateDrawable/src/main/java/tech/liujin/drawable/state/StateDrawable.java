package tech.liujin.drawable.state;

import tech.liujin.drawable.PaintDrawable;
import tech.liujin.drawable.StateConsumer;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class StateDrawable extends PaintDrawable implements StateConsumer {

      /**
       * 当前状态
       */
      protected int mState;

      public void setState ( int state ) {

            if( state != mState ) {

                  mState = state;
                  onStateChange( state );
            }
      }

      public int getCurrentState ( ) {

            return mState;
      }

      /**
       * 当状态改变时,准备新状态下数据,之后重新绘制{@link #invalidateSelf()}
       *
       * @param state : 状态
       */
      @Override
      public abstract void onStateChange ( int state );
}

