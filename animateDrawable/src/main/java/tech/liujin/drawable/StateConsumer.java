package tech.liujin.drawable;

/**
 * @author Liujin 2019/5/18:10:45:30
 */
public interface StateConsumer {

      /**
       * 根据进度值 绘制内容
       *
       * @param state : 状态
       */
      void onStateChange ( int state );
}
