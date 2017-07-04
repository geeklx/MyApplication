package com.haiersmart.voice.bean.event;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/16 22:46
 */

public class CloseEvent {
    private boolean close ;

    public CloseEvent(boolean close) {
        this.close = close;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
}
