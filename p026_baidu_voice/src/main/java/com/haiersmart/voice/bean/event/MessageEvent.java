package com.haiersmart.voice.bean.event;

/**
 * Created by Hsueh on 2016/12/26.
 */

public class MessageEvent {
    public boolean prepared;
    public int current;

    public MessageEvent(boolean prepared) {
        this.prepared = prepared;
    }
    public MessageEvent(int current){
        this.current = current;
    }

}
