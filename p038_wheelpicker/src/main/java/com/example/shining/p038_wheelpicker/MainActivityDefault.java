package com.example.shining.p038_wheelpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivityDefault extends AppCompatActivity implements WheelPicker.OnItemSelectedListener {

    private Calendar mCalendar;
    private WheelPicker wheelLeft;
    private WheelPicker wheelCenter;
    private WheelPicker wheelRight;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);
        wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
        wheelLeft.setOnItemSelectedListener(this);
        wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
        wheelCenter.setOnItemSelectedListener(this);
        wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
        wheelRight.setOnItemSelectedListener(this);
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);
        wheelLeft.setData(fillData_Year_Month(mYear, 1));
        wheelCenter.setData(fillData_Year_Month(mMonth, 2));
        wheelRight.setData(updateDays(true));

        showToastCenter("text:" + wheelLeft.getmData_Item() + " " + wheelCenter.getmData_Item() + " " + wheelRight.getmData_Item());
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.main_wheel_left:
                mYear = Integer.valueOf(data + "");
                wheelRight.setData(updateDays(false));
                text = "Left:";
                mDay = wheelRight.getmData_Item();
                break;
            case R.id.main_wheel_center:
                mMonth = Integer.valueOf(data + "");
                wheelRight.setData(updateDays(false));
                text = "Center:";
                mDay = wheelRight.getmData_Item();
                break;
            case R.id.main_wheel_right:
                mDay = Integer.valueOf(data + "");
                text = "Right:";
                break;
        }
        showToastCenter(text + mYear + " " + get12to01(mMonth) + " " + get12to01(mDay));
    }

    private List<String> updateDays(boolean is_first) {
        // mCalendar.clear();
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.MONTH, mMonth - 1);
        int days = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<String> data = new ArrayList<>();
        if (is_first) {

            for (int i = mDay; i <= days; i++) {
                data.add(get12to01(i));
            }
            for (int i = 1; i < mDay; i++) {
                data.add(get12to01(i));
            }
        } else {
            for (int i = 1; i <= days; i++) {
                data.add(get12to01(i));
            }
        }

        return data;
    }

    public List<String> fillData_Year_Month(int start, int which_y_m) {
        List<String> data_new = new ArrayList<>();
        if (which_y_m == 1) {
            for (int i = start; i <= mYear + 10; i++) {
                data_new.add(i + "");
            }
            for (int i = 1900; i < start; i++) {
                data_new.add(i + "");
            }
        } else if (which_y_m == 2) {
            for (int i = start; i <= 12; i++) {
                data_new.add(get12to01(i));
            }
            for (int i = 1; i < start; i++) {
                data_new.add(get12to01(i));
            }
        }
        return data_new;
    }

    private String get12to01(int i) {
        String i2 = String.format("%02d", i);
        return i2;
    }

    private Toast toast;

    public void showToastCenter(String toastText) {
        if (toast == null) {
            toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(toastText + "");
        toast.show();
    }


    private static void updToastTextSize(Toast toast) {
        try {
            Field f = toast.getClass().getDeclaredField("mNextView");
            f.setAccessible(true);
            ViewGroup view = (ViewGroup) f.get(toast);
            if (view == null) {
                return;
            }
            TextView tv = (TextView) view.getChildAt(0);
            if (tv == null) {
                return;
            }
            tv.setTextSize(20);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
