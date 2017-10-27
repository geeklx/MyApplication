package com.example.shining.p044_wechat_record.audiomanagerset.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by shining on 2017/10/16.
 */
@Entity
public class Recorder {
    @Id
    private Long id;
    public float time;
    public String filePath;
    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public float getTime() {
        return this.time;
    }
    public void setTime(float time) {
        this.time = time;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 845739284)
    public Recorder(Long id, float time, String filePath) {
        this.id = id;
        this.time = time;
        this.filePath = filePath;
    }
    @Generated(hash = 1244939113)
    public Recorder() {
    }


}
