package com.threekilogram.wuxio.animatedrawable;

import android.animation.TimeInterpolator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.threekilogram.drawable.progress.AnimateProgressDrawable;
import com.threekilogram.drawable.progress.Arc240RotateDrawable;
import com.threekilogram.drawable.progress.ArcChangeRotateDrawable;
import com.threekilogram.drawable.progress.ArcChangeRotateDrawableV2;
import com.threekilogram.drawable.progress.ArcProgressDrawable;
import com.threekilogram.drawable.progress.BallCircleAlphaDrawable;
import com.threekilogram.drawable.progress.BallCircleScaleDrawable;
import com.threekilogram.drawable.progress.BallGridPulseDrawable;
import com.threekilogram.drawable.progress.BallPulseDrawable;
import com.threekilogram.drawable.progress.BallPulsePushDrawable;
import com.threekilogram.drawable.progress.BallRotateDrawable;
import com.threekilogram.drawable.progress.BallTriangleRotateDrawable;
import com.threekilogram.drawable.progress.BallTwoRotateDrawable;
import com.threekilogram.drawable.progress.BiliBiliDrawable;
import com.threekilogram.drawable.progress.CirclePathDrawable;
import com.threekilogram.drawable.progress.CubeFlipDrawable;
import com.threekilogram.drawable.progress.CubeGridDrawable;
import com.threekilogram.drawable.progress.CubeTwoRotateDrawable;
import com.threekilogram.drawable.progress.PacManDrawable;
import com.threekilogram.drawable.progress.ProgressDrawable;
import com.threekilogram.drawable.progress.RoundRectCornerDrawable;
import com.threekilogram.drawable.progress.RoundRectPathDrawable;
import com.threekilogram.drawable.progress.StrokePulseDrawable;
import com.threekilogram.drawable.progress.StrokePulsePushDrawable;
import com.threekilogram.drawable.progress.StrokeSkipDrawable;
import com.threekilogram.drawable.progress.StrokeWaveDrawable;
import com.threekilogram.drawable.progress.WrongRightDrawable;
import com.threekilogram.drawable.state.AddLoadDoneDrawable;
import com.threekilogram.drawable.state.StartLoadDoneDrawable;
import com.threekilogram.drawable.state.TextLoadDoneDrawable;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      private ImageView    mBilibili;
      private SeekBar      mBilibiliSeek;
      private ImageView    mBilibiliAnimate;
      private ImageView    mCircle;
      private SeekBar      mCircleSeek;
      private ImageView    mCircleAnimate;
      private ImageView    mRoundRect;
      private SeekBar      mRoundRectSeek;
      private ImageView    mRoundRectAnimate;
      private ImageView    mCorner;
      private SeekBar      mCornerSeek;
      private ImageView    mCornerAnimate;
      private ImageView    mBallPulse;
      private SeekBar      mBallPulseSeek;
      private ImageView    mBallPulseAnimate;
      private ImageView    mBallGridPulse;
      private SeekBar      mBallGridPulseSeek;
      private ImageView    mBallGridPulseAnimate;
      private ImageView    mBallFlip;
      private SeekBar      mBallFlipSeek;
      private ImageView    mBallFlipAnimate;
      private ImageView    mBallRotate;
      private SeekBar      mBallRotateSeek;
      private ImageView    mBallRotateAnimate;
      private ImageView    mRectRotate;
      private SeekBar      mRectRotateSeek;
      private ImageView    mRectRotateAnimate;
      private ImageView    mBallTriRotate;
      private SeekBar      mBallTriRotateSeek;
      private ImageView    mBallTriRotateAnimate;
      private ImageView    mStreakWave;
      private SeekBar      mStreakWaveSeek;
      private ImageView    mStreakWaveAnimate;
      private ImageView    mStreakPulse;
      private SeekBar      mStreakPulseSeek;
      private ImageView    mStreakPulseAnimate;
      private ImageView    mBallsCircleScale;
      private SeekBar      mBallsCircleScaleSeek;
      private ImageView    mBallsCircleScaleAnimate;
      private ImageView    mPacMan;
      private SeekBar      mPacManSeek;
      private ImageView    mPacManAnimate;
      private ImageView    mPulsePush;
      private SeekBar      mPulsePushSeek;
      private ImageView    mPulsePushAnimate;
      private ImageView    mBallTwo;
      private SeekBar      mBallTwoSeek;
      private ImageView    mBallTwoAnimate;
      private ImageView    mBallPush;
      private SeekBar      mBallPushSeek;
      private ImageView    mBallPushAnimate;
      private ImageView    mCubeGrid;
      private SeekBar      mCubeGridSeek;
      private ImageView    mCubeGridAnimate;
      private ImageView    mBallAlpha;
      private SeekBar      mBallAlphaSeek;
      private ImageView    mBallAlphaAnimate;
      private ImageView    mStrokeSkip;
      private SeekBar      mStrokeSkipSeek;
      private ImageView    mStrokeSkipAnimate;
      private ImageView    mArc240;
      private SeekBar      mArc240Seek;
      private ImageView    mArc240Animate;
      private ImageView    mArcProgress;
      private SeekBar      mArcProgressSeek;
      private ImageView    mArcChange;
      private SeekBar      mArcChangeSeek;
      private ImageView    mArcChangeAnimate;
      private ImageView    mAddLoading;
      private ScrollView   mScrollView;
      private LinearLayout mContainer;
      private ImageView    mArcChangeV2;
      private SeekBar      mArcChangeSeekV2;
      private ImageView    mArcChangeAnimateV2;
      private ImageView    mTextLoading;
      private ImageView    mWrongRight;
      private SeekBar      mWrongRightSeek;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            super.setContentView( R.layout.activity_main );
            initView();

            mScrollView.post( ( ) -> {

                  int measuredHeight = mContainer.getMeasuredHeight();
                  int height = mScrollView.getMeasuredHeight();

                  mScrollView.scrollTo( 0, measuredHeight - height );
            } );

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
            testBallAlpha();
            testStrokeSkip();
            testArc240();
            testArcProgress();
            testArcChange();
            testArcChangeV2();
            testAddLoading();
            testTextLoading();
            testWrongRight();
      }

      private void testWrongRight ( ) {

            WrongRightDrawable drawable = new WrongRightDrawable();
            mWrongRight.setImageDrawable( drawable );
            mWrongRightSeek.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        drawable.setDrawProgress( v );
                  }
            } );
      }

      private void testTextLoading ( ) {

            TextLoadDoneDrawable drawable = new TextLoadDoneDrawable();
            drawable.setText( "登录", "登录成功" );
            testStartState( drawable, mTextLoading );
      }

      private void testArcChangeV2 ( ) {

            ArcChangeRotateDrawableV2 drawableV2 = new ArcChangeRotateDrawableV2();
            test( drawableV2, mArcChangeV2, mArcChangeSeekV2, mArcChangeAnimateV2 );
      }

      private void testAddLoading ( ) {

            AddLoadDoneDrawable doneDrawable = new AddLoadDoneDrawable();
            testStartState( doneDrawable, mAddLoading );
      }

      private void testStartState ( StartLoadDoneDrawable drawable, ImageView view ) {

            view.setImageDrawable( drawable );

            view.setOnClickListener( v -> {

                  int currentState = drawable.getCurrentState();
                  if( currentState == StartLoadDoneDrawable.STATE_START ) {
                        drawable.setState( StartLoadDoneDrawable.STATE_LOAD );
                  } else if( currentState == StartLoadDoneDrawable.STATE_LOAD ) {
                        drawable.setState( StartLoadDoneDrawable.STATE_DONE );
                  } else if( currentState == StartLoadDoneDrawable.STATE_DONE ) {
                        drawable.setState( StartLoadDoneDrawable.STATE_START );
                  }
            } );
      }

      private void test ( ProgressDrawable drawable, ImageView view, SeekBar seekBar ) {

            view.setImageDrawable( drawable );
            seekBar.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        drawable.setDrawProgress( v );
                  }
            } );
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
            AnimateProgressDrawable wrapperDrawable = new AnimateProgressDrawable( drawable );
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
            mBallAlpha = findViewById( R.id.ballAlpha );
            mBallAlphaSeek = findViewById( R.id.ballAlphaSeek );
            mBallAlphaAnimate = findViewById( R.id.ballAlphaAnimate );
            mStrokeSkip = findViewById( R.id.strokeSkip );
            mStrokeSkipSeek = findViewById( R.id.strokeSkipSeek );
            mStrokeSkipAnimate = findViewById( R.id.strokeSkipAnimate );
            mArc240 = findViewById( R.id.arc240 );
            mArc240Seek = findViewById( R.id.arc240Seek );
            mArc240Animate = findViewById( R.id.arc240Animate );
            mArcProgress = findViewById( R.id.arcProgress );
            mArcProgressSeek = findViewById( R.id.arcProgressSeek );
            mArcChange = findViewById( R.id.arcChange );
            mArcChangeSeek = findViewById( R.id.arcChangeSeek );
            mArcChangeAnimate = findViewById( R.id.arcChangeAnimate );
            mAddLoading = findViewById( R.id.addLoading );
            mScrollView = findViewById( R.id.scrollView );
            mContainer = findViewById( R.id.container );
            mArcChangeV2 = findViewById( R.id.arcChangeV2 );
            mArcChangeSeekV2 = findViewById( R.id.arcChangeSeekV2 );
            mArcChangeAnimateV2 = findViewById( R.id.arcChangeAnimateV2 );
            mTextLoading = findViewById( R.id.textLoading );
            mWrongRight = findViewById( R.id.wrongRight );
            mWrongRightSeek = findViewById( R.id.wrongRightSeek );
      }

      private void testArcChange ( ) {

            ArcChangeRotateDrawable drawable = new ArcChangeRotateDrawable();
            test( drawable, mArcChange, mArcChangeSeek, mArcChangeAnimate );
      }

      private void testArcProgress ( ) {

            ArcProgressDrawable drawable = new ArcProgressDrawable();
            test( drawable, mArcProgress, mArcProgressSeek );
      }

      private void testArc240 ( ) {

            Arc240RotateDrawable drawable = new Arc240RotateDrawable();
            test( drawable, mArc240, mArc240Seek, mArc240Animate, 800, new LinearInterpolator() );
      }

      private void testStrokeSkip ( ) {

            StrokeSkipDrawable drawable = new StrokeSkipDrawable();
            test( drawable, mStrokeSkip, mStrokeSkipSeek, mStrokeSkipAnimate, 800,
                  new LinearInterpolator()
            );
      }

      private void testBallAlpha ( ) {

            BallCircleAlphaDrawable drawable = new BallCircleAlphaDrawable();
            test( drawable, mBallAlpha, mBallAlphaSeek, mBallAlphaAnimate );
      }

      private void testCubeGrid ( ) {

            CubeGridDrawable drawable = new CubeGridDrawable();
            test( drawable, mCubeGrid, mCubeGridSeek, mCubeGridAnimate, 10, 2400,
                  new LinearInterpolator()
            );
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

            BallCircleScaleDrawable drawable = new BallCircleScaleDrawable();
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

            BallTriangleRotateDrawable drawable = new BallTriangleRotateDrawable();
            test( drawable, mBallTriRotate, mBallTriRotateSeek, mBallTriRotateAnimate );
      }

      private void testRectRotate ( ) {

            CubeTwoRotateDrawable drawable = new CubeTwoRotateDrawable();
            test( drawable, mRectRotate, mRectRotateSeek, mRectRotateAnimate );
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
            test( cornerDrawable, mCorner, mCornerSeek, mCornerAnimate, 1, 1000,
                  new LinearInterpolator()
            );
      }

      private void testPath ( int color ) {

            /* path progress */
            RoundRectPathDrawable pathDrawable = new RoundRectPathDrawable();
            pathDrawable.setColor( color );
            test( pathDrawable, mRoundRect, mRoundRectSeek, mRoundRectAnimate, 1, 2000,
                  new LinearInterpolator()
            );
      }

      private void testCircle ( int color ) {

            /* circle progress */
            CirclePathDrawable drawable = new CirclePathDrawable();
            drawable.setColor( color );
            test( drawable, mCircle, mCircleSeek, mCircleAnimate, 10, 2000,
                  new AccelerateDecelerateInterpolator()
            );
      }

      private void testBilibili ( int color ) {

            /* bilibili progress */
            BiliBiliDrawable biliBiliDrawable = new BiliBiliDrawable();
            biliBiliDrawable.setColor( color );

            test(
                biliBiliDrawable,
                mBilibili,
                mBilibiliSeek,
                mBilibiliAnimate,
                4000,
                new LinearInterpolator()
            );
      }

      public void toWeChat ( View view ) {

            WeChatBottomActivity.start( this );
      }

      private abstract class SimpleOnSeekBarChangeListener implements OnSeekBarChangeListener {

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) { }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) { }
      }
}
