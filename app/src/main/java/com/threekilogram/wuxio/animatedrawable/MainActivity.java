package com.threekilogram.wuxio.animatedrawable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.threekilogram.drawable.anim.BiliBiliLoadingDrawable;
import com.threekilogram.drawable.anim.CircleLoadingDrawable;
import com.threekilogram.drawable.anim.RoundRectAnimDrawable;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {

      protected ImageView mAnimate;
      protected ImageView mImageView;
      private   TextView  mCountDownText;

      @Override
      protected void onCreate (Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            super.setContentView( R.layout.activity_main );
            initView();
      }

      private void initView () {

            /* bili bili */

            final BiliBiliLoadingDrawable biliLoadingDrawable = new BiliBiliLoadingDrawable( 300 );
            biliLoadingDrawable.setStrokeWidth(10);
            biliLoadingDrawable.setDuration(3500);
            biliLoadingDrawable.setRepeat(3000);
            biliLoadingDrawable.setColor(getResources().getColor(R.color.colorAccent));
            mAnimate = findViewById(R.id.animate);
            mAnimate.setImageDrawable(biliLoadingDrawable);

            mAnimate.setOnClickListener(new OnClickListener() {

                  @Override
                  public void onClick (View v) {

                        if(biliLoadingDrawable.isRunning()) {
                              biliLoadingDrawable.stop();
                        } else {
                              biliLoadingDrawable.start();
                        }
                  }
            });

            /* circle */

            mImageView = (ImageView) findViewById(R.id.imageView);

            final CircleLoadingDrawable circleLoadingDrawable = new CircleLoadingDrawable( 300 );
            circleLoadingDrawable.setStrokeColor(getResources().getColor(R.color.colorAccent));
            circleLoadingDrawable.setStrokeWidth(12);

            mImageView.setImageDrawable(circleLoadingDrawable);
            mImageView.setOnClickListener(new OnClickListener() {

                  @Override
                  public void onClick (View v) {

                        if(circleLoadingDrawable.isRunning()) {

                              circleLoadingDrawable.stop();
                        } else {

                              circleLoadingDrawable.start(1500);
                        }
                  }
            });

            mCountDownText = (TextView) findViewById(R.id.countDownText);
            RoundRectAnimDrawable rectAnimDrawable = new RoundRectAnimDrawable();
            rectAnimDrawable.setColor(Color.BLUE);
            rectAnimDrawable.setStrokeWidth(16);
            rectAnimDrawable.setDuration(1500);
            mCountDownText.setBackgroundDrawable(rectAnimDrawable);

            mCountDownText.setOnClickListener(new OnClickListener() {

                  private int[] modes = {
                      RoundRectAnimDrawable.CLOCK_WISE_ADD,
                      RoundRectAnimDrawable.CLOCK_WISE_SUB,
                      RoundRectAnimDrawable.COUNTER_CLOCK_WISE_ADD,
                      RoundRectAnimDrawable.COUNTER_CLOCK_WISE_SUB
                  };

                  private int time = 0;

                  @Override
                  public void onClick (View v) {

                        if(rectAnimDrawable.isRunning()) {
                              return;
                        }

                        int index = time % modes.length;
//                        rectAnimDrawable.setMode(modes[index]);
                        rectAnimDrawable.setMode(modes[0]);
                        rectAnimDrawable.start();

                        time++;
                  }
            });

            mCountDownText.post(new Runnable() {

                  private final String TAG = MainActivity.class.getSimpleName();

                  @Override
                  public void run () {

                        rectAnimDrawable.setMode(RoundRectAnimDrawable.CLOCK_WISE_SUB);
                        rectAnimDrawable.start();
                        Log.e(TAG, "run : " + Thread.currentThread().getName());
                  }
            });
      }

      public void toWechatBottom (View view) {

            WechatBottomActivity.start(this);
      }
}
