package com.example.p051_smartrefreshayut_intentfileuri;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by shining on 2018/2/2.
 */

public class Demo3 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        findViewById(R.id.rl1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareBottomDialogFragment dialogFragment = new ShareBottomDialogFragment();
                dialogFragment.show(getSupportFragmentManager());
            }
        });
    }


}
