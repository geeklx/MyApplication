package com.example.p010_recycleviewall.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by geek on 2016/4/6.
 */
public class PackageOneKeyBuyBeanNewList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PackageOneKeyBuyBeanNew> packageOneKeyBuyBeanNew;

    public PackageOneKeyBuyBeanNewList() {
    }

    public PackageOneKeyBuyBeanNewList(List<PackageOneKeyBuyBeanNew> packageOneKeyBuyBeanNew) {
        this.packageOneKeyBuyBeanNew = packageOneKeyBuyBeanNew;
    }

    public List<PackageOneKeyBuyBeanNew> getPackageOneKeyBuyBeanNew() {
        return packageOneKeyBuyBeanNew;
    }

    public void setPackageOneKeyBuyBeanNew(List<PackageOneKeyBuyBeanNew> packageOneKeyBuyBeanNew) {
        this.packageOneKeyBuyBeanNew = packageOneKeyBuyBeanNew;
    }
}
