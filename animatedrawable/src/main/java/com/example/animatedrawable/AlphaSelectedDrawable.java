package com.example.animatedrawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * @author wuxio 2018-05-25:6:58
 */
public class AlphaSelectedDrawable extends BaseProgressDrawable {

    private Bitmap mNormalBitmap;
    private Bitmap mSelectedBitmap;
    private float  mProgress;


    public AlphaSelectedDrawable(Bitmap normalBitmap, Bitmap selectedBitmap) {

        mNormalBitmap = normalBitmap;
        mSelectedBitmap = selectedBitmap;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {

        int selectedAlpha = (int) (255 * (1 - mProgress));
        int normalAlpha = 255 - selectedAlpha;

        mPaint.setAlpha(normalAlpha);
        canvas.drawBitmap(mNormalBitmap, 0, 0, mPaint);
        this.mPaint.setAlpha(selectedAlpha);
        canvas.drawBitmap(mSelectedBitmap, 0, 0, mPaint);
    }


    @Override
    public void setProgress(float progress) {

        mProgress = progress;
        invalidateSelf();
    }


    @Override
    public int getIntrinsicWidth() {

        return mNormalBitmap.getWidth();
    }


    @Override
    public int getIntrinsicHeight() {

        return mSelectedBitmap.getHeight();
    }
}
