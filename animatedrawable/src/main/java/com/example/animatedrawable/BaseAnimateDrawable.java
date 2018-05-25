package com.example.animatedrawable;

import android.graphics.Canvas;
import android.graphics.drawable.Animatable;

/**
 * @author wuxio 2018-05-12:11:03
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseAnimateDrawable extends BaseDrawable implements Animatable {


    /**
     * when calculate finish call {@link #invalidateSelf()}, to make {@link #draw(Canvas)} call
     */
    protected abstract void calculate();

}
