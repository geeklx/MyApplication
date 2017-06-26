package com.shining.p024_wifidemoactivity.domain;

import org.loader.opendroid.db.OpenDroid;

import java.io.Serializable;

/**
 * Created by qibin on 2016/6/3.
 */
public class Wifi extends OpenDroid implements Serializable {
    private String ssid;
    private String capabilities;
    private String password;

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
