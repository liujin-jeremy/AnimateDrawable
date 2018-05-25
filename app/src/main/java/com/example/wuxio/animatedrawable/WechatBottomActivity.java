package com.example.wuxio.animatedrawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animatedrawable.AlphaSelectedDrawable;
import com.example.constraintlayout.Constraint;
import com.example.constraintlayout.ConstraintLayout;
import com.example.constraintlayout.adapter.BaseConstraintAdapter;

/**
 * @author wuxio
 */
public class WechatBottomActivity extends AppCompatActivity {

    private static final String TAG = "WechatBottomActivity";

    protected ViewPager        mPager;
    protected ConstraintLayout mBottomNavigation;


    public static void start(Context context) {

        Intent starter = new Intent(context, WechatBottomActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_wechat_bottom);
        initView();
    }


    private void initView() {

        mPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

        mBottomNavigation = findViewById(R.id.bottomNavigation);
        mBottomNavigation.setAdapter(new BottomNavigationAdapter(adapter.getTitles()));
    }

    //============================ constraintLayout adapter ============================

    private class BottomNavigationAdapter extends BaseConstraintAdapter {

        private String[] titles;
        private Drawable[] mDrawables = new Drawable[4];


        public BottomNavigationAdapter(String[] titles) {

            this.titles = titles;
        }


        @Override
        public View generateViewTo(int position) {

            if (position % 2 == 0) {

                //icon

                return getIconView();

            } else {

                //title

                return getTitleTextView();
            }
        }


        @Override
        public ConstraintLayout.LayoutParams generateLayoutParamsTo(int position, View view) {

            if (position % 2 == 0) {

                return super.generateLayoutParamsTo(position, view);

            } else {

                return new ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            }
        }


        @Override
        public Constraint generateConstraintTo(int position, Constraint constraint, View view) {

            int cellWidth = constraint.getWeightWidth(4, 1);

            final int iconHeight = 120;

            if (position % 2 == 0) {

                int index = position / 2;

                Drawable drawable = mDrawables[index];
                if (drawable == null) {
                    mDrawables[index] = getIconDrawable(index);
                }
                ((ImageView) view).setImageDrawable(drawable);

                //icon
                constraint.topToTopOfParent(0, iconHeight)
                        .leftToLeftOfParent(cellWidth * index, cellWidth);

            } else {

                //text

                final int textSize = 20;

                int index = position / 2;

                ((TextView) view).setTextSize(textSize);
                ((TextView) view).setText(titles[index]);

                constraint.leftToLeftOfView(position - 1, 0)
                        .rightToRightOfView(position - 1, 0)
                        .topToBottomOfView(position - 1, 0);
            }

            return constraint;
        }


        @Override
        public int getChildCount() {

            return 8;
        }


        private TextView getTitleTextView() {

            TextView textView = new TextView(WechatBottomActivity.this);
            textView.setGravity(Gravity.CENTER);
            return textView;
        }


        private ImageView getIconView() {

            ImageView imageView = new ImageView(WechatBottomActivity.this);
            return imageView;
        }


        private Drawable getIconDrawable(int index) {

            if (index == 0) {

                Bitmap bitmapNormal = BitmapFactory.decodeResource(getResources(), R.drawable.home_normal);
                Bitmap bitmapSelected = BitmapFactory.decodeResource(getResources(), R.drawable
                        .home_selected);

                return new AlphaSelectedDrawable(
                        bitmapNormal,
                        bitmapSelected);

            } else if (index == 1) {

                Bitmap bitmapNormal = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.category_normal
                );

                Bitmap bitmapSelected = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.category_selected
                );

                return new AlphaSelectedDrawable(
                        bitmapNormal,
                        bitmapSelected
                );

            } else if (index == 2) {

                Bitmap bitmapNormal = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.find_normal
                );

                Bitmap bitmapSelected = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.find_selected
                );

                return new AlphaSelectedDrawable(
                        bitmapNormal,
                        bitmapSelected
                );

            } else if (index == 3) {

                Bitmap bitmapNormal = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.mine_normal
                );

                Bitmap bitmapSelected = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.mine_selected
                );

                return new AlphaSelectedDrawable(
                        bitmapNormal,
                        bitmapSelected
                );

            } else {
                return null;
            }

        }
    }

    //============================ pager Adapter ============================

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private String[] titles = {
                "微信",
                "通信录",
                "发现",
                "我"
        };

        private TextFragment[] mFragments = {
                TextFragment.newInstance(titles[0]),
                TextFragment.newInstance(titles[1]),
                TextFragment.newInstance(titles[2]),
                TextFragment.newInstance(titles[3]),
        };


        public String[] getTitles() {

            return titles;
        }


        public PagerAdapter(FragmentManager fm) {

            super(fm);
        }


        @Override
        public Fragment getItem(int position) {

            return mFragments[position];
        }


        @Override
        public int getCount() {

            return mFragments.length;
        }
    }
}
