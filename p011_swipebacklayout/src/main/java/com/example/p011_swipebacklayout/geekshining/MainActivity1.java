package com.example.p011_swipebacklayout.geekshining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.p011_swipebacklayout.R;

public class MainActivity1 extends AppCompatActivity {
	private static final String TAG = "geek";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        setTitle("Main Activity"); 
        Button btnStartSec = (Button)findViewById(R.id.btn_start_sec); 
        btnStartSec.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) {
				toSecondActivity();
			}
		});

    }

	protected void toSecondActivity() {
		Intent intent = new Intent(this, SecActivity.class);
		startActivity(intent);
		
	}
    

}

