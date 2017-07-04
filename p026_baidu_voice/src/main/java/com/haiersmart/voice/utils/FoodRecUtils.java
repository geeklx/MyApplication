/**
 * Copyright 2016,Smart Haier.All rights reserved
 * Description:
 * Author:jiayuzhen
 * ModifyBy:
 * ModifyDate:
 * ModifyDes:
 */
package com.haiersmart.voice.utils;

import android.util.Log;

import com.haiersmart.voice.bean.foodRec.BaiduFood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yuzhen on 2017/2/17.
 *@time 2017/2/17  15:26
 */
public class FoodRecUtils {
    //图片请求接口
    String imgPath = "http://xiaodu.baidu.com/saiya/fridge/image";
    private static InputStream inputStream;

    public static  String  uploadFile(String imgPath, String pathAndName) {

        try {

            Log.i("photoRecup","进入上传方法中");
            Log.i("photoRecup",pathAndName);
            long begintime = System.currentTimeMillis();
            URL url = new URL(imgPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            //读取超时
            connection.setReadTimeout(5000);
            connection.setRequestMethod("POST");
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            File f=new File(pathAndName);
            if(!f.exists()){
                Log.i("photoRecup","照片文件不存在");
                return null;
            }
            InputStream is = new FileInputStream(pathAndName);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
            }
            is.close();
            dos.flush();
            Log.i("photoRecup", System.currentTimeMillis()-begintime+"");
            Log.i("photoRecup","读取流成功，正在将数据上传至远程服务器……等待服务器返回响应,");
            if (connection.getResponseCode() == 200) {
                Log.i("photoRecup", "1getResponseCode = " + 200);
                inputStream = connection.getInputStream();
                String info = getInfoFromIs(inputStream);
                if (info != null) {
                    Log.i("photoRecup", "1info = " + info);
                } else {
                    Log.i("photoRecup", "1info = null");
                }
                if(inputStream!=null){
                    inputStream.close();

                }

                return info;

            }else{
                Log.i("photoRecup","1getResponseCode"+connection.getResponseCode());
                inputStream.close();
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getInfoFromIs(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        String JsonStr = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((JsonStr = reader.readLine()) != null) {
                stringBuilder.append(JsonStr);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    //食材识别返回食材json，解析获取picture_id;
    public static List<BaiduFood> paseJson(String jsonStr){
        List<BaiduFood> foodList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            //0：正常  其他：系统异常
            int status = jsonObject.getInt("status");
            String msg = jsonObject.getString("msg");
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String name = jsonObject2.getString("class");
                //出现可能性
                String probability = jsonObject2.getString("probability");
                BaiduFood baiduFood = new BaiduFood(name,probability);
                foodList.add(baiduFood);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foodList;
    }


    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 1000;//超时时间
    private static final String CHARSET = "utf-8";
    private static final String BOUNDARY = UUID.randomUUID().toString();//边界标识随机生成
    private static final String PREFIX = "--";
    private static final String LINE_END = "\r\n";
    private static final String CONTENT_TYPE = "multipart/form-data"; //内容类型
    /**
     * 上传文件
     * @param file 文件
     * @param RequestURL post地址
     * @param params 除文件外其他参数
     * @param uploadFieldName 上传文件key
     * @return */
    public static String upLoadFile(File file, String RequestURL, Map<String, String> params, String uploadFieldName) {
        String result = null;
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            StringBuffer sb = new StringBuffer();
            sb.append(getRequestData(params));
            if (file != null) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                sb.append("Content-Disposition: form-data; name=\"" + uploadFieldName + "\"; filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: image/jpeg; charset=" + CHARSET + LINE_END);
                sb.append(LINE_END);
            }
            dos.write(sb.toString().getBytes());
            if (file != null) {
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
            }
            dos.flush();
            int res = conn.getResponseCode();
            Log.e(TAG, "response code:" + res);
            if (res == 200) {
                Log.e(TAG, "request success");
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                Log.i(TAG, "result : " + result);
            } else {
                Log.e(TAG, "request error");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对post参数进行编码处理
     * @param params post参数
     * @return
     */
    private static StringBuffer getRequestData(Map<String, String> params) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(PREFIX);
                stringBuffer.append(BOUNDARY);
                stringBuffer.append(LINE_END);
                stringBuffer.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINE_END);
                stringBuffer.append(LINE_END);
                stringBuffer.append(URLEncoder.encode(entry.getValue(), CHARSET));
                stringBuffer.append(LINE_END);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
