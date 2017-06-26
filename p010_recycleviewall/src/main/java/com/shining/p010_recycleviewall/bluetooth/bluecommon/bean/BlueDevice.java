package com.shining.p010_recycleviewall.bluetooth.bluecommon.bean;

import android.bluetooth.BluetoothDevice;

public class BlueDevice {
    private String name;
    private String address;
    private BluetoothDevice device;
    private int type;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlueDevice that = (BlueDevice) o;

        if (!name.equals(that.name)) return false;
        return address.equals(that.address);

    }

//    @Override
//    public int hashCode() {
//        int result = name.hashCode();
//        result = 31 * result + address.hashCode();
//        return result;
//    }
}
