package tech.liujin.drawable;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/18:10:45:30
 */
public interface DrawableStateConsumer {

      /**
       * 根据进度值 绘制内容
       *
       * @param canvas : 画布
       * @param state : 状态
       */
      void draw ( @NonNull Canvas canvas, int state );
}
