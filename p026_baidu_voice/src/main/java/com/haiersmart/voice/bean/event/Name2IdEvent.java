package com.haiersmart.voice.bean.event;

/**
 * Created with Android Studio.
 *
 * @author : Hsueh
 * @email : i@hsueh.top
 * @date : 2017-02-28 09:20
 */
public class Name2IdEvent {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name2IdEvent(String id) {
        this.id = id;
    }

    public Name2IdEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
