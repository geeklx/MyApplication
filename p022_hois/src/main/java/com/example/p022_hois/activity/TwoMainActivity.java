package com.example.p022_hois.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.p022_hois.R;

public class TwoMainActivity extends AppCompatActivity {

    private String mAlertSkuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        mAlertSkuId = getIntent().getStringExtra("sku_id");
        if (mAlertSkuId != null) {
            Toast.makeText(this, mAlertSkuId, Toast.LENGTH_LONG).show();
        }
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back
                finish();
            }
        });


    }
}
