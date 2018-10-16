package com.threekilogram.wuxio.animatedrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.threekilogram.drawable.AnimateWrapperDrawable;
import com.threekilogram.drawable.BiliBiliLoadingDrawable;
import com.threekilogram.drawable.CircleLoadingDrawable;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

      private static final String TAG = MainActivity.class.getSimpleName();

      private ImageView mBilibiliImage;
      private SeekBar   mBilibiliSeek;
      private ImageView mBilibiliAnimateImage;
      private ImageView mCircleImage;
      private SeekBar   mCircleSeek;
      private ImageView mCircleAnimateImage;

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            super.setContentView( R.layout.activity_main );
            initView();
      }

      private void initView ( ) {

            int color = getResources().getColor( R.color.orangered );

            /* bilibili progress */
            BiliBiliLoadingDrawable biliBiliLoadingDrawable = new BiliBiliLoadingDrawable( 200 );
            biliBiliLoadingDrawable.setRadius( 20 );
            biliBiliLoadingDrawable.setStrokeWidth( 10 );
            biliBiliLoadingDrawable
                .setColor( color );

            mBilibiliImage = findViewById( R.id.bilibiliImage );
            mBilibiliImage.setImageDrawable( biliBiliLoadingDrawable );
            mBilibiliSeek = findViewById( R.id.bilibiliSeek );
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

            mBilibiliAnimateImage = findViewById( R.id.bilibiliAnimateImage );
            mBilibiliAnimateImage.setImageDrawable( bilibiliWrapper );
            mBilibiliAnimateImage.setOnClickListener( v -> {

                  if( bilibiliWrapper.isRunning() ) {
                        bilibiliWrapper.stop();
                  } else {
                        bilibiliWrapper.start();
                  }
            } );

            /* circle progress */
            CircleLoadingDrawable circleLoadingDrawable = new CircleLoadingDrawable( 160 );
            circleLoadingDrawable.setStrokeColor( color );
            circleLoadingDrawable.setStrokeWidth( 16 );
            mCircleImage = findViewById( R.id.circleImage );
            mCircleImage.setImageDrawable( circleLoadingDrawable );
            mCircleSeek = findViewById( R.id.circleSeek );
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
            circleDrawable.setStrokeColor( color );
            circleDrawable.setStrokeWidth( 16 );
            AnimateWrapperDrawable circleWrapper = new AnimateWrapperDrawable( circleDrawable );
            circleWrapper.setDuration( 4000 );
            circleWrapper.setInterpolator( new AccelerateDecelerateInterpolator() );

            mCircleAnimateImage = findViewById( R.id.circleAnimateImage );
            mCircleAnimateImage.setImageDrawable( circleWrapper );
            mCircleAnimateImage.setOnClickListener( v -> {

                  if( circleWrapper.isRunning() ) {
                        circleWrapper.stop();
                  } else {
                        circleWrapper.start();
                  }
            } );
      }

      private abstract class SimpleOnSeekBarChangeListener implements OnSeekBarChangeListener {

            @Override
            public void onStartTrackingTouch ( SeekBar seekBar ) { }

            @Override
            public void onStopTrackingTouch ( SeekBar seekBar ) { }
      }
}
