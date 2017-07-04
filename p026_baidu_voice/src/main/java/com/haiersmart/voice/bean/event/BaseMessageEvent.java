package com.haiersmart.voice.bean.event;

/**
 * EventBus消息传递 基类
 * Created by JefferyLeng on 2017/1/3.
 */
public class BaseMessageEvent {

    private String message;

    public BaseMessageEvent() {
    }

    public BaseMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
