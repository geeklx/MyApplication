package com.example.p051_smartrefreshayut_intentfileuri;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Config;
import android.util.Log;
import android.view.View;

import java.io.File;

/**
 * Created by shining on 2018/2/2.
 */

public class Demo2 extends AppCompatActivity {
    private static final String LOGTAG = "Demo2";
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_shape);
        mContext = Demo2.this;
    }

    public void DemoClick(View view) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10001);

        } else {
            String sdcardPath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "geek11/file";//
            File appDir = new File(sdcardPath2);
            if (!appDir.exists()) {
                appDir.mkdirs();
            }
            viewUrl_util(sdcardPath2 + File.separator + "1.mp3");
        }

    }

    //Android获取一个用于打开AUDIO文件的intent
    public Intent getAudioFileIntent(String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(getUriForFile(mContext, param), "audio/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        return intent;
    }

    /**
     * 举个栗子：通过provider获取到的uri链接
     * content://com.example.shining.p050_smartrefreshlayout.provider/geek1/storage/emulated/0/geek1/1.MP3
     * content://com.honjane.providerdemo.fileprovider/files_path/files/b7d4b092822da.pdf
     * <external-path path="honjane/" name="files_path" />
     * name对应到链接中的files_path
     * path对应到链接中的 files ,当然files是在honjane/目录下
     *
     *
     *
     * @param context
     * @param param
     * @return
     */
    public static Uri getUriForFile(Context context, String param) {
        if (context == null || param == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
//            uri = FileProvider.getUriForFile(context.getApplicationContext(),
//                    context.getApplicationContext().getPackageName() + ".provider", new File(param));
            uri = FileProvider.getUriForFile(context.getApplicationContext(),
                    "com.smartrefreshlayout.provider", new File(param));
//            context.grantUriPermission(context.getApplicationContext().getPackageName(),uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            context.grantUriPermission(context.getApplicationContext().getPackageName(),uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(new File(param));
        }
        return uri;
    }

    public void viewUrl_util(String url) {
        String aa = "";
        Intent intent = getAudioFileIntent(url);
        String a = "";
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                if (Config.LOGD) {
                    Log.d(LOGTAG, "activity not found for " + /*mimeType + */ " over " + Uri.parse(url).getScheme(), e);
                }
            }
        }
    }

    public void viewUrl(String url, String mimeType) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), mimeType);
        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                if (Config.LOGD) {
                    Log.d(LOGTAG, "activity not found for " + mimeType + " over " + Uri.parse(url).getScheme(), e);
                }
            }
        }
    }


}
