package com.example.shining.p038_wheelpicker;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aigestudio.wheelpicker.widgets.WheelDatePicker;

import java.util.Date;

import static com.aigestudio.wheelpicker.WheelPicker.ALIGN_CENTER;

public class MainActivityData extends AppCompatActivity implements WheelDatePicker.OnDateSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_data);

        WheelDatePicker wheelData = (WheelDatePicker) findViewById(R.id.main_wheel_default);
        wheelData.setOnDateSelectedListener(this);

        //setting
        wheelData.setItemTextSize(100);
        wheelData.setItemTextColor(ContextCompat.getColor(this, R.color.colorA7A7DB));
        wheelData.setSelectedItemTextColor(ContextCompat.getColor(this, R.color.color536D8A));
        wheelData.setItemSpace(40);
//        wheelData.setIndicator(true);
//        wheelData.setIndicatorSize(20);
//        wheelData.setIndicatorColor(ContextCompat.getColor(this, R.color.transparent));

        wheelData.setItemAlignYear(ALIGN_CENTER);
        wheelData.setItemAlignMonth(ALIGN_CENTER);
        wheelData.setItemAlignDay(ALIGN_CENTER);
        wheelData.setCyclic(true);
        wheelData.setCurved(true);
        wheelData.setCurtain(false);
        wheelData.setAtmospheric(true);

        wheelData.getTextViewYear().setText("年");
        wheelData.getTextViewYear().setTextSize(20);
        wheelData.getTextViewMonth().setText("月");
        wheelData.getTextViewMonth().setTextSize(20);
        wheelData.getTextViewDay().setText("日");
        wheelData.getTextViewDay().setTextSize(20);

//        wheelData.getWheelYearPicker().setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayYear)));
//        wheelData.getWheelMonthPicker().setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayMonth)));
//        wheelData.getWheelDayPicker().setData(Arrays.asList(getResources().getStringArray(R.array.WheelArrayDay)));

    }

    @Override
    public void onDateSelected(WheelDatePicker picker, Date date) {
        String text = "";
        switch (picker.getId()) {
            case R.id.main_wheel_default:
                text = text + DateUtil.format_ymd_cn(date);
                break;
            default:

                break;
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
