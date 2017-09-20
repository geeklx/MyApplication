package com.example.shining.p041_uppicture.uploadimg.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UlFileUtils {

    public static final String DIR_PROJECT = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/geekuppicture/";
    public static final String DIR_IMAGE_NAME = "image"; // 拍照的图
    public static final String DIR_IMAGE = DIR_PROJECT + DIR_IMAGE_NAME + "/"; // 拍照的图
    public static final String DIR_RADOM = String.valueOf(System.currentTimeMillis());
    public static final String DIR_IMAGE_TEMP = DIR_RADOM + ".JPEG"; // image.JPEG
    // public static String SDPATH = Environment.getExternalStorageDirectory()
    // + "/formatsname/";
    // public static String SDPATH = Environment.getExternalStorageDirectory()
    // + UlConstantUtil.DIR_IMAGE + "/"
    // + String.valueOf(System.currentTimeMillis()) + "/";
//	public static String SDPATH = Environment.getExternalStorageDirectory()
//			+ "/" + String.valueOf(System.currentTimeMillis()) + "/";

    public static void saveBitmap(Bitmap bm, String picName) {
        // Log.e("", "保存图片");
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(DIR_IMAGE, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            // Log.e("", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(DIR_IMAGE + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(DIR_IMAGE + fileName);
        file.isFile();
        return file.exists();
    }

    public static void delFile(String fileName) {
        File file = new File(DIR_IMAGE + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir() {
        File dir = new File(DIR_IMAGE);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }
}
