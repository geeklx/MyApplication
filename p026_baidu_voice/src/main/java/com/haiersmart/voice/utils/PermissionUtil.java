package com.haiersmart.voice.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by JefferyLeng on 2016/12/19.
 */
public class PermissionUtil {
    private static PermissionUtil mUtil = null;
    private int curRequestCode = 5000;
    private Map<Integer, PermissionHandles> cache = new HashMap();
    private AlertDialog mdiaLog = null;

    public PermissionUtil() {
    }

    public static PermissionUtil getInstance() {
        if(mUtil == null) {
            Class var0 = PermissionUtil.class;
            synchronized(PermissionUtil.class) {
                if(mUtil == null) {
                    mUtil = new PermissionUtil();
                }
            }
        }

        return mUtil;
    }

    @TargetApi(23)
    public void getPermission(final Activity activity, String[] permissions, PermissionUtil.PermissionHandles handles) {
        if(Build.VERSION.SDK_INT < 23) {
            handles.allowed();
        } else {
            ArrayList permisReq = new ArrayList();
            String[] isNeedDialog = permissions;
            int permiss = permissions.length;

            int i;
            for(i = 0; i < permiss; ++i) {
                String permission = isNeedDialog[i];
                if(activity.checkSelfPermission(permission) != 0) {
                    permisReq.add(permission);
                }
            }

            if(permisReq.size() == 0) {
                handles.allowed();
            } else {
                ++this.curRequestCode;
                boolean var9 = true;
                Iterator var10 = permisReq.iterator();

                while(var10.hasNext()) {
                    String var12 = (String)var10.next();
                    if(!activity.shouldShowRequestPermissionRationale(var12)) {
                        var9 = false;
                    }
                }

                if(var9) {
                    this.showPemisRemindDialog(activity, "小度需要您的信任才能更好的为您服务，请您到设置里手动开启该权限~", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.MAIN");
                            intent.setClassName("com.android.settings", "com.android.settings.ManageApplications");
                            activity.startActivity(intent);
                        }
                    });
                } else {
                    String[] var11 = new String[permisReq.size()];

                    for(i = 0; i < permisReq.size(); ++i) {
                        var11[i] = (String)permisReq.get(i);
                    }

                    activity.requestPermissions(var11, this.curRequestCode);
                }

                this.cache.put(Integer.valueOf(this.curRequestCode), handles);
            }
        }
    }

    private void showPemisRemindDialog(Activity activity, String msg, DialogInterface.OnClickListener okListener) {
        if(this.mdiaLog == null) {
            this.mdiaLog = (new AlertDialog.Builder(activity)).setMessage(msg).setNegativeButton("残忍拒绝", (DialogInterface.OnClickListener)null).setPositiveButton("信任小度", okListener).create();
        }

        this.mdiaLog.show();
    }

    /** @deprecated */
    @Deprecated
    public void permissionAllowed(int requestCode) {
        if(this.cache.containsKey(Integer.valueOf(requestCode))) {
            ((PermissionUtil.PermissionHandles)this.cache.get(Integer.valueOf(requestCode))).allowed();
            this.cache.remove(Integer.valueOf(requestCode));
        }

    }

    /** @deprecated */
    @Deprecated
    public void permissionDenied(int requestCode) {
        if(this.cache.containsKey(Integer.valueOf(requestCode))) {
            ((PermissionUtil.PermissionHandles)this.cache.get(Integer.valueOf(requestCode))).denied();
            this.cache.remove(Integer.valueOf(requestCode));
        }

    }

    public interface PermissionHandles {
        void allowed();

        void denied();
    }
}