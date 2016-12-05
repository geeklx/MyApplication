package com.example.p016_auto_textview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private TextView tv1;
    private TextView tv2;
    private EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed1.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        if (s.length() > 0) {
            tv1.setText(ed1.getText().toString().trim());
            tv2.setText(ed1.getText().toString().trim());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
