package com.example.shining.p041_uppicture.uploadimg.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shining.p041_uppicture.R;
import com.example.shining.p041_uppicture.uploadimg.adapter.UlRecycleAdapter1;
import com.example.shining.p041_uppicture.uploadimg.application.UlDemoApplication;
import com.example.shining.p041_uppicture.uploadimg.bitmap.UlBimp;
import com.example.shining.p041_uppicture.uploadimg.domain.UlDaoContentModel;
import com.example.shining.p041_uppicture.uploadimg.domain.UlDaoImgModel;
import com.example.shining.p041_uppicture.uploadimg.domain.UlDataImgModel;
import com.example.shining.p041_uppicture.uploadimg.imgutils.PermissionsChecker;
import com.example.shining.p041_uppicture.uploadimg.sqlitedao.UlContentDao;
import com.example.shining.p041_uppicture.uploadimg.sqlitedao.UlImgDao;
import com.example.shining.p041_uppicture.uploadimg.utils.UlBitMapUtils;
import com.example.shining.p041_uppicture.uploadimg.utils.UlFileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.shining.p041_uppicture.uploadimg.imgutils.Utils.PERMISSIONS_STORAGE;
import static com.example.shining.p041_uppicture.uploadimg.imgutils.Utils.REQUEST_EXTERNAL_STORAGE;

public class MainActivityUpLoadImg extends AppCompatActivity implements View.OnClickListener {

    private TextView activity_selectimg_send;
    private EditText et1;
    private RecyclerView recyclerView1;
    private UlRecycleAdapter1 mAdapter1;
    private List<UlDaoImgModel> mList1;
    private UlDaoContentModel mContentModel = new UlDaoContentModel();

    private UlContentDao ulContentDao;
    private UlImgDao ulImgDao;

