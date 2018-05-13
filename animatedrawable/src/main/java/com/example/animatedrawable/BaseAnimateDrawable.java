package com.example.animatedrawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-12:11:03
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseAnimateDrawable extends Drawable implements Animatable {

    protected Paint mPaint;


    public BaseAnimateDrawable() {

        init();
    }


    protected void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }


    /**
     * when {@link #draw(Canvas)} finish, call {@link #calculate()} to continue
     */
    @Override
    public abstract void draw(@NonNull Canvas canvas);


    /**
     * when calculate finish call {@link #invalidateSelf()}, to make {@link #draw(Canvas)} call
     */
    protected abstract void calculate();


    @Override
    public void setAlpha(int alpha) {

        mPaint.setAlpha(alpha);
    }


    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

        mPaint.setColorFilter(colorFilter);
    }


    @Override
    public int getOpacity() {

        return PixelFormat.TRANSPARENT;
    }
}
