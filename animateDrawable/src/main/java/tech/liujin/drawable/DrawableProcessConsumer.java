package tech.liujin.drawable;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * @author Liujin 2019/5/18:10:43:48
 */
public interface DrawableProcessConsumer {

      /**
       * 根据进度值{@code process}绘制内容
       *
       * @param canvas :画布
       * @param progress 进度值
       */
      void draw ( @NonNull Canvas canvas, float progress );
}
