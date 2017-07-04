package com.haiersmart.voice.bean.local_feature_support;

/**
 * 目标业务
 * Created by JefferyLeng on 2017/2/25.
 */
public class TargetBusiness {

    private String className;
    private String operation;

    public TargetBusiness(String className, String operation) {
        this.className = className;
        this.operation = operation;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
