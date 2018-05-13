package com.example.wuxio.animatedrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.animatedrawable.BiliBiliLoadingDrawable;
import com.example.animatedrawable.CircleLoadingDrawable;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {


    protected ImageView mAnimate;
    protected ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        /* bili bili */

        final BiliBiliLoadingDrawable biliLoadingDrawable = new BiliBiliLoadingDrawable(300);
        biliLoadingDrawable.setStrokeWidth(10);
        biliLoadingDrawable.setRepeat(3000);
        biliLoadingDrawable.setColor(getResources().getColor(R.color.colorAccent));
        mAnimate = findViewById(R.id.animate);
        mAnimate.setImageDrawable(biliLoadingDrawable);

        mAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (biliLoadingDrawable.isRunning()) {
                    biliLoadingDrawable.stop();
                } else {
                    biliLoadingDrawable.start();
                }
            }
        });

        /* circle */

        mImageView = (ImageView) findViewById(R.id.imageView);
        final CircleLoadingDrawable circleLoadingDrawable = new CircleLoadingDrawable(300);
        mImageView.setImageDrawable(circleLoadingDrawable);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (circleLoadingDrawable.isRunning()) {

                    circleLoadingDrawable.stop();
                } else {

                    circleLoadingDrawable.start();
                }
            }
        });
    }
}
