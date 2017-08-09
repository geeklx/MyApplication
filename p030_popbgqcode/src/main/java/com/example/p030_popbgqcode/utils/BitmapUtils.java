package com.example.p030_popbgqcode.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;


public class BitmapUtils {

    /**
     * 截图
     * @param view
     * @return
     */
    public static Bitmap takeScreenShot(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap res = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return res;
    }

    /**
     * 模糊
     * @param context
     * @param src
     * @return
     */
    public static Bitmap blur(Context context, Bitmap src) {
        Bitmap out = Bitmap.createBitmap(src);
        // 创建RenderScript内核对象
        RenderScript script = RenderScript.create(context);
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(script, Element.U8_4(script));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
        Allocation inAllo = Allocation.createFromBitmap(script, src);
        Allocation outAllo = Allocation.createFromBitmap(script, out);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blur.setRadius(25f);
        // 设置blurScript对象的输入内存
        blur.setInput(inAllo);
        // 将输出数据保存到输出内存中
        blur.forEach(outAllo);
        // 将数据填充到Allocation中
        outAllo.copyTo(out);

        return out;
    }
}
