package com.example.p010_recycleviewall.domain;


import com.example.p010_recycleviewall.application.ConstantUtil;

import java.io.Serializable;

/**
 * Created by jack_D on 2016/3/30.
 */
public class AppBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String app_id = "";
    private String version;
    private String user_id;

    public AppBean(String user_id) {
        //TODO 生成数据
        setApp_id(ConstantUtil.APP_ID);
        setVersion("BXNT_256_0166_0002");//DeviceUtil.getVersionName(DemoApplication.get())
        if(user_id!=null){
            this.user_id=user_id;
        }else{
            this.user_id = "";
        }
    }


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
