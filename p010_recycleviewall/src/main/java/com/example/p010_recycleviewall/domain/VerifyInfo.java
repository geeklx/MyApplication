package com.example.p010_recycleviewall.domain;



import java.io.Serializable;

/**
 * Created by jack_D on 2016/3/30.
 */
public class VerifyInfo implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * app_id : string
     * version : 1.0.0
     * user_id : string
     */

    private AppBean app;
    /**
     * platform : XiaoMi Note2
     * model : Android 4.4.2
     * factory : XiaoMi
     * screen_size : 1920*1080
     * denstiy : 2.0
     * imei : 352105068965016/01
     * mac : 50-8D-4F-4S-55-5D
     * gprs : 4G
     * latitude : 39.972907
     * longitude : 116.503154
     */

    private DeviceBean device;

    private TokenCheckBean token_check;


    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public TokenCheckBean getToken_check() {
        return token_check;
    }

    public void setToken_check(TokenCheckBean token_check) {
        this.token_check = token_check;
    }

    public VerifyInfo(AppBean app, DeviceBean device, TokenCheckBean token_check) {
        this.app = app;
        this.device = device;
        this.token_check = token_check;
    }

    public VerifyInfo() {
    }


}
