package com.threekilogram.wuxio.animatedrawable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.threekilogram.drawable.BiliBiliDrawable;
import com.threekilogram.drawable.widget.StaticAnimateDrawableView;

public class RecyclerActivity extends AppCompatActivity {

      private RecyclerView mRecycler;

      public static void start ( Context context ) {

            Intent starter = new Intent( context, RecyclerActivity.class );
            context.startActivity( starter );
      }

      @Override
      protected void onCreate ( Bundle savedInstanceState ) {

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_recycler );
            initView();
      }

      private void initView ( ) {

            mRecycler = findViewById( R.id.recycler );
            LinearLayoutManager layout = new LinearLayoutManager( this );
            layout.setOrientation( RecyclerView.VERTICAL );
            mRecycler.setLayoutManager( layout );
            mRecycler.setAdapter( new Adapter() );
      }

      private class Adapter extends RecyclerView.Adapter<ViewHolder> {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder (
                @NonNull ViewGroup parent, int viewType ) {

                  FrameLayout frameLayout = new FrameLayout( RecyclerActivity.this );
                  frameLayout.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, 500 ) );
                  return new ViewHolder( frameLayout );
            }

            @Override
            public void onBindViewHolder (
                @NonNull ViewHolder holder, int position ) {

            }

            @Override
            public int getItemCount ( ) {

                  return 50;
            }
      }

      private class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder ( View itemView ) {

                  super( itemView );

                  ( (FrameLayout) itemView ).addView( create() );
            }

            private StaticAnimateDrawableView create ( ) {

                  StaticAnimateDrawableView view = new StaticAnimateDrawableView(
                      RecyclerActivity.this );

                  if( StaticAnimateDrawableView.getDrawable() == null ) {

                        StaticAnimateDrawableView.setDrawable( createBiliBiliLoadingDrawable() );
                  }
                  return view;
            }

            public BiliBiliDrawable createBiliBiliLoadingDrawable ( ) {

                  BiliBiliDrawable loadingDrawable = new BiliBiliDrawable( 180 );
                  loadingDrawable.setColor( Color.BLUE );
                  loadingDrawable.setStrokeWidth( 5 );
                  loadingDrawable.setRadius( 8 );
                  return loadingDrawable;
            }
      }
}
