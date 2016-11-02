package com.example.p012_welcomepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_test1 = (TextView) findViewById(R.id.tv_test1);
        tv_test1.setText(getIntent().getStringExtra("web"));
        Toast.makeText(MainActivity.this, getIntent().getStringExtra("web"), Toast.LENGTH_LONG).show();
    }
}
