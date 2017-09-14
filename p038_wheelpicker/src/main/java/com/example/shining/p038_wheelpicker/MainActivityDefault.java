package com.example.shining.p038_wheelpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;

import java.util.Arrays;

public class MainActivityDefault extends AppCompatActivity implements WheelPicker.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);

        WheelPicker wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
        wheelLeft.setOnItemSelectedListener(this);
        WheelPicker wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
        wheelCenter.setOnItemSelectedListener(this);
        WheelPicker wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
        wheelRight.setOnItemSelectedListener(this);

        wheelLeft.setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayYear)));
        wheelCenter.setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayMonth)));
        wheelRight.setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayDay)));

    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.main_wheel_left:
                text = "Left:";
                break;
            case R.id.main_wheel_center:
                text = "Center:";
                break;
            case R.id.main_wheel_right:
                text = "Right:";
                break;
        }
        Toast.makeText(this, text + String.valueOf(data), Toast.LENGTH_SHORT).show();
    }
}
