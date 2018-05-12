package com.example.wuxio.animatedrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.animatedrawable.BiliBiliLoadingDrawable;

/**
 * @author wuxio
 */
public class MainActivity extends AppCompatActivity {


    protected ImageView mAnimate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {

        final BiliBiliLoadingDrawable biliLoadingDrawable = new BiliBiliLoadingDrawable(300);
        biliLoadingDrawable.setStrokeWidth(10);
        biliLoadingDrawable.setRepeat(3);
        biliLoadingDrawable.setColor(getResources().getColor(R.color.colorAccent));
        mAnimate = findViewById(R.id.animate);
        mAnimate.setImageDrawable(biliLoadingDrawable);

        mAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                biliLoadingDrawable.start();
            }
        });
    }
}
