package com.example.shining.p037_androidutilcode;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.init(getApplication());

        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String shortClassName = info.topActivity.getShortClassName();    //类名
        String className = info.topActivity.getClassName();              //完整类名
        String packageName = info.topActivity.getPackageName();
        boolean isMain = ActivityUtils.isActivityExists(AppUtils.getAppPackageName(), className);

        String aaa = EncryptUtils.encryptMD5ToString("梁肖");
        LogUtils.getConfig();
        LogUtils.e("---aa---", aaa);

        //正则
        RegexUtils.getSplits("", "");

        //test
        List<Biaoge_listBean> list = new ArrayList<Biaoge_listBean>();
        list.add(new Biaoge_listBean("1"));
        list.add(new Biaoge_listBean("2"));
        list.add(new Biaoge_listBean("3"));
        list.add(new Biaoge_listBean("4"));
        list.add(new Biaoge_listBean("5"));
        list.add(new Biaoge_listBean("5"));
        String a = "1,2,3,4,5,6";
        List<String> list1 = stringToList(a);
        LogUtils.e("---string---", listToString(list));
    }

    private List<String> stringToList(String strs) {
        String str[] = strs.split(",");
        return Arrays.asList(str);
    }

    public String listToString(List<Biaoge_listBean> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (Biaoge_listBean string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string.getText_content());
        }
        return result.toString();
    }

    public String listToStringT(List<? extends Object> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (Object string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    private <T> T checkIsNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
}
