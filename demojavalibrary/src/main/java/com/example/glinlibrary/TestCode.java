package com.example.glinlibrary;

/**
 * Created by Pengliang on 2016/9/2.
 */
public class TestCode {
    public static void main(String[] args) {

      /*  int freezeByte = 0x01;
        int coolByte = 0x02;
        int smartByte = 0x04;
        int cleanByte = 0x08;

        byte a = (byte) (freezeByte | cleanByte);

        System.out.println(String.format("%02x",a));*/

        printbyte(getHexByteFrom10(38));

    }


    public static byte getHexByteFrom10(int i) {
        return (byte) Integer.parseInt(Integer.toHexString(i), 16);
    }

    public static void printbyte(byte a){
        System.out.println(String.format("%02x",a));
    }
}
