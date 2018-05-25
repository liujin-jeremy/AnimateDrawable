package com.example.drawable.progress;

import android.support.annotation.FloatRange;

import com.example.drawable.BaseDrawable;

/**
 * @author wuxio 2018-05-25:7:11
 */
public abstract class BaseProgressDrawable extends BaseDrawable {

    /**
     * 设置进度
     *
     * @param progress 进度
     */
    public abstract void setProgress(@FloatRange(from = 0f, to = 1f) float progress);
}
