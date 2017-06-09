package com.example.p025_upload_img;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.p025_upload_img.imgutils.PermissionsChecker;
import com.example.p025_upload_img.imgutils.SpUtils;
import com.example.p025_upload_img.imgutils.Utils;

import java.io.File;

import static com.example.p025_upload_img.imgutils.Utils.DIR_IMAGE;
import static com.example.p025_upload_img.imgutils.Utils.PERMISSIONS_STORAGE;
import static com.example.p025_upload_img.imgutils.Utils.REQUEST_EXTERNAL_STORAGE;
import static com.example.p025_upload_img.imgutils.Utils.SP_TEMP1;
import static com.example.p025_upload_img.imgutils.Utils.startPhotoZoom;

public class MainActivity extends AppCompatActivity {

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_change = (Button) findViewById(R.id.btn_change);
        iv_personal_icon = (ImageView) findViewById(R.id.iv_personal_icon);
        String a = SpUtils.get(this).get(SP_TEMP1, "").toString();
        if (a != null && !a.equals("")) {
            Uri img_uri = Uri.fromFile(new File(a));
            iv_personal_icon.setImageURI(img_uri);
        } else {
            iv_personal_icon.setImageResource(R.mipmap.ic_launcher);
        }
        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //判断权限是否开启
                PermissionsChecker mPermissionsChecker = new PermissionsChecker(MainActivity.this);
                // 缺少权限时, 进入权限配置页面
                if (mPermissionsChecker.lacksPermissions(PERMISSIONS_STORAGE)) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE);
                } else {
                    showChoosePicDialog();
                }

            }
        });

    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_PICK);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(DIR_IMAGE, "image.jpg"));//Environment.getExternalStorageDirectory()
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    if (tempUri == null) {
                        Log.i("tag", "The uri is not exist.");
                    }
//                    tempUri = uri;
//                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    startActivityForResult(startPhotoZoom(tempUri), CROP_SMALL_PICTURE);
                    break;
                case CHOOSE_PICTURE:
                    if (data.getData() == null) {
                        Log.i("tag", "The uri is not exist.");
                    }
                    tempUri = data.getData();
//                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    startActivityForResult(startPhotoZoom(data.getData()), CROP_SMALL_PICTURE);
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }


    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     * @param data
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            iv_personal_icon.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = Utils.savePhoto(bitmap, DIR_IMAGE, String
                .valueOf("geeklovewho"));//System.currentTimeMillis()
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了
            // ...
            SpUtils.get(this).put(SP_TEMP1, imagePath);
        }
    }
}
