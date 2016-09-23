package com.example.geek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    HeartProgressBar heartProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartProgressBar = (HeartProgressBar) findViewById(R.id.progressBar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartProgressBar.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        heartProgressBar.dismiss();

                    }
                }, 6000);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                if(heartProgressBar.isStopped()) {
//                    ((Button)view).setText("Stop");
//                    heartProgressBar.start();
//                } else {
//                    ((Button)view).setText("Start");
//                    heartProgressBar.dismiss();
//                }
//                if(heartProgressBar.getVisibility()==View.GONE) {
////                    MainActivity.this.runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                        }
////                    });
//                    heartProgressBar.setVisibility(View.VISIBLE);
//                    heartProgressBar.start();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            heartProgressBar.dismiss();
//                            heartProgressBar.setVisibility(View.GONE);
//                        }
//                    }, 6000);
//
//                } else {
//                    heartProgressBar.setVisibility(View.GONE);
//                    heartProgressBar.dismiss();
//                }
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.startActivity(new Intent(MainActivity.this, ViewDemoActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.startActivity(new Intent(MainActivity.this, DialogDemoActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
