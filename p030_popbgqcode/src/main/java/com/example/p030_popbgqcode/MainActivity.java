package com.example.p030_popbgqcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.p030_popbgqcode.pop.PopPeopleCenter;
import com.example.p030_popbgqcode.utils.ExpandViewRectUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        ExpandViewRectUtils.expandViewTouchDelegate(tv1, 10, 10, 10, 10);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopPeopleCenter.showVerDialog(MainActivity.this, "http://s3.51cto.com/wyfs02/M02/8B/22/wKiom1hFLyKTGPwiAACdgfAvtXo683.jpg");
            }
        });

        //activity bufen
//        Bitmap shot1 = BitmapUtils.takeScreenShot(getWindow().getDecorView());
//        Bitmap blur1 = BitmapUtils.blur(this, shot1);
//        getWindow().getDecorView().setBackgroundDrawable(new BitmapDrawable(getResources(),blur1));

    }
}
