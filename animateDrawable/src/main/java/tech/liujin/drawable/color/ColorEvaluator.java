package tech.liujin.drawable.color;

import android.animation.ArgbEvaluator;
import android.graphics.Color;

/**
 * @author Liujin 2019/5/13:16:49:14
 */
public class ColorEvaluator {

      private static final String TAG = ColorEvaluator.class.getSimpleName();

      private static final ArgbEvaluator EVALUATOR = new ArgbEvaluator();

      private int[]   mColors;
      private float[] mLocations;

      public ColorEvaluator ( int... colors ) {

            mColors = colors;
            int count = colors.length;
            mLocations = new float[ count ];
            float cell = 1f / ( count - 1 );
            for( int i = 0; i < count; i++ ) {
                  mLocations[ i ] = i * cell;
            }
      }

      /**
       * 如果 colors={red,green,blue,yellow},locations={0,0.25,0.5,1},那么进度在0~0.25之间颜色值根据red和green计算,以此类推
       */
      public ColorEvaluator ( int[] colors, float[] locations ) {

            mColors = colors;
            mLocations = locations;
      }

      public int evaluate ( float process ) {

            int count = mColors.length;
            for( int i = 1; i < count; i++ ) {

                  float start = mLocations[ i - 1 ];
                  float end = mLocations[ i ];

                  if( process <= end ) {
                        float v = ( process - start ) / ( end - start );
                        return (Integer) EVALUATOR.evaluate( v, mColors[ i - 1 ], mColors[ i ] );
                  }
            }
            return Color.TRANSPARENT;
      }
}
