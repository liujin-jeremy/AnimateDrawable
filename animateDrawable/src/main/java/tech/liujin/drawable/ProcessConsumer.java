package tech.liujin.drawable;

import android.support.annotation.FloatRange;

/**
 * @author Liujin 2019/5/18:10:43:48
 */
public interface ProcessConsumer {

      /**
       * 根据进度值{@code process}绘制内容
       *
       * @param progress 进度值
       */
      void onProcessChange ( @FloatRange(from = 0f, to = 1f) float progress );
}
