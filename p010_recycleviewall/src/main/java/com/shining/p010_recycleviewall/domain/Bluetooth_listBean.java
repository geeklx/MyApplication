package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Bluetooth_listBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private String bluetooth_type;
    private String bluetooth_name;
    private String bluetooth_address;

    public Bluetooth_listBean() {
    }

    public Bluetooth_listBean(String bluetooth_type, String bluetooth_name, String bluetooth_address) {
        this.bluetooth_type = bluetooth_type;
        this.bluetooth_name = bluetooth_name;
        this.bluetooth_address = bluetooth_address;
    }

    public String getBluetooth_type() {
        return bluetooth_type;
    }

    public void setBluetooth_type(String bluetooth_type) {
        this.bluetooth_type = bluetooth_type;
    }

    public String getBluetooth_name() {
        return bluetooth_name;
    }

    public void setBluetooth_name(String bluetooth_name) {
        this.bluetooth_name = bluetooth_name;
    }

    public String getBluetooth_address() {
        return bluetooth_address;
    }

    public void setBluetooth_address(String bluetooth_address) {
        this.bluetooth_address = bluetooth_address;
    }
}
