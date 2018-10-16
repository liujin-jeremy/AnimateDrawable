package com.threekilogram.wuxio.animatedrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.threekilogram.drawable.AnimateWrapperDrawable;
import com.threekilogram.drawable.BiliBiliLoadingDrawable;
import com.threekilogram.drawable.CircleLoadingDrawable;
import com.threekilogram.drawable.RoundRectCornerDrawable;
import com.threekilogram.drawable.RoundRectPathDrawable;
import com.threekilogram.drawable.widget.AnimateDrawableView;
import com.threekilogram.drawable.widget.StaticAnimateDrawableView;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      private ImageView                 mBilibiliImage;
      private SeekBar                   mBilibiliSeek;
      private ImageView                 mBilibiliAnimateImage;
      private ImageView                 mCircleImage;
      private SeekBar                   mCircleSeek;
      private ImageView                 mCircleAnimateImage;
      private ImageView                 mPathImage;
      private SeekBar                   mPathSeek;
      private ImageView                 mPathAnimateImage;
      private ImageView                 mCornerImage;
      private SeekBar                   mCornerSeek;
      private ImageView                 mCornerAnimateImage;
      private AnimateDrawableView       mBilibiliView;
      private AnimateDrawableView       mCircleView;
      private StaticAnimateDrawableView mStatic0;
      private StaticAnimateDrawableView mStatic1;
      private StaticAnimateDrawableView mStatic2;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            super.setContentView( R.layout.activity_main );
            initView();
      }

      private void initView ( ) {

            mBilibiliImage = findViewById( R.id.bilibiliImage );
            mBilibiliSeek = findViewById( R.id.bilibiliSeek );
            mBilibiliAnimateImage = findViewById( R.id.bilibiliAnimateImage );
            mCircleImage = findViewById( R.id.circleImage );
            mCircleSeek = findViewById( R.id.circleSeek );
            mCircleAnimateImage = findViewById( R.id.circleAnimateImage );
            mPathImage = findViewById( R.id.pathImage );
            mPathSeek = findViewById( R.id.pathSeek );
            mPathAnimateImage = findViewById( R.id.pathAnimateImage );
            mCornerImage = findViewById( R.id.cornerImage );
            mCornerSeek = findViewById( R.id.cornerSeek );
            mCornerAnimateImage = findViewById( R.id.cornerAnimateImage );
            mBilibiliView = findViewById( R.id.bilibiliView );
            mCircleView = findViewById( R.id.circleView );
            mStatic0 = findViewById( R.id.static0 );
            mStatic1 = findViewById( R.id.static1 );
            mStatic2 = findViewById( R.id.static2 );

            int color = getResources().getColor( R.color.orangered );
            testBilibili( color );
            testCircle( color );
            testPath( color );
            testCorner( color );
            testView();

            testStatic( color );
      }

      private void testStatic ( int color ) {

            BiliBiliLoadingDrawable loadingDrawable = new BiliBiliLoadingDrawable();
            loadingDrawable.setRadius( 10 );
            loadingDrawable.setRadius( 20 );
            loadingDrawable.setColor( color );
            StaticAnimateDrawableView.setDrawable( loadingDrawable );

            OnClickListener listener = new OnClickListener() {

                  @Override
                  public void onClick ( View v ) {

                        if( v.getVisibility() == View.VISIBLE ) {
                              v.setVisibility( View.INVISIBLE );
                        } else {
                              v.setVisibility( View.VISIBLE );
                        }
                  }
            };

            mStatic0.setOnClickListener( listener );
            mStatic1.setOnClickListener( listener );
            mStatic2.setOnClickListener( listener );
      }

      private void testView ( ) {

            BiliBiliLoadingDrawable loadingDrawable = new BiliBiliLoadingDrawable();
            mBilibiliView.setDrawable( loadingDrawable );
            mBilibiliView.setCount( Integer.MAX_VALUE );
            mBilibiliView.setOnClickListener( v -> {

                  if( mBilibiliView.isRunning() ) {
                        mBilibiliView.stop();
                  } else {
                        mBilibiliView.start();
                  }
            } );

            CircleLoadingDrawable circleLoadingDrawable = new CircleLoadingDrawable();
            mCircleView.setDrawable( circleLoadingDrawable );
            mCircleView.setOnClickListener( v -> {
                  if( mCircleView.isRunning() ) {
                        mCircleView.stop();
                  } else {
                        mCircleView.start();
                  }
            } );
      }

      private void testCorner ( int color ) {

            RoundRectCornerDrawable cornerDrawable = new RoundRectCornerDrawable();
            cornerDrawable.setColor( color );
            mCornerImage.setImageDrawable( cornerDrawable );
            mCornerSeek.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        cornerDrawable.setDrawProgress( v );
                  }
            } );

            RoundRectCornerDrawable animateCorner = new RoundRectCornerDrawable();
            animateCorner.setColor( color );
            AnimateWrapperDrawable wrapperDrawable = new AnimateWrapperDrawable( animateCorner );
            wrapperDrawable.setDuration( 1000 );
            mCornerAnimateImage.setImageDrawable( wrapperDrawable );
            mCornerAnimateImage.setOnClickListener( v -> {

                  if( wrapperDrawable.isRunning() ) {
                        wrapperDrawable.stop();
                  } else {
                        wrapperDrawable.start();
                  }
            } );
      }

      private void testPath ( int color ) {

            /* path progress */
            RoundRectPathDrawable pathDrawable = new RoundRectPathDrawable();
            pathDrawable.setStrokeWidth( 16 );
            pathDrawable.setColor( color );
            mPathImage.setImageDrawable( pathDrawable );
            mPathSeek.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        pathDrawable.setDrawProgress( v );
                  }
            } );

            /* path animate */
            RoundRectPathDrawable rectPathDrawable = new RoundRectPathDrawable();
            rectPathDrawable.setMode( RoundRectPathDrawable.COUNTER_CLOCKWISE_SUB );
            rectPathDrawable.setStrokeWidth( 16 );
            rectPathDrawable.setColor( color );
            AnimateWrapperDrawable pathWrapper = new AnimateWrapperDrawable( rectPathDrawable );
            pathWrapper.setDuration( 2000 );
            mPathAnimateImage.setImageDrawable( pathWrapper );
            mPathAnimateImage.setOnClickListener( v -> {
                  if( pathWrapper.isRunning() ) {
                        pathWrapper.stop();
                  } else {
                        pathWrapper.start();
                  }
            } );
      }

      private void testCircle ( int color ) {

            /* circle progress */
            CircleLoadingDrawable circleLoadingDrawable = new CircleLoadingDrawable( 160 );
            circleLoadingDrawable.setColor( color );
            circleLoadingDrawable.setStrokeWidth( 16 );
            mCircleImage.setImageDrawable( circleLoadingDrawable );
            mCircleSeek.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        circleLoadingDrawable.setDrawProgress( v );
                  }
            } );

            /* circle animate */
            CircleLoadingDrawable circleDrawable = new CircleLoadingDrawable( 160 );
            circleDrawable.setColor( color );
            circleDrawable.setStrokeWidth( 16 );
            AnimateWrapperDrawable circleWrapper = new AnimateWrapperDrawable( circleDrawable );
            circleWrapper.setDuration( 4000 );
            circleWrapper.setInterpolator( new AccelerateDecelerateInterpolator() );

            mCircleAnimateImage.setImageDrawable( circleWrapper );
            mCircleAnimateImage.setOnClickListener( v -> {

                  if( circleWrapper.isRunning() ) {
                        circleWrapper.stop();
                  } else {
                        circleWrapper.start();
                  }
            } );
      }

      private void testBilibili ( int color ) {

            /* bilibili progress */
            BiliBiliLoadingDrawable biliBiliLoadingDrawable = new BiliBiliLoadingDrawable( 200 );
            biliBiliLoadingDrawable.setRadius( 20 );
            biliBiliLoadingDrawable.setStrokeWidth( 10 );
            biliBiliLoadingDrawable
                .setColor( color );

            mBilibiliImage.setImageDrawable( biliBiliLoadingDrawable );
            mBilibiliSeek.setOnSeekBarChangeListener( new SimpleOnSeekBarChangeListener() {

                  @Override
                  public void onProgressChanged (
                      SeekBar seekBar, int progress, boolean fromUser ) {

                        float v = progress * 1f / seekBar.getMax();
                        biliBiliLoadingDrawable.setDrawProgress( v );
                  }
            } );

            /* bilibili animate */
            BiliBiliLoadingDrawable biliLoadingDrawable = new BiliBiliLoadingDrawable( 200 );
            biliLoadingDrawable.setRadius( 20 );
            biliLoadingDrawable.setStrokeWidth( 10 );
            biliLoadingDrawable
                .setColor( color );
            AnimateWrapperDrawable bilibiliWrapper = new AnimateWrapperDrawable(
                biliLoadingDrawable );
            bilibiliWrapper.setDuration( 4000 );

            mBilibiliAnimateImage.setImageDrawable( bilibiliWrapper );
            mBilibiliAnimateImage.setOnClickListener( v -> {

                  if( bilibiliWrapper.isRunning() ) {
                        bilibiliWrapper.stop();
                  } else {
                        bilibiliWrapper.start();
                  }
            } );
      }

      public void toWeChat ( View view ) {

            WeChatBottomActivity.start( this );
      }

      public void allVisible ( View view ) {

            mStatic0.setVisibility( View.VISIBLE );
            mStatic1.setVisibility( View.VISIBLE );
            mStatic2.setVisibility( View.VISIBLE );
      }

      private abstract class SimpleOnSeekBarChangeListener implements OnSeekBarChangeListener {

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) { }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) { }
      }
}
