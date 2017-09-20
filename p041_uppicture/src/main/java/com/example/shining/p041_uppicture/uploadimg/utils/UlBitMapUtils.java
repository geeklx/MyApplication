package com.example.shining.p041_uppicture.uploadimg.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class UlBitMapUtils {

    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff000000;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, int w, int h, int pixels) {
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static int changeColor(String startColor, String endColor,
                                  float process) {
        int start = Color.parseColor(startColor);
        int startR = start >> 16 & 0xff;
        int startG = start >> 8 & 0xff;
        int startB = start & 0xff;

        int end = Color.parseColor(endColor);
        int endR = end >> 16 & 0xff;
        int endG = end >> 8 & 0xff;
        int endB = end & 0xff;
        float colorR = startR + (endR - startR) * process;
        float colorG = startG + (endG - startG) * process;
        float colorB = startB + (endB - startB) * process;
        return Color.rgb((int) colorR, (int) colorG, (int) colorB);
    }

    /**
     * Bitmap to Drawable
     *
     * @param bitmap Bitmap类型的图片
     * @return 返回Dawable类型的图片
     */
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        @SuppressWarnings("deprecation")
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        return bitmapDrawable;
    }

    /**
     * Bitmap from Drawable
     *
     * @param drawable Drawable类型的图片
     * @return 返回Bitmap类型的图片
     */
    public static Bitmap bitmapFromDrawable(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                                : Config.RGB_565);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Bitmap转换Byte数组
     *
     * @param bitmap Bitmap类型的图片
     * @return 返回Byte数组数据
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * Bitmap转换自Byte数组
     *
     * @param data Byte数组的数据
     * @return 返回Bitmap类型的图片
     */
    public static Bitmap bitmapFromBytes(byte[] data) {
        if (data.length != 0) {
            return BitmapFactory.decodeByteArray(data, 0, data.length);
        } else {
            return null;
        }
    }

    /**
     * Bitmap缩放
     *
     * @param bitmap Bitmap类型的图片
     * @param width  Resize后的图片宽度
     * @param height Resize后的图片高度
     * @return 返回Bitmap类型的图片
     */
    public static Bitmap bitmap2Resize(Bitmap bitmap, int width, int height) {
        int tempWidth = bitmap.getWidth();
        int tempHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / tempWidth);
        float scaleHeight = ((float) height / tempHeight);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, tempWidth,
                tempHeight, matrix, true);
        return newBitmap;
    }

    /**
     * Bitmap设置圆角
     *
     * @param bitmap  Bitmap类型的图片
     * @param roundPx 圆角的大小
     * @return Bitmap类型的图片
     */
    public static Bitmap bitmap2Round(Bitmap bitmap, float roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * Bitmap设置倒影
     *
     * @param bitmap                  Bitmap类型的图片
     * @param reflectionImageDistance 倒影与图片的间距
     * @return 返回Bitmap类型的图片
     */
    public static Bitmap bitmap2ReflectionImage(Bitmap bitmap,
                                                int reflectionImageDistance) {
        final int reflectionGap = reflectionImageDistance;// 图片和倒影的距离

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
                width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);

        canvas.drawBitmap(bitmap, 0, 0, null);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();

        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, Color.WHITE,
                0x00ffffff, TileMode.CLAMP);
        // Color.WHITE 交换 0x70ffffff
        paint.setShader(shader);

        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);
        return bitmapWithReflection;
    }

    /**
     * Bitmap设置水印图片
     *
     * @param bitmap    Bitmap类型的图片
     * @param watermark Bitmap类型的水印图片
     * @return Bitmap类型的图片
     */
    public static Bitmap bitmap2WatermarkImage(Bitmap bitmap, Bitmap watermark) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        // 需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(bitmap, 0, 0, null);// 在 0，0坐标开始画入src
        Paint paint = new Paint();
        // 加入图片
        if (watermark != null) {
            int ww = watermark.getWidth();
            int wh = watermark.getHeight();
            paint.setAlpha(50);
            cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印
        }

        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newb;
    }

    /**
     * Bitmap设置水印文字
     *
     * @param bitmap             Bitmap类型的图片
     * @param text               String类型的字符串
     * @param textColor          int类型的文字颜色
     * @param textSize           int类型的文字大小
     * @param x                  int类型的文字x坐标
     * @param y                  int类型的文字y坐标
     * @param isHorizentalCenter 文字是否居中显示
     * @return 返回Bitmap类型的图片
     */
    public static Bitmap bitmap2WatermarkText(Bitmap bitmap, String text,
                                              int textColor, int textSize, int x, int y,
                                              boolean isHorizentalCenter) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        // 需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(bitmap, 0, 0, null);// 在 0，0坐标开始画入src

        // 加入文字
        if (text != null) {
            // String familyName = "宋体";
            // Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(textColor);
            // textPaint.setTypeface(font);
            textPaint.setTextSize(textSize);

            // 这里是自动换行的
            cv.translate(x, y);
            StaticLayout layout = new StaticLayout(text, textPaint, w,
                    isHorizentalCenter ? Alignment.ALIGN_CENTER
                            : Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            layout.draw(cv);

        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储

        return newb;
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, int width, int height) {
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    static int computeSampleSize(Options options,
                                 int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 将Bitmap转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param bitmap
     * @return
     */
    public static String GetBitMapStr(Bitmap bitmap, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        if (quality >= 2) {
            quality = quality - 1;
        }
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将本体url图片转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param path
     * @return
     */
    public static String GetBitMapFromUrlToStr(String path) {
        String result = null;
        int targetSize = 1024 * 1024;
        int size = 1;
        try {
            size = (int) UlFileSizeUtil.getFileSize(path);
            // MyLogUtil.d("FileScale", "TargetSize==" + size);
        } catch (Exception e) {
        }
        if (targetSize > size) {
            result = GetBitMapStr(getDiskBitmap(path), 100);
        } else {
            Double scale = Double.parseDouble("" + targetSize)
                    / Double.parseDouble("" + size);
            int option = size / targetSize;
            // MyLogUtil.d("FileScale", "ScaletSize=="+(new Double(scale *
            // 100).intValue()-1));
            result = GetBitMapStr(getDiskBitmap(path, option), new Double(
                    scale * 100).intValue());
        }
        return result;
    }

    public static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            Options options = new Options();
            options.inSampleSize = 2;
            // MyLogUtil.d("FileScale", "options==" + 2);
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString, options);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }

    public static Bitmap getDiskBitmap(String pathString, int option) {
        Bitmap bitmap = null;
        try {
            Options options = new Options();
            options.inSampleSize = option;
            // MyLogUtil.d("FileScale", "options==" + option);
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString, options);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }

}
