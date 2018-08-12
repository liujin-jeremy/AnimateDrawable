package com.threekilogram.drawable.anim;

import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import com.threekilogram.drawable.BaseDrawable;

/**
 * @author wuxio 2018-05-12:11:03
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseAnimateDrawable extends BaseDrawable implements Animatable {

      /**
       * when calculate finish call {@link #invalidateSelf()}, to make {@link #draw(Canvas)} call
       */
      protected abstract void calculate ();
}
