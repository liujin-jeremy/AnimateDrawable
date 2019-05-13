package tech.liujin.drawable.progress.text;

import android.text.TextPaint;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * @author Liujin 2019/5/13:11:32:34
 */
public abstract class TextProgressDrawable extends ProgressDrawable {

      /**
       * 绘制文字
       */
      protected TextPaint mTextPaint;

      protected TextProgressDrawable ( ) {

            mTextPaint = new TextPaint( TextPaint.ANTI_ALIAS_FLAG );
      }
}
