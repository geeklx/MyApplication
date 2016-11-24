package com.example.p010_recycleviewall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.p010_recycleviewall.recycleviewgridview.MainActivity2;
import com.example.p010_recycleviewall.recycleviewgridviewaddheadandfooter.MainActivity4;
import com.example.p010_recycleviewall.recycleviewlistview.MainActivity1;
import com.example.p010_recycleviewall.recycleviewlistviewaddheadandfooter.MainActivity3;
import com.example.p010_recycleviewall.tablayout.fragmentviewpager.MainActivityTabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivity1.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, MainActivityTabLayout.class);
                startActivity(intent);
            }
        });
    }
}
