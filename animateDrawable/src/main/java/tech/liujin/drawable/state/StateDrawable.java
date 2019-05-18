package tech.liujin.drawable.state;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import tech.liujin.drawable.DrawableStateConsumer;
import tech.liujin.drawable.PaintDrawable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class StateDrawable extends PaintDrawable implements DrawableStateConsumer {

      /**
       * 当前状态
       */
      protected int mState;

      public void setState ( int state ) {

            if( state != mState ) {

                  mState = state;
                  invalidateSelf();
            }
      }

      public int getCurrentState ( ) {

            return mState;
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            draw( canvas, mState );
      }

      /**
       * 根据进度值 绘制内容
       *
       * @param canvas : 画布
       * @param state : 状态
       */
      public abstract void draw ( @NonNull Canvas canvas, int state );
}

