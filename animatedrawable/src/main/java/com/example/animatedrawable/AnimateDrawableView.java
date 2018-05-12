package com.example.animatedrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wuxio 2018-05-12:14:51
 */
public class AnimateDrawableView extends View {

    private static final String TAG = "AnimateDrawableView";


    public AnimateDrawableView(Context context) {

        super(context);
    }


    public AnimateDrawableView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
    }


    public AnimateDrawableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    private AnimateDrawable mDrawable;


    public void setDrawable(AnimateDrawable drawable) {

        mDrawable = drawable;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (mDrawable != null) {
            mDrawable.draw(canvas);
        }
    }
}
