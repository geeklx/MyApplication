package com.haiersmart.sfnation.common;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * 设备信息工具类
 */
public class StringUtil {

    /**
     * str is null or length==0 or "null" or "NULL"
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return TextUtils.isEmpty(str) || str.equalsIgnoreCase("null");
    }

    public static String avoidNull(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /**
     * 大图地址
     */
    public static String transformBIG(String url) {
        return url.substring(0, url.lastIndexOf(".")) + ".big"
                + url.substring(url.lastIndexOf("."), url.length());
    }

    /**
     * StringUtil.isEmpty(null) = true StringUtil.isEmpty("") = true
     * StringUtil.isEmpty(" ") = false StringUtil.isEmpty("gaohang") = false
     * StringUtil.isEmpty("  gaohang  ") = false
     */
    // 是否为空
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }


    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public static boolean personIdValidation(String text) {
        text = text.toUpperCase();
//		String regx = "[0-9]{17}x";
//		String reg1 = "[0-9]{15}";
//		String regex = "[0-9]{18}";
        String re = "(\\d{14}[0-9X])|(\\d{17}[0-9X])";
        return text.matches(re) /*|| text.matches(regx) || text.matches(reg1)
                || text.matches(regex)*/;
    }

    /**
     * StringUtil.isBlank(null) = true StringUtil.isBlank("") = true
     * StringUtil.isBlank(" ") = true StringUtil.isBlank("gaohang") = false
     * StringUtil.isBlank("  gaohang  ") = false
     */
    // 是否为空白
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * StringUtil.isNotEmpty(null) = false StringUtil.isNotEmpty("") = false
     * StringUtil.isNotEmpty(" ") = true StringUtil.isNotEmpty("gaohang") = true
     * StringUtil.isNotEmpty("  gaohang  ") = true
     */
    // 是否不为空
    public static boolean isNotEmpty(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * StringUtil.isNotBlank(null) = false StringUtil.isNotBlank("") = false
     * StringUtil.isNotBlank(" ") = false StringUtil.isNotBlank("gaohang") =
     * true StringUtil.isNotBlank("  gaohang  ") = true
     */
    // 是否不为空白
    public static boolean isNotBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return true;
            }
        }
        return false;
    }

    /**
     * StringUtil.isNumeric(null) = false StringUtil.isNumeric("") = false
     * StringUtil.isNumeric("  ") = false StringUtil.isNumeric("1234567890") =
     * true StringUtil.isNumeric("12 3") = false StringUtil.isNumeric("ab2c") =
     * false StringUtil.isNumeric("12-3") = false StringUtil.isNumeric("12.3") =
     * false
     */
    // 是否为数字
    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }


    // 分割字符串
    public static String[] split(String src, String separator) {
        if (src == null || separator == null) {
            return null;
        }
        if (src.length() == 0 || separator.length() == 0) {
            String[] s = {src};
            return s;
        }
        java.util.Vector<String> v = new java.util.Vector<String>();
        int start = 0, end = 0;
        while ((end = src.indexOf(separator, start)) != -1) {
            v.addElement(src.substring(start, end));
            start = end + separator.length();
        }
        v.addElement(src.substring(start));
        int ilen = v.size();
        if (ilen < 1) {
            return null;
        }
        String[] arr = new String[ilen];
        for (int i = 0; i < ilen; i++) {
            arr[i] = v.elementAt(i).toString();
        }
        return arr;
    }

    // 获取url最后一节
    public static String getLastPathComponent(String url) {
        if (url == null)
            return "";
        return url.split("/")[url.split("/").length - 1];
    }

    // 是否是子字符串
    public static boolean isSubString(String str, String subStr) {
        return str.indexOf(subStr) != -1;
    }

    public static void appendString(StringBuffer buff, String str) {
        if (isBlank(str))
            str = "";
        buff.append(str);
    }

    // 字符串拼接
    public static String appendString(String str1, String str2) {
        if (isBlank(str1))
            str1 = "";
        StringBuffer strBuf = new StringBuffer(str1);
        appendString(strBuf, str2);
        return strBuf.toString();
    }

    public static boolean isNotBlank(Object object) {
        return !isBlank(object);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isBlank(Object object) {
        if (isNull(object))
            return true;
        if (object instanceof JSONArray) {
            JSONArray obj = (JSONArray) object;
            if (obj.length() == 0)
                return true;
        }
        if (object instanceof JSONObject) {
            JSONObject obj = (JSONObject) object;
            if (obj.length() == 0)
                return true;
        }
        if (object instanceof List) {
            List<?> obj = (List<?>) object;
            if (obj.size() == 0)
                return true;
        }
        if (object instanceof Map) {
            Map<?, ?> obj = (Map<?, ?>) object;
            if (obj.size() == 0)
                return true;
        }
        return isNull(object);
    }

    public static SpannableString formatText(int left, int right) {
        String num = left + "/" + right;
        SpannableString ssb = new SpannableString(num);
        int index = num.indexOf("/");
        ssb.setSpan(new AbsoluteSizeSpan(40, true), 0, index,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#ff7800")), 0,
                index, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan(18, true), index, num.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#fefefe")),
                index, num.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    /**
     * 判断edittext是否null
     */
    public static String checkEditText(EditText editText) {
        if (editText != null && editText.getText() != null
                && !(editText.getText().toString().trim().equals(""))) {
            return editText.getText().toString().trim();
        } else {
            return "";
        }
    }

    /**
     * 获取arrays的string拼接
     */
    public static String ArraysToString(int[] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(array.length * 6);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }


    public static boolean isNumber(String str) {
        Boolean isNumber = str.matches("-?[0-9]+.*[0-9]*");
        return isNumber;
    }


    public static void printSerialString(byte[] bytes, int size) {
        StringBuffer stringBuffer = new StringBuffer();//by holy
        stringBuffer.append("datatostring:::: ");//by holy
        for (int counts = 0; counts < size; counts++) {//by holy
            stringBuffer.append(String.format("%02x", bytes[counts]));//by holy
            stringBuffer.append(" ");//by holy
        }//by holy
    }

    public static byte getHexByteFrom10(int i) {
        byte b=(byte) Integer.parseInt(Integer.toHexString(i), 16);
        return b;
    }

    public static String fmtPrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

    public static boolean doubleEquals(double d1, double d2, double accuracy) {
        return Math.abs(d1 - d2) < accuracy;
    }
}
