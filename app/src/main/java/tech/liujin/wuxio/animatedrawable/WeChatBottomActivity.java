package tech.liujin.wuxio.animatedrawable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.threekilogram.wuxio.animatedrawable.R;
import tech.liujin.drawable.widget.ProgressAlphaDrawable;
import tech.liujin.drawable.widget.TabItemBuilder;

/**
 * @author wuxio
 */
public class WeChatBottomActivity extends AppCompatActivity {

      private static final String TAG = WeChatBottomActivity.class.getSimpleName();

      protected ViewPager mPager;
      private   TabLayout mTabLayout;

      private String[] mTitles = { "微信", "通信录", "发现", "我" };

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
            mTabLayout = findViewById( R.id.tabLayout );
            PagerAdapter adapter = new PagerAdapter( getSupportFragmentManager() );
            mPager.setAdapter( adapter );

            // 辅助关联ViewPager和TabLayout
            TabItemBuilder builder = new ItemBuilder( mTabLayout, mPager );
            builder.setTitles( mTitles );
            // 设置textView文字颜色变化
            builder.setTextColorRes( R.color.textColorNormal, R.color.textColorSelected );
            // 配置每个图标资源
            builder.setDrawable( 0, R.drawable.home_normal, R.drawable.home_selected );
            builder.setDrawable( 1, R.drawable.category_normal, R.drawable.category_selected );
            builder.setDrawable( 2, R.drawable.find_normal, R.drawable.find_selected );
            builder.setDrawable( 3, R.drawable.mine_normal, R.drawable.mine_selected );
            // 创建
            builder.build( 0 );
      }

      private class PagerAdapter extends FragmentStatePagerAdapter {

            private TextFragment[] mFragments = {
                TextFragment.newInstance( mTitles[ 0 ] ),
                TextFragment.newInstance( mTitles[ 1 ] ),
                TextFragment.newInstance( mTitles[ 2 ] ),
                TextFragment.newInstance( mTitles[ 3 ] )
            };

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

      private class ItemBuilder extends TabItemBuilder {

            public ItemBuilder ( TabLayout tabLayout, ViewPager viewPager ) {

                  super( tabLayout, viewPager );
            }

            @Override
            public TabItemBuilder setDrawable (
                int position, int normalDrawable, int selectDrawable ) {

                  return super.setDrawable( position, normalDrawable, selectDrawable );
            }

            @Override
            public TabItemBuilder setDrawable (
                int position, ProgressAlphaDrawable drawable ) {

                  return super.setDrawable( position, drawable );
            }
      }
}
