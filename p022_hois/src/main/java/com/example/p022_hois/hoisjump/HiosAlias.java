/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.p022_hois.hoisjump;

import android.util.Pair;

import java.util.HashMap;

/**
 * <p class="note">File Note</p>
 * created by qibin at 2017/5/8 
 */
public class HiosAlias {
    // Map<alias, Pair<packageName, className>>
    private static HashMap<String, Pair<String, String>> sAlias = new HashMap<>();

    public static void register(String name, String packageName, String target) {
        sAlias.put(name, Pair.create(packageName, target));
    }

    public static Pair<String, String> getByName(String name) {
        return sAlias.get(name);
    }
}
