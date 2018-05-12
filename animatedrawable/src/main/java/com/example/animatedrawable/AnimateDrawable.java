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
public class AnimateDrawable extends Drawable implements Animatable {

    protected Paint mPaint;

    protected int mWidth;
    protected int mHeight;


    public AnimateDrawable() {

        this(100, 100);
    }


    public AnimateDrawable(int width, int height) {

        mWidth = width;
        mHeight = height;

        initPaint();
    }


    protected void initPaint() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }


    @Override
    public void draw(@NonNull Canvas canvas) {

    }


    protected void calculate() {

    }


    @Override
    public int getIntrinsicWidth() {

        return mWidth;
    }


    @Override
    public int getIntrinsicHeight() {

        return mHeight;
    }


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


    @Override
    public void start() {

    }


    @Override
    public void stop() {

    }


    @Override
    public boolean isRunning() {

        return false;
    }
}
