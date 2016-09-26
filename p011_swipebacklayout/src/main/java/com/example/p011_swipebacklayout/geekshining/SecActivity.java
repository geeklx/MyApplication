package com.example.p011_swipebacklayout.geekshining;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p011_swipebacklayout.R;
import com.example.p011_swipebacklayout.geekshining.lib.app.SwipeBackActivity;

public class SecActivity extends SwipeBackActivity {
	private static final String TAG = "geek";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second); 
        setTitle("Second Activity");
        
      /*  Button btnPickPhoto = (Button)findViewById(R.id.btn_start_sec); 
        btnPickPhoto.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) {
			}
		});
*/
    }
    
	
}

