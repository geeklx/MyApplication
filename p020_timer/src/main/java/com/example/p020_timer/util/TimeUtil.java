package com.example.p020_timer.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>function: </p>
 * <p>description:  </p>
 * <p>history:  1. 2017/3/15</p>
 * <p>Author: qibin</p>
 * <p>modification:</p>
 */
public class TimeUtil {

    /**
     * @param ms
     * @return result[day, hour, minute, second]
     */
    public static long[] compute(long ms) {
        long[] result = new long[4];

        // day
        result[0] = ms / (24 * 60 * 60 * 1000);
        ms = ms % (24 * 60 * 60 * 1000);

        // hour
        result[1] = ms / (60 * 60 * 1000);
        ms = ms % (60 * 60 * 1000);

        // minute
        result[2] = ms / (60 * 1000);
        ms = ms % (60 * 1000);

        // second
        result[3] = ms / 1000;

        return result;
    }

    /**
     * @param
     * @return void
     * @throws Exception
     * @throws
     * @Description: 设置倒计时的时长
     */
    public static int[] setTime(int hour) {
        int[] result = new int[2];
        //hour 十位
        result[0] = hour / 10;
        //hour 个位
        result[1] = hour - hour / 10 * 10;
        return result;
    }

    /**
     * TimePicker设置初始化数据bufen
     *
     * @param hour
     * @param num
     */
    public static List<String> initTpTime(final int hour, final int num) {
        List<String> data = new ArrayList<String>() {
            {
                for (int i = hour; i < num; i++) {
                    add(i + "");
                }
            }
        };
        return data;
    }

    /**
     * TimePicker设置起始数据bufen  hour=12 num=24 min=59 num=60 sec=59 num=60
     *
     * @param hour
     * @param num
     */
    public static List<String> setTpTime(final int hour, final int num) {
        List<String> data = new ArrayList<String>() {
            {
                for (int i = hour; i < num; i++) {
                    add(i + "");
                }
                for (int i = 0; i < hour; i++) {
                    add(i + "");
                }
            }
        };
        return data;
    }

    /**
     * 获取倒计时布局的hour min sec bufen 10 : 12 : 59 02 : 01 : 02 转成 long
     *
     * @param timeStr
     * @return
     */
    public static long getTimeStrSec(String timeStr) {
        if (timeStr == null || timeStr.length() < 5) {
            return -1L;
        }
        String[] times = timeStr.split(":");
        if (times == null || times.length != 3) {
            return -1L;
        }
        if (times[0] == null || times[1] == null || times[2] == null) {
            return -1L;
        }
        long hour = Long.parseLong(times[0].trim());
        long minute = Long.parseLong(times[1].trim());
        long second = Long.parseLong(times[2].trim());
        return hour * 60L * 60L + minute * 60 + second;
    }

    /**
     * 1 2 3 转 01 02 03
     * @param i
     * @return
     */
    public static String time_change_one(long i) {
        String s = String.format("%02d", i);
        return s;
    }

    /**
     * 01 02 12 zhuang 1 2 12
     *
     * @param data
     * @return
     */
    public static String time_change_two(String data) {
        char[] chars = data.toCharArray();
        if (String.valueOf(chars[0]).equals("0")) {
            data = String.valueOf(chars[1]);
        } else {
            data = data;
        }
        return data;
    }

}
