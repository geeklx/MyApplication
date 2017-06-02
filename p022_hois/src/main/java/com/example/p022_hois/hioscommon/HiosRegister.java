package com.example.p022_hois.hioscommon;

import com.example.p022_hois.hoisjump.HiosAlias;

public class HiosRegister {

    private static final String PKG_SFNATION = "com.example.p022_hois";

    public static void load() {

        HiosAlias.register("jump.twomainactivity", PKG_SFNATION, ".activity.TwoMainActivity");
        HiosAlias.register("jump.webviewmainactivity", PKG_SFNATION, ".activity.WebViewMainActivity");

    }
}
