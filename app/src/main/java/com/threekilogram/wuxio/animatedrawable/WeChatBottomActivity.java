package com.threekilogram.wuxio.animatedrawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.threekilogram.drawable.AlphaProgressDrawable;
import com.threekilogram.drawable.widget.ProgressColorTextView;
import tech.threekilogram.pager.scroll.pager.ViewPagerScrollListener;

/**
 * @author wuxio
 */
public class WeChatBottomActivity extends AppCompatActivity {

      private static final String TAG = WeChatBottomActivity.class.getSimpleName();

      protected ViewPager mPager;
      private   TabLayout mTabLayout;

      private String[] mTitles = { "微信", "通信录", "发现", "我" };

      private AlphaProgressDrawable[] mDrawables = new AlphaProgressDrawable[ 4 ];
      private ProgressColorTextView[] mTextViews = new ProgressColorTextView[ 4 ];

      private boolean mTabSelect;

      public static void start ( Context context ) {

            Intent starter = new Intent( context, WeChatBottomActivity.class );
            context.startActivity( starter );
      }

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            super.setContentView( R.layout.activity_wechat_bottom );
            initView();
      }

      private void initView ( ) {

            mPager = findViewById( R.id.pager );
            PagerAdapter adapter = new PagerAdapter( getSupportFragmentManager() );
            mPager.setAdapter( adapter );
            mPager.addOnPageChangeListener( new PagerScroll( mPager ) );
            mTabLayout = findViewById( R.id.tabLayout );
            mTabLayout.setupWithViewPager( mPager );
            mTabLayout.addOnTabSelectedListener( new TabSelectListener() );

            mTabLayout.post( ( ) -> {

                  LayoutInflater layoutInflater = getLayoutInflater();
                  int colorNormal = getResources().getColor( R.color.textColorNormal );
                  int colorSelect = getResources().getColor( R.color.textColorSelected );

                  for( int i = 0; i < mTabLayout.getTabCount(); i++ ) {
                        Tab tabAt = mTabLayout.getTabAt( i );
                        View view = layoutInflater.inflate( R.layout.item_tab, null );
                        tabAt.setCustomView( view );

                        ImageView imageView = view.findViewById( R.id.image );
                        setTabImageDrawable( i, imageView );

                        ProgressColorTextView textView = view.findViewById( R.id.text );
                        textView.setTextColor( colorNormal, colorSelect );
                        textView.setText( mTitles[ i ] );
                        mTextViews[ i ] = textView;
                  }
            } );
      }

      private void setTabImageDrawable ( int index, ImageView imageView ) {

            if( index == 0 ) {

                  Bitmap bitmapNormal = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.home_normal
                  );
                  Bitmap bitmapSelected = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.home_selected
                  );

                  AlphaProgressDrawable alphaProgressDrawable = new AlphaProgressDrawable(
                      bitmapNormal,
                      bitmapSelected
                  );
                  imageView.setImageDrawable( alphaProgressDrawable );
                  mDrawables[ 0 ] = alphaProgressDrawable;
            } else if( index == 1 ) {

                  Bitmap bitmapNormal = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.category_normal
                  );

                  Bitmap bitmapSelected = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.category_selected
                  );

                  AlphaProgressDrawable alphaProgressDrawable = new AlphaProgressDrawable(
                      bitmapNormal,
                      bitmapSelected
                  );
                  imageView.setImageDrawable( alphaProgressDrawable );
                  mDrawables[ 1 ] = alphaProgressDrawable;
            } else if( index == 2 ) {

                  Bitmap bitmapNormal = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.find_normal
                  );

                  Bitmap bitmapSelected = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.find_selected
                  );

                  AlphaProgressDrawable alphaProgressDrawable = new AlphaProgressDrawable(
                      bitmapNormal,
                      bitmapSelected
                  );
                  imageView.setImageDrawable( alphaProgressDrawable );
                  mDrawables[ 2 ] = alphaProgressDrawable;
            } else if( index == 3 ) {

                  Bitmap bitmapNormal = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.mine_normal
                  );

                  Bitmap bitmapSelected = BitmapFactory.decodeResource(
                      getResources(),
                      R.drawable.mine_selected
                  );

                  AlphaProgressDrawable alphaProgressDrawable = new AlphaProgressDrawable(
                      bitmapNormal,
                      bitmapSelected
                  );
                  imageView.setImageDrawable( alphaProgressDrawable );
                  mDrawables[ 3 ] = alphaProgressDrawable;
            }
      }

      private void setProgress ( int i, float progress ) {

            mDrawables[ i ].setProgress( progress );
            mTextViews[ i ].setTextColorProgress( progress );
      }

      private class PagerAdapter extends FragmentStatePagerAdapter {

            private TextFragment[] mFragments = {
                TextFragment.newInstance( mTitles[ 0 ] ),
                TextFragment.newInstance( mTitles[ 1 ] ),
                TextFragment.newInstance( mTitles[ 2 ] ),
                TextFragment.newInstance( mTitles[ 3 ] )
            };

            public String[] getTitles ( ) {

                  return mTitles;
            }

            public PagerAdapter ( FragmentManager fm ) {

                  super( fm );
            }

            @Override
            public Fragment getItem ( int position ) {

                  return mFragments[ position ];
            }

            @Override
            public int getCount ( ) {

                  return mFragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle ( int position ) {

                  return mTitles[ position ];
            }
      }

      private class PagerScroll extends ViewPagerScrollListener {

            /**
             * 创建
             *
             * @param pager pager
             */
            public PagerScroll ( ViewPager pager ) {

                  super( pager );
            }

            @Override
            protected void onScroll ( int state, int currentIndex, int nextIndex, float offset ) {

                  if( mTabSelect ) {
                        return;
                  }
                  setProgress( currentIndex, 1 - Math.abs( offset ) );
                  setProgress( nextIndex, Math.abs( offset ) );
            }

            @Override
            public void onPageScrollStateChanged ( int state ) {

                  super.onPageScrollStateChanged( state );
                  if( state == ViewPager.SCROLL_STATE_IDLE ) {
                        mTabSelect = false;
                  }
            }
      }

      private class TabSelectListener implements OnTabSelectedListener {

            @Override
            public void onTabSelected ( Tab tab ) {

                  int position = tab.getPosition();
                  setProgress( position, 1 );
                  mTabSelect = true;
            }

            @Override
            public void onTabUnselected ( Tab tab ) {

                  setProgress( tab.getPosition(), 0 );
            }

            @Override
            public void onTabReselected ( Tab tab ) {

            }
      }
}
