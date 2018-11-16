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
import com.threekilogram.drawable.BallRotateDrawable;
import com.threekilogram.drawable.BiliBiliDrawable;
import com.threekilogram.drawable.CircleDrawable;
import com.threekilogram.drawable.ProgressDrawable;
import com.threekilogram.drawable.RectRotateDrawable;
import com.threekilogram.drawable.RoundRectCornerDrawable;
import com.threekilogram.drawable.RoundRectPathDrawable;
import com.threekilogram.drawable.SquareFlipDrawable;

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
      }

      private void testRectRotate ( ) {

            RectRotateDrawable drawable = new RectRotateDrawable();
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
      }

      private void test (
          ProgressDrawable drawable, ImageView view, SeekBar seekBar, ImageView animate ) {

            test( drawable, view, seekBar, animate, 1500, new LinearInterpolator() );
      }

      private void test (
          ProgressDrawable drawable,
          ImageView view,
          SeekBar seekBar,
          ImageView animate,
          int duration,
          TimeInterpolator interpolator ) {

            test( drawable, view, seekBar, animate, 1, duration, interpolator );
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

            SquareFlipDrawable drawable = new SquareFlipDrawable();
            test( drawable, mBallFlip, mBallFlipSeek, mBallFlipAnimate );
      }

      private void testBallGrid ( ) {

            BallGridPulseDrawable drawable = new BallGridPulseDrawable();
            test( drawable, mBallGridPulse, mBallGridPulseSeek, mBallGridPulseAnimate );
      }

      private void testBallScale ( ) {

            BallPulseDrawable ballPulseDrawable = new BallPulseDrawable();
            test( ballPulseDrawable, mBallPulse, mBallPulseSeek, mBallPulseAnimate );
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
            CircleDrawable circleLoadingDrawable = new CircleDrawable();
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
