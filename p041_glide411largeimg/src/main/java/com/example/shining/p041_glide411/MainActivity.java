package com.example.shining.p041_glide411;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.shining.p041_glide411.glidedemo411.MainActivityGlide;
import com.example.shining.p041_glide411.largeimage.MainActivityLargeImageLocal1;
import com.example.shining.p041_glide411.largeimage.MainActivityLargeImageLocal2;
import com.example.shining.p041_glide411.largeimage.MainActivityLargeImageNet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivityGlide.class));
            }
        });

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivityLargeImageNet.class));
            }
        });

        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivityLargeImageLocal1.class));
            }
        });

        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivityLargeImageLocal2.class));
            }
        });
    }
}
