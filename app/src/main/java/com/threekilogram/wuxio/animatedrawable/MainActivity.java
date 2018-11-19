package com.threekilogram.wuxio.animatedrawable;

import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.threekilogram.drawable.AnimateWrapperDrawable;
import com.threekilogram.drawable.BallGridPulseDrawable;
import com.threekilogram.drawable.BallPulseDrawable;
import com.threekilogram.drawable.BallPulsePushDrawable;
import com.threekilogram.drawable.BallRotateDrawable;
import com.threekilogram.drawable.BallTwoRotateDrawable;
import com.threekilogram.drawable.BallsCircleScaleDrawable;
import com.threekilogram.drawable.BallsTriangleRotateDrawable;
import com.threekilogram.drawable.BiliBiliDrawable;
import com.threekilogram.drawable.CirclePathDrawable;
import com.threekilogram.drawable.CubeFlipDrawable;
import com.threekilogram.drawable.CubeGridDrawable;
import com.threekilogram.drawable.CubeTwoRotateDrawable;
import com.threekilogram.drawable.PacManDrawable;
import com.threekilogram.drawable.ProgressDrawable;
import com.threekilogram.drawable.RoundRectCornerDrawable;
import com.threekilogram.drawable.RoundRectPathDrawable;
import com.threekilogram.drawable.StrokePulseDrawable;
import com.threekilogram.drawable.StrokePulsePushDrawable;
import com.threekilogram.drawable.StrokeWaveDrawable;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

      private ImageView mBilibili;
      private SeekBar   mBilibiliSeek;
      private ImageView mBilibiliAnimate;
      private ImageView mCircle;
      private SeekBar   mCircleSeek;
      private ImageView mCircleAnimate;
      private ImageView mRoundRect;
      private SeekBar   mRoundRectSeek;
      private ImageView mRoundRectAnimate;
      private ImageView mCorner;
      private SeekBar   mCornerSeek;
      private ImageView mCornerAnimate;
      private ImageView mBallPulse;
      private SeekBar   mBallPulseSeek;
      private ImageView mBallPulseAnimate;
      private ImageView mBallGridPulse;
      private SeekBar   mBallGridPulseSeek;
      private ImageView mBallGridPulseAnimate;
      private ImageView mBallFlip;
      private SeekBar   mBallFlipSeek;
      private ImageView mBallFlipAnimate;
      private ImageView mBallRotate;
      private SeekBar   mBallRotateSeek;
      private ImageView mBallRotateAnimate;
      private ImageView mRectRotate;
      private SeekBar   mRectRotateSeek;
      private ImageView mRectRotateAnimate;
      private ImageView mBallTriRotate;
      private SeekBar   mBallTriRotateSeek;
      private ImageView mBallTriRotateAnimate;
      private ImageView mStreakWave;
      private SeekBar   mStreakWaveSeek;
      private ImageView mStreakWaveAnimate;
      private ImageView mStreakPulse;
      private SeekBar   mStreakPulseSeek;
      private ImageView mStreakPulseAnimate;
      private ImageView mBallsCircleScale;
      private SeekBar   mBallsCircleScaleSeek;
      private ImageView mBallsCircleScaleAnimate;
      private ImageView mPacMan;
      private SeekBar   mPacManSeek;
      private ImageView mPacManAnimate;
      private ImageView mPulsePush;
      private SeekBar   mPulsePushSeek;
      private ImageView mPulsePushAnimate;
      private ImageView mBallTwo;
      private SeekBar   mBallTwoSeek;
      private ImageView mBallTwoAnimate;
      private ImageView mBallPush;
      private SeekBar   mBallPushSeek;
      private ImageView mBallPushAnimate;
      private ImageView mCubeGrid;
      private SeekBar   mCubeGridSeek;
      private ImageView mCubeGridAnimate;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            super.setContentView( R.layout.activity_main );
            initView();

            int color = getResources().getColor( R.color.orangered );
            testBilibili( color );
            testCircle( color );
            testPath( color );
            testCorner( color );
            testBallScale();
            testBallGrid();
            testBallFlip();
            testBallRotate();
            testRectRotate();
            testBallTriRotate();
            testStreakWave();
            testStreakPulse();
            testBallsCircleScale();
            testPacMan();
            testPulsePush();
            testBallTwo();
            testBallPush();
            testCubeGrid();
      }

      private void testCubeGrid ( ) {

            CubeGridDrawable drawable = new CubeGridDrawable();
            test( drawable, mCubeGrid, mCubeGridSeek, mCubeGridAnimate );
      }

      private void testBallPush ( ) {

            BallPulsePushDrawable drawable = new BallPulsePushDrawable();
            test( drawable, mBallPush, mBallPushSeek, mBallPushAnimate, 10, 2000,
                  new LinearInterpolator()
            );
      }

      private void testBallTwo ( ) {

            BallTwoRotateDrawable drawable = new BallTwoRotateDrawable();
            test( drawable, mBallTwo, mBallTwoSeek, mBallTwoAnimate, 10, 1600,
                  new LinearInterpolator()
            );
      }

      private void testPulsePush ( ) {

            StrokePulsePushDrawable drawable = new StrokePulsePushDrawable();
            test( drawable, mPulsePush, mPulsePushSeek, mPulsePushAnimate );
      }

      private void testPacMan ( ) {

            PacManDrawable drawable = new PacManDrawable();
            test( drawable, mPacMan, mPacManSeek, mPacManAnimate, 10, 800,
                  new AccelerateDecelerateInterpolator()
            );
      }

      private void testBallsCircleScale ( ) {

            BallsCircleScaleDrawable drawable = new BallsCircleScaleDrawable();
            test( drawable, mBallsCircleScale, mBallsCircleScaleSeek, mBallsCircleScaleAnimate );
      }

      private void testStreakPulse ( ) {

            StrokePulseDrawable drawable = new StrokePulseDrawable();
            test( drawable, mStreakPulse, mStreakPulseSeek, mStreakPulseAnimate, 10, 1000,
                  new LinearInterpolator()
            );
      }

      private void testStreakWave ( ) {

            StrokeWaveDrawable drawable = new StrokeWaveDrawable();
            test( drawable, mStreakWave, mStreakWaveSeek, mStreakWaveAnimate, 10, 1200,
                  new LinearInterpolator()
            );
      }

      private void testBallTriRotate ( ) {

            BallsTriangleRotateDrawable drawable = new BallsTriangleRotateDrawable();
            test( drawable, mBallTriRotate, mBallTriRotateSeek, mBallTriRotateAnimate );
      }

      private void testRectRotate ( ) {

            CubeTwoRotateDrawable drawable = new CubeTwoRotateDrawable();
            test( drawable, mRectRotate, mRectRotateSeek, mRectRotateAnimate );
      }

      private void initView ( ) {

            mBilibili = findViewById( R.id.bilibili );
            mBilibiliSeek = findViewById( R.id.bilibiliSeek );
            mBilibiliAnimate = findViewById( R.id.bilibiliAnimate );
            mCircle = findViewById( R.id.circle );
            mCircleSeek = findViewById( R.id.circleSeek );
            mCircleAnimate = findViewById( R.id.circleAnimate );
            mRoundRect = findViewById( R.id.roundRect );
            mRoundRectSeek = findViewById( R.id.roundRectSeek );
            mRoundRectAnimate = findViewById( R.id.roundRectAnimate );
            mCorner = findViewById( R.id.corner );
            mCornerSeek = findViewById( R.id.cornerSeek );
            mCornerAnimate = findViewById( R.id.cornerAnimate );
            mBallPulse = findViewById( R.id.ballPulse );
            mBallPulseSeek = findViewById( R.id.ballPulseSeek );
            mBallPulseAnimate = findViewById( R.id.ballPulseAnimate );
            mBallGridPulse = findViewById( R.id.ballGridPulse );
            mBallGridPulseSeek = findViewById( R.id.ballGridPulseSeek );
            mBallGridPulseAnimate = findViewById( R.id.ballGridPulseAnimate );
            mBallFlip = findViewById( R.id.ballFlip );
            mBallFlipSeek = findViewById( R.id.ballFlipSeek );
            mBallFlipAnimate = findViewById( R.id.ballFlipAnimate );
            mBallRotate = findViewById( R.id.ballRotate );
            mBallRotateSeek = findViewById( R.id.ballRotateSeek );
            mBallRotateAnimate = findViewById( R.id.ballRotateAnimate );
            mRectRotate = findViewById( R.id.rectRotate );
            mRectRotateSeek = findViewById( R.id.rectRotateSeek );
            mRectRotateAnimate = findViewById( R.id.rectRotateAnimate );
            mBallTriRotate = findViewById( R.id.ballTriRotate );
            mBallTriRotateSeek = findViewById( R.id.ballTriRotateSeek );
            mBallTriRotateAnimate = findViewById( R.id.ballTriRotateAnimate );
            mStreakWave = findViewById( R.id.streakWave );
            mStreakWaveSeek = findViewById( R.id.streakWaveSeek );
            mStreakWaveAnimate = findViewById( R.id.streakWaveAnimate );
            mStreakPulse = findViewById( R.id.streakPulse );
            mStreakPulseSeek = findViewById( R.id.streakPulseSeek );
            mStreakPulseAnimate = findViewById( R.id.streakPulseAnimate );
            mBallsCircleScale = findViewById( R.id.ballsCircleScale );
            mBallsCircleScaleSeek = findViewById( R.id.ballsCircleScaleSeek );
            mBallsCircleScaleAnimate = findViewById( R.id.ballsCircleScaleAnimate );
            mPacMan = findViewById( R.id.pacMan );
            mPacManSeek = findViewById( R.id.pacManSeek );
            mPacManAnimate = findViewById( R.id.pacManAnimate );
            mPulsePush = findViewById( R.id.pulsePush );
            mPulsePushSeek = findViewById( R.id.pulsePushSeek );
            mPulsePushAnimate = findViewById( R.id.pulsePushAnimate );
            mBallTwo = findViewById( R.id.ballTwo );
            mBallTwoSeek = findViewById( R.id.ballTwoSeek );
            mBallTwoAnimate = findViewById( R.id.ballTwoAnimate );
            mBallPush = findViewById( R.id.ballPush );
            mBallPushSeek = findViewById( R.id.ballPushSeek );
            mBallPushAnimate = findViewById( R.id.ballPushAnimate );
            mCubeGrid = findViewById( R.id.cubeGrid );
            mCubeGridSeek = findViewById( R.id.cubeGridSeek );
            mCubeGridAnimate = findViewById( R.id.cubeGridAnimate );
      }

      private void test (
          ProgressDrawable drawable, ImageView view, SeekBar seekBar, ImageView animate ) {

            test( drawable, view, seekBar, animate, 10, 1500, new LinearInterpolator() );
      }

      private void test (
          ProgressDrawable drawable,
          ImageView view,
          SeekBar seekBar,
          ImageView animate,
          int duration,
          TimeInterpolator interpolator ) {

            test( drawable, view, seekBar, animate, 10, duration, interpolator );
      }

      private void test (
          ProgressDrawable drawable,
          ImageView view,
          SeekBar seekBar,
          ImageView animate,
          int count,
          int duration,
          TimeInterpolator interpolator ) {

            view.setImageDrawable( drawable );
            seekBar.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        drawable.setDrawProgress( v );
                  }
            } );
            AnimateWrapperDrawable wrapperDrawable = new AnimateWrapperDrawable( drawable );
            wrapperDrawable.setCount( count );
            wrapperDrawable.setDuration( duration );
            wrapperDrawable.setInterpolator( interpolator );
            animate.setImageDrawable( wrapperDrawable );
            animate.setOnClickListener( v -> {
                  if( wrapperDrawable.isRunning() ) {
                        wrapperDrawable.stop();
                  } else {
                        wrapperDrawable.start();
                  }
            } );
      }

      private void testBallRotate ( ) {

            BallRotateDrawable drawable = new BallRotateDrawable();
            test( drawable, mBallRotate, mBallRotateSeek, mBallRotateAnimate, 1200,
                  new AccelerateDecelerateInterpolator()
            );
      }

      private void testBallFlip ( ) {

            CubeFlipDrawable drawable = new CubeFlipDrawable();
            test( drawable, mBallFlip, mBallFlipSeek, mBallFlipAnimate );
      }

      private void testBallGrid ( ) {

            BallGridPulseDrawable drawable = new BallGridPulseDrawable();
            test( drawable, mBallGridPulse, mBallGridPulseSeek, mBallGridPulseAnimate );
      }

      private void testBallScale ( ) {

            BallPulseDrawable ballPulseDrawable = new BallPulseDrawable();
            test( ballPulseDrawable, mBallPulse, mBallPulseSeek, mBallPulseAnimate, 10, 1000,
                  new LinearInterpolator()
            );
      }

      private void testCorner ( int color ) {

            RoundRectCornerDrawable cornerDrawable = new RoundRectCornerDrawable();
            cornerDrawable.setColor( color );
            test( cornerDrawable, mCorner, mCornerSeek, mCornerAnimate );
      }

      private void testPath ( int color ) {

            /* path progress */
            RoundRectPathDrawable pathDrawable = new RoundRectPathDrawable();
            pathDrawable.setStrokeWidth( 16 );
            pathDrawable.setColor( color );
            test( pathDrawable, mRoundRect, mRoundRectSeek, mRoundRectAnimate, 2000,
                  new LinearInterpolator()
            );
      }

      private void testCircle ( int color ) {

            /* circle progress */
            CirclePathDrawable circleLoadingDrawable = new CirclePathDrawable();
            circleLoadingDrawable.setColor( color );
            circleLoadingDrawable.setStrokeWidth( 16 );
            test( circleLoadingDrawable, mCircle, mCircleSeek, mCircleAnimate );
      }

      private void testBilibili ( int color ) {

            /* bilibili progress */
            BiliBiliDrawable biliBiliDrawable = new BiliBiliDrawable();
            biliBiliDrawable.setRadius( 20 );
            biliBiliDrawable.setStrokeWidth( 10 );
            biliBiliDrawable
                .setColor( color );

            test( biliBiliDrawable, mBilibili, mBilibiliSeek, mBilibiliAnimate, 4000,
                  new LinearInterpolator()
            );
      }

      public void toWeChat ( View view ) {

            WeChatBottomActivity.start( this );
      }

      public void toRecycler ( View view ) {

            RecyclerActivity.start( MainActivity.this );
      }

      private abstract class SimpleOnSeekBarChangeListener implements OnSeekBarChangeListener {

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) { }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) { }
      }
}
