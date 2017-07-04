package com.haiersmart.voice.bean.event;

/**
 * Created with Android Studio.
 *
 * @author : Hsueh
 * @email : i@hsueh.top
 * @date : 2017-03-02 21:42
 */
public class FoodManagerEvent {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FoodManagerEvent(String id) {
        this.id = id;
    }
}
