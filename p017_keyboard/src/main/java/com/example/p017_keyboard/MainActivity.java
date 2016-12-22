package com.example.p017_keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.p017_keyboard.keyboardutils.MainActivityKeyboard;
import com.example.p017_keyboard.keyboardutils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityKeyboard.class);
                startActivityForResult(intent, 1001);
                overridePendingTransition(R.anim.push_bottom_in, R.anim.push_bottom_out);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                String ed_text = data.getExtras().getString("edtPwd");
                ToastUtil toastUtil = new ToastUtil(MainActivity.this);
                toastUtil.showToastCenter(ed_text);
            }
        }

    }
}