    private final int TAKE_PICTURE = 0x000000;
    private final int TAKE_PHOTO = 0x000001;
    private final int PHOTO_ACTIVITY = 0x000002;
    private String path = "";
    public static final String MSG_IMG_STR2 = ".jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimg);
        findview();
        onclick();
        doNetWork();
    }

    private void doNetWork() {
        et1.clearFocus();
        // 判断加载数据是否缓存
        ulContentDao = UlContentDao.getInstance();
        UlDaoContentModel mContentModel = ulContentDao.getUlDaoContentModelByDataId(UlDemoApplication.userPhoneId + "");
        if (mContentModel != null) {
            et1.setText(mContentModel.getDataContent());
        }
        ulImgDao = UlImgDao.getInstance();
        mList1 = ulImgDao.getUlDaoImgModelByImgId(UlDemoApplication.userPhoneId);
        if (mList1 != null && mList1.size() >= 1) {
            UlBimp.drr.clear();
            UlBimp.bmp.clear();
            UlBimp.max = 0;
            for (int i = 0; i < mList1.size(); i++) {
                UlBimp.drr.add(mList1.get(i).getImgpath());
                UlBimp.bmp.add(mList1.get(i).getImgbitmap());
            }
        }
        updata_listview();

    }

    private void updata_listview() {
        mAdapter1.notifyDataSetChanged();
    }

    private void findview() {
        activity_selectimg_send = (TextView) findViewById(R.id.activity_selectimg_send);
        et1 = (EditText) findViewById(R.id.et1);
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);

        mAdapter1 = new UlRecycleAdapter1(this);
        if (UlBimp.drr.size() == 1) {
            recyclerView1.setLayoutManager(new GridLayoutManager(this, 1));
        } else {
            recyclerView1.setLayoutManager(new GridLayoutManager(this, 3));
        }
        recyclerView1.setAdapter(mAdapter1);

    }

    private void onclick() {
        activity_selectimg_send.setOnClickListener(this);

        //点击图片显示大图bufen
        mAdapter1.setOnItemImgClickLitener(new UlRecycleAdapter1.OnItemImgClickLitener() {
            @Override
            public void onItemImgClick(View view, int position) {
                if (position == UlBimp.bmp.size()) {
                    view.setVisibility(View.GONE);
                    PopupWindows mPopupWindows = new PopupWindows(MainActivityUpLoadImg.this);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 得到InputMethodManager的实例
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                } else {
                    view.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(MainActivityUpLoadImg.this,
                            UlPhotoActivity.class);
                    intent.putExtra("ID", position);
                    startActivityForResult(intent, PHOTO_ACTIVITY);
                }
            }
        });

        //点击ImgXXbufen
        mAdapter1.setOnItemImgXXClickLitener(new UlRecycleAdapter1.OnItemImgXXClickLitener() {
            @Override
            public void onItemImgXXClick(View view, int position) {
                if (UlBimp.drr.size() == 1) {
                    UlBimp.bmp.clear();
                    UlBimp.drr.clear();
                    UlBimp.max = 0;
                    UlFileUtils.deleteDir();
//                    noScrollgridview.setNumColumns(1);
                    recyclerView1.setLayoutManager(new GridLayoutManager(MainActivityUpLoadImg.this, 1));
                    recyclerView1.setAdapter(mAdapter1);
                    updata_listview();
                } else {
                    UlBimp.bmp.remove(position);
                    UlBimp.drr.remove(position);
                    UlBimp.max--;
//                    noScrollgridview.setNumColumns(4);
                    recyclerView1.setLayoutManager(new GridLayoutManager(MainActivityUpLoadImg.this, 3));
                    recyclerView1.setAdapter(mAdapter1);
                    updata_listview();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_selectimg_send:
                //获取img提交bufen
                // 第一步保存到数据库
                MainActivityUpLoadImg.this.runOnUiThread(new Runnable() {
                    public void run() {
                        // TODO 保存图片地址
                        List<UlDaoImgModel> listcims = new ArrayList<>();
                        // 如果用户有上传图片 则保存
                        if (UlBimp.drr != null && UlBimp.drr.size() > 0) {
                            for (int i = 0; i < UlBimp.drr.size(); i++) {
                                UlDaoImgModel cm = new UlDaoImgModel();
                                String Str = UlBimp.drr.get(i).substring(
                                        UlBimp.drr.get(i).lastIndexOf("/") + 1,
                                        UlBimp.drr.get(i).lastIndexOf("."));
                                cm.setImgpath(UlFileUtils.DIR_IMAGE + Str + ".JPEG");
                                cm.setImgid(UlDemoApplication.userPhoneId);
                                cm.setImgbitmap(UlBimp.bmp.get(i));
                                listcims.add(cm);
                            }

                        }
                        ulImgDao.saveList(listcims);
                        // MyLogUtil
                        // .i("--------JhTjwzActivity1 patientid-------------",
                        // CacheUtil.getUser()
                        // .getProfile().getId()
                        // + "");
                        mContentModel.setDataId(UlDemoApplication.userPhoneId);
                        mContentModel.setDataContent(et1.getText().toString());

                        ulContentDao.save(mContentModel);
                    }
                });
                // 第二步从数据库中取出传值服务器
                // 第一层部分
                mList1 = ulImgDao.getUlDaoImgModelByImgId(UlDemoApplication.userPhoneId);
                List<UlDataImgModel> listcim = new ArrayList<>();
                for (int t = 0; t < mList1.size(); t++) {
                    UlDataImgModel cm = new UlDataImgModel();
                    cm.setUrl(mList1.get(t).getImgpath());
                    cm.setExtension(MSG_IMG_STR2);
                    cm.setBytes(UlBitMapUtils.GetBitMapStr(mList1.get(t).getImgbitmap(), 100));
                    listcim.add(cm);
                }
                // List<CaseImagesModel> listcim = new
                // ArrayList<CaseImagesModel>();
                // for (int i = 0; i < UlBimp.drr.size(); i++) {
                // CaseImagesModel cm = new CaseImagesModel();
                // String Str = UlBimp.drr.get(i).substring(
                // UlBimp.drr.get(i).lastIndexOf("/") + 1,
                // UlBimp.drr.get(i).lastIndexOf("."));
                // cm.setUrl(UlFileUtils.SDPATH + Str + ".JPEG");
                // cm.setExtension(Config.MSG_IMG_STR2);
                // cm.setBytes(UlBitMapUtils
                // .GetBitMapFromUrlToStr(UlFileUtils.SDPATH
                // + Str + ".JPEG"));
                // listcim.add(cm);
                // }
                // 高清的压缩图片全部就在 list 路径里面了
                // 高清的压缩过的 bmp 对象 都在 UlBimp.bmp里面
                // 完成上传服务器后 .........
                UlFileUtils.deleteDir();
                // 第二层部分
                // 保存数据库部分

                ulContentDao.save(mContentModel);
                // 跳转部分
                // Intent intentToJhZhuanjiadetail = new Intent(
                // JhTjwzActivity1.this,
                // JhZhuanjiadetail.class);
                // intentToJhZhuanjiadetail.putExtra("CaseName",
                // ed_bqmc);// 编辑名字
                // intentToJhZhuanjiadetail.putExtra("CaseImages",
                // (Serializable) listcim);// 图片清单
                // startActivityForResult(intentToJhZhuanjiadetail,
                // UlConstantUtil.REQUEST_JH_PAY);
                Toast.makeText(MainActivityUpLoadImg.this, "已上传服务器 并且保存数据到本地", Toast.LENGTH_LONG).show();

                break;
            default:

                break;
        }
    }


    public class PopupWindows extends PopupWindow {

        public PopupWindows(Context mContext) {

            View view = View.inflate(mContext,
                    R.layout.activity_uploading_item_pop, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.qqfade_ins));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.qqpush_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.FILL_PARENT);
            setHeight(ViewGroup.LayoutParams.FILL_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            bt1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //判断权限是否开启
                    PermissionsChecker mPermissionsChecker = new PermissionsChecker(MainActivityUpLoadImg.this);
                    // 缺少权限时, 进入权限配置页面
                    if (mPermissionsChecker.lacksPermissions(PERMISSIONS_STORAGE)) {
                        // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(MainActivityUpLoadImg.this, PERMISSIONS_STORAGE,
                                REQUEST_EXTERNAL_STORAGE);
                    } else {
                        photo();
                    }
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //判断权限是否开启
                    PermissionsChecker mPermissionsChecker = new PermissionsChecker(MainActivityUpLoadImg.this);
                    // 缺少权限时, 进入权限配置页面
                    if (mPermissionsChecker.lacksPermissions(PERMISSIONS_STORAGE)) {
                        // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(MainActivityUpLoadImg.this, PERMISSIONS_STORAGE,
                                REQUEST_EXTERNAL_STORAGE);
                    } else {
                        Intent intent = new Intent(MainActivityUpLoadImg.this,
                                UlOnePicActivity.class);
                        // startActivity(intent);
                        startActivityForResult(intent, TAKE_PHOTO);
                        dismiss();
                    }

                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // ToastUtil.showToastLong("requestCode:"+requestCode+" TAKE_PICTURE:"+TAKE_PICTURE+" TAKE_PHOTO:"+TAKE_PHOTO);
        // MyLogUtil.i("------requestCode-------", requestCode + "");
        // MyLogUtil.i("------resultCode-------", resultCode + "");
        // MyLogUtil.i("------UlConstantUtil.REQUEST_PHONE_PAY-------",
        // UlConstantUtil.REQUEST_PHONE_PAY + "");
        // MyLogUtil.e("------TAKE_PICTURE-------", TAKE_PICTURE + "");
        // MyLogUtil.e("------TAKE_PHOTO-------", TAKE_PHOTO + "");
        switch (requestCode) {
            case TAKE_PICTURE:
                if (UlBimp.drr.size() < 9) {
                    UlBimp.drr.add(path);
                    try {
                        Bitmap bm = UlBimp.revitionImageSize(UlBimp.drr.get(UlBimp.drr.size() - 1));
                        UlBimp.bmp.add(bm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                updata_listview();
                break;
            case TAKE_PHOTO:
                updata_listview();
                break;
            case PHOTO_ACTIVITY:
                if (UlBimp.drr == null || UlBimp.drr.size() == 0) {
                    updata_listview();
                }
                break;
            default:
                break;
        }
    }


    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            if (!UlFileUtils.isFileExist("")) {
                File tempf = UlFileUtils.createSDDir("");
                String aa = "";
            }
            File file = new File(UlFileUtils.DIR_IMAGE, UlFileUtils.DIR_IMAGE_TEMP);
            // if (file.exists()) {
            // file.delete();
            // }
            path = file.getPath();
            Uri imageUri = Uri.fromFile(file);
            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(openCameraIntent, TAKE_PICTURE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // File file = new File(UlFileUtils.SDPATH, String.valueOf(System
        // .currentTimeMillis()) + ".jpg");
        // path = file.getPath();
        // Uri imageUri = Uri.fromFile(file);
        // openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

}
