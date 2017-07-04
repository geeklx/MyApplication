package com.haiersmart.voice.bean;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/10 19:59
 */

public class BaiduBaseResultBean {

    /**
     * status : 0
     * msg : ok
     * se_query : 我要听周杰伦的歌
     * logid : xxxx1234354
     * id : id12334235
     * user_id :
     * cuid :
     * time : 1482226019
     */

    private int status;
    private String msg;
    private String se_query;
    private String logid;
    private String id;
    private String user_id;
    private String cuid;
    private int time;
    private String domainType;
    private String intentType;

    public String getDomain() {
        return domainType;
    }

    public void setDomain(String domain) {
        this.domainType = domain;
    }

    public String getIntent() {
        return intentType;
    }

    public void setIntent(String intent) {
        this.intentType = intent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSe_query() {
        return se_query;
    }

    public void setSe_query(String se_query) {
        this.se_query = se_query;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
