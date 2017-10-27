package com.example.shining.p045_butterknifegradle300;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by shining on 2017/10/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setup(savedInstanceState);
    }
    protected abstract int getLayoutId();

    protected void setup(@Nullable Bundle savedInstanceState) {

    }

}
