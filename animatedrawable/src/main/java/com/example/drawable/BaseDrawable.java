package com.example.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author wuxio 2018-05-25:6:59
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseDrawable extends Drawable {

    protected Paint mPaint;


    public BaseDrawable() {

        init();
    }


    protected void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }


    /**
     *  draw there
     * @param canvas canvas
     */
    @Override
    public abstract void draw(@NonNull Canvas canvas);



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
