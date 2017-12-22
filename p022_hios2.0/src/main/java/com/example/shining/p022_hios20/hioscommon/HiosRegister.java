package com.example.shining.p022_hios20.hioscommon;


import com.example.shining.p022_hios20.hois2.HiosAlias;

public class HiosRegister {

    private static final String PKG_SFNATION = "com.example.shining.p022_hios20";

    public static void load() {

        HiosAlias.register("jump.HiosMainActivity", PKG_SFNATION, ".activity.HiosMainActivity");
//        HiosAlias.register("jump.webviewmainactivity", PKG_SFNATION, ".base.WebViewMainActivity");

    }
}
