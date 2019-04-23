package tech.liujin.drawable.state;

import android.graphics.Canvas;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Liujin 2018-11-26:11:06
 */
public abstract class StartLoadDoneDrawable extends StateDrawable {

      public static final int STATE_START = 0;
      public static final int STATE_LOAD  = 1;
      public static final int STATE_DONE  = 2;

      @Retention(RetentionPolicy.SOURCE)
      @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
      @IntDef(value = { STATE_START, STATE_LOAD, STATE_DONE })
      public @interface StateValue { }

      @Override
      protected void draw ( @NonNull Canvas canvas, int state ) {

            if( state == STATE_START ) {
                  drawStart( canvas );
            } else if( state == STATE_LOAD ) {
                  drawLoad( canvas );
            } else if( state == STATE_DONE ) {
                  drawDone( canvas );
            }
      }

      @Override
      public void setState ( @StateValue int state ) {

            super.setState( state );
      }

      @StateValue
      @Override
      public int getCurrentState ( ) {

            return super.getCurrentState();
      }

      /**
       * 绘制起始状态{@link #STATE_START}
       *
       * @param canvas 画布
       */
      protected abstract void drawStart ( Canvas canvas );

      /**
       * 绘制加载状态{@link #STATE_LOAD}
       *
       * @param canvas 画布
       */
      protected abstract void drawLoad ( Canvas canvas );

      /**
       * 会直结束状态{@link #STATE_DONE}
       *
       * @param canvas 画布
       */
      protected abstract void drawDone ( Canvas canvas );
}
