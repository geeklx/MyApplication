package com.haiersmart.sfnation.common;


public class AppActionActivity {

    private String className;
    private boolean isOpen;

    public AppActionActivity() {
    }

    public AppActionActivity(String className, boolean isOpen) {
        this.className = className;
        this.isOpen = isOpen;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
