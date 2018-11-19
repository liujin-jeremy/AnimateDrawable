package com.threekilogram.wuxio.animatedrawable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
      }
}
