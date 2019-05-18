package tech.liujin.drawable.progress.decoration;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import tech.liujin.drawable.progress.ProgressDrawable;

/**
 * 根据进度绘制圆角矩形
 *
 * @author: Liujin
 * @version: V1.0
 * @date: 2018-07-23
 * @time: 10:57
 */
public class RoundRectCornerDrawable extends ProgressDrawable {

      private Path    mPath;
      private RectF   mRectF;
      private float[] mRadius = new float[ 8 ];

      public RoundRectCornerDrawable ( ) {

            mPaint.setStyle( Style.FILL );
            mPath = new Path();
            mRectF = new RectF();
      }

      @Override
      public void draw ( @NonNull Canvas canvas, float progress ) {

            int width = getWidth();
            int height = getHeight();
            float[] radius = configRadius(
                width, height, mRadius, mProgress );
            mRectF.left = 0;
            mRectF.top = 0;
            mRectF.right = width;
            mRectF.bottom = height;

            mPath.reset();
            mPath.addRoundRect( mRectF, radius, Direction.CW );

            canvas.drawPath( mPath, mPaint );
      }

      private float[] configRadius ( int width, int height, float[] radius, float progress ) {

            int min = Math.min( width, height ) >> 1;
            float radiusCell = progress * min;

            radius[ 0 ] = radiusCell;
            radius[ 1 ] = radiusCell;
            radius[ 2 ] = radiusCell;
            radius[ 3 ] = radiusCell;
            radius[ 4 ] = radiusCell;
            radius[ 5 ] = radiusCell;
            radius[ 6 ] = radiusCell;
            radius[ 7 ] = radiusCell;

            return radius;
      }
}
