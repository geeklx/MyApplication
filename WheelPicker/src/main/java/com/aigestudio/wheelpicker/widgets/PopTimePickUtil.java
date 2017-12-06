package com.aigestudio.wheelpicker.widgets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopTimePickUtil {

    /**
     * 根据不同的对象类型填充数据
     * 年 2015 2016 2017
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_Year(List<String> datas, int start) {
        for (int i = start; i <= DateUtil.year(); i++) {
            datas.add(i + "");
        }
        for (int i = 1985; i < start; i++) {
            datas.add(i + "");
        }

        // 添加空字符串是为了与滚轮做判断，停止无限循环，视觉上填充了上下部分，勿删
//        datas.add("");
//        datas.add("");
//        datas.add("");
//        datas.add("");
    }

    /**
     * 根据不同的对象类型填充数据
     * 月 1 2 3 4
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_Month(List<String> datas, int start, boolean isyear) {
        int month;
        if (isyear) {
            month = DateUtil.month();
        } else {
            month = 12;//
        }
        if (start > month) {
            start = 1;
        } else {
            start = start;
        }

        for (int i = start; i <= month; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
        for (int i = 1; i < start; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
    }

    /**
     * 根据不同的对象类型填充数据
     * 日 01 02 03 04
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_Day(List<String> datas, int start, boolean ismonth, int year, int month) {

        int day;
        if (ismonth) {
            day = DateUtil.day();
        } else {
            day = PanduanMonth(year, month);//
        }
//        ToastUtil.showToastCenter(year+" "+month+" "+day + "");
        if (start > day) {
            start = 1;
        } else {
            start = start;
        }

        //越界判断bufen
        if (day == 1) {
            String v1 = String.format("%02d", 1);
            String v2 = String.format("%02d", 1);
            datas.add(v1);
            datas.add(v2);
        } else {
            for (int i = start; i <= day; i++) {
                String v = String.format("%02d", i);
                datas.add(v);
            }
        }

        for (int i = 1; i < start; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
    }

    public static double num_data(Date startTime,Date endTime) throws ParseException {
        double num =Double.valueOf(PopTimePickUtil.daysBetween(startTime,endTime));
        return  num;
    }

    public static  long nd = 1000 * 24 * 60 * 60;

    public static int daysBetween(Date smdate, Date bdate)
            throws ParseException {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / nd;
            //
            return Integer.parseInt(String.valueOf(between_days));
        }catch(Exception ioe){
            ioe.printStackTrace();
            return 0;
        }
    }

    public static Map<String, Integer> getFresh(double num, Date startTime) {
        Date now = new Date();
        String nowStr = DateUtil.format_ymd(now);//2016-02-12
        String start_Time = DateUtil.format_ymd(startTime);//2016-02-12
        int resultFreshRate = 0;
        int resultOverDay = 0;
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        //食品从生产或是从放进冰箱那天到现在一共多少天
        int day = (int)((DateUtil.parse_ymd(nowStr).getTime() - DateUtil.parse_ymd(start_Time).getTime()) / (24 * 60 * 60 * 1000));
        if (day >= num) {
        } else {
            //新鲜率
            resultFreshRate = (int) ((num - day) * 100 / num);

            resultOverDay = (int) (num - day);

            if(num == 1){
                if(resultOverDay>0){
                    resultFreshRate = 32;
                }else{
                    resultFreshRate = 0;
                }
            }else if(num == 2){
                if(resultOverDay>1){
                    resultFreshRate = 67;
                }else{
                    resultFreshRate = 32;
                }
            }else{//保质期3天及以上
                if (resultFreshRate > 66) {
                } else if (resultFreshRate > 33) {
                }
                //      else if (resultFreshRate >= 30) {
                //        resultFresh = FRESH103;
                //      }
                else if (resultFreshRate > 0) {
                } else {
                }
            }
        }
        resultMap.put("fresh_rate", resultFreshRate);//新鲜率
        resultMap.put("overdue_day", resultOverDay);//离过期的时间长度
        return resultMap;
    }

    /**
     * 当前时间到生产日期有多少天（生产日期不可大于now）
     * @param year
     * @param month
     * @param howmuchmonth
     * @return
     */
    public static int Panduanday(int year, int month, int howmuchmonth) {
        int a = 0;
        for (int i = 1; i <= howmuchmonth; i++) {
            a += PanduanMonth(year, month + i);
        }
        return a;
    }

    /**
     * 根据年判断月份有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int PanduanMonth(int year, int month) {
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month))) {
            return 31;
        } else if (list_little.contains(String.valueOf(month))) {
            return 30;
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                return 29;
            else
                return 28;
        }
    }

    /**
     * 01 02 12 zhuang 1 2 12
     *
     * @param data
     * @return
     */
    public static String changeData_month(String data) {
        char[] chars = data.toCharArray();
        if (String.valueOf(chars[0]).equals("0")) {
            data = String.valueOf(chars[1]);
        } else {
            data = data;
        }
        return data;
    }

    /**
     * 根据不同的对象类型填充数据
     * 天 1 2 3 4 转 01 02 03 04 05
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_Data1(List<String> datas, int start) {
        for (int i = start; i <= 365; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
        for (int i = 1; i < start; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
    }

    /**
     * 根据不同的对象类型填充数据
     * 月 1 2 3 4
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_Data2(List<String> datas, int start) {
        for (int i = start; i <= 50; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
        for (int i = 1; i < start; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
    }

    /**
     * 根据不同的对象类型填充数据
     * 年 1 2 3 4
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_Data3(List<String> datas, int start) {
        for (int i = start; i <= 50; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
        for (int i = 1; i < start; i++) {
            String v = String.format("%02d", i);
            datas.add(v);
        }
    }

    public static final String pop_year = "年";//y
    public static final String pop_month = "月";//m
    public static final String pop_day = "日";//d

    /**
     * 根据不同的对象类型填充数据
     * 年 月 日
     *
     * @param datas 需要传给滚轮的数组
     */
    public static void fillData_YearMonthDay(List<String> datas, int start) {
        for (int i = start; i < 4; i++) {
            if (i == 1) {
                datas.add(pop_day);
            } else if (i == 2) {
                datas.add(pop_month);
            } else if (i == 3) {
                datas.add(pop_year);
            }
        }
        for (int i = 1; i < start; i++) {
            if (i == 1) {
                datas.add(pop_day);
            } else if (i == 2) {
                datas.add(pop_month);
            } else if (i == 3) {
                datas.add(pop_year);
            }
        }
    }

    /**
     * 从2016-05-12 变成 2016 05 12
     *
     * @param str
     * @return
     */
    public static String[] splitTimeStr(String str) {
        String[] result = new String[]{};
        if (str == null) {
            return result;
        }
        result = str.split("-");
        return result;
    }

    /**
     * 5-d 5-m 5-y 转 5 1 5 2 5 3
     *
     * @param str
     * @return
     */
    public static String[] splitUnitStr(String str) {
        String[] result = new String[]{};
        if (str == null) {
            return result;
        }
        String[] temp = str.split("-");
        if (temp.length != 2) {
            return result;
        }
        String v1 = "1";
        String unit = temp[1];
        if (unit.equals("d")) {
            v1 = "1";
        } else if (unit.equals("m")) {
            v1 = "2";
        } else if (unit.equals("y")) {
            v1 = "3";
        }
        result = new String[]{temp[0], v1};

        return result;
    }

    /**
     * 食材列表遮罩层bufen     //5-d 5-m 5-y 转 5日 5月 5年
     *
     * @param food_unit
     * @return
     */
    public static String TimeChanged(String food_unit) {
        String foodunit = "";
        String[] pop_unit_list = PopTimePickUtil.splitUnitStr(food_unit);
        if (pop_unit_list.length == 2) {
            String pop_data = pop_unit_list[0];
            String pop_ymd = pop_unit_list[1];
            if (pop_ymd.equals("1")) {
                foodunit = pop_data + pop_day;
            } else if (pop_ymd.equals("2")) {
                foodunit = pop_data + pop_month;
            } else if (pop_ymd.equals("3")) {
                foodunit = pop_data + pop_year;
            }

        }
        return foodunit;
    }

}
