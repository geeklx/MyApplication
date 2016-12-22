package com.example.p018_activity_fragmenta_b;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.p018_activity_fragmenta_b.mywalletchangemima.ShezhimimaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接口bufen
                Intent intent_sz = new Intent(MainActivity.this, ShezhimimaActivity.class);
                startActivity(intent_sz);
                overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
            }
        });

    }
}
