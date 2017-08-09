package com.example.p030_popbgqcode.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;


public class QrCodeUtil {
    private static int IMAGE_HALFWIDTH = 50;//宽度值，影响中间图片大小
    private static final int DEFAULT_SIZE = 500;

    /**
     * 生成二维码，默认大小为500*500
     *
     * @param text 需要生成二维码的文字、网址等
     * @return bitmap
     */
    public static void createQRCode(ImageView iv, String text) {
        createQRCode(iv, text, DEFAULT_SIZE);
    }

    /**
     * 生成二维码
     *
     * @param text 需要生成二维码的文字、网址等
     * @param size 需要生成二维码的大小（）
     * @return bitmap
     */
    public static void createQRCode(final ImageView iv, final String text, final int size) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    BitMatrix bitMatrix = new QRCodeWriter().encode(text,
                            BarcodeFormat.QR_CODE, size, size, hints);
                    int[] pixels = new int[size * size];
                    for (int y = 0; y < size; y++) {
                        for (int x = 0; x < size; x++) {
                            if (bitMatrix.get(x, y)) {
                                pixels[y * size + x] = 0xff000000;
                            } else {
                                pixels[y * size + x] = 0xffffffff;
                            }

                        }
                    }
                    sleep(500);
                    final Bitmap bitmap = Bitmap.createBitmap(size, size,
                            Bitmap.Config.ARGB_8888);
                    bitmap.setPixels(pixels, 0, size, 0, 0, size, size);

                    iv.post(new Runnable() {
                        @Override
                        public void run() {
                            if (iv != null) {
                                iv.setImageBitmap(bitmap);
                            }
                        }
                    });
                } catch (WriterException e) {
                    e.printStackTrace();
                    ToastUtil.showToastShort("creat code err");
                } catch (InterruptedException e) {
                    ToastUtil.showToastShort("creat code err");
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
