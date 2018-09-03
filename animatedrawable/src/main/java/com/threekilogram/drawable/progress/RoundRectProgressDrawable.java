package com.threekilogram.drawable.progress;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * 根据进度绘制圆角矩形
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-07-23
 * @time: 10:57
 */
public class RoundRectProgressDrawable extends BaseProgressDrawable {

      private static final String TAG = RoundRectProgressDrawable.class.getSimpleName();
      private Path  mPath;
      private RectF mRectF;
      private float[] mRadius = new float[ 8 ];
      private float mProgress;

      public RoundRectProgressDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPath = new Path();
            mRectF = new RectF();
      }

      @Override
      public void setProgress ( float progress ) {

            mProgress = progress;
            invalidateSelf();
      }

      @Override
      public void draw ( @NonNull Canvas canvas ) {

            mPath.reset();

            Rect bounds = getBounds();
            float[] radius = configRadius( bounds, mRadius, mProgress );
            mRectF.left = bounds.left;
            mRectF.top = bounds.top;
            mRectF.right = bounds.right;
            mRectF.bottom = bounds.bottom;
            mPath.addRoundRect( mRectF, radius, Direction.CW );

            canvas.drawPath( mPath, mPaint );
      }

      protected float[] configRadius ( Rect rect, float[] radius, float progress ) {

            int maxRadius = getMaxRadius( rect );
            float radiusCell = progress * maxRadius;

            radius[ 0 ] = radiusCell;
            radius[ 1 ] = radiusCell;
            radius[ 2 ] = radiusCell;
            radius[ 3 ] = radiusCell;
            radius[ 4 ] = radiusCell;
            radius[ 5 ] = radiusCell;
            radius[ 6 ] = radiusCell;
            radius[ 7 ] = radiusCell;

            Log.e( TAG, "configRadius : " + radiusCell );

            return radius;
      }

      protected int getMaxRadius ( Rect rect ) {

            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;

            int min = Math.min( width, height );

            return min >> 1;
      }
}
