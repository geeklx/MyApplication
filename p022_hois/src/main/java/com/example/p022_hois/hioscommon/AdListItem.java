package com.example.p022_hois.hioscommon;

/**
 * Created by qibin on 2016/10/27.
 */

public class AdListItem {
    private String banner;
    private String aid;
    private String url;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AdListItem)) { return false;}
        AdListItem that = (AdListItem) obj;

        if (this.aid == null) {
            if (that.aid != null) { return false;}
        } else {
            if (!this.aid.equals(that.aid)) { return false;}
        }

        if (this.banner == null) {
            if (that.banner != null) { return false;}
        } else {
            if (!this.banner.equals(that.banner)) { return false;}
        }

        if (this.url == null) {
            if (that.url != null) { return false;}
        } else {
            if (!this.url.equals(that.url)) { return false;}
        }

        return true;
    }

    @Override
    public int hashCode() {
        int code = 17;
        if (this.aid != null) {
            code = 31 * code + this.aid.hashCode();
        }

        if (this.url != null) {
            code = 31 * code + this.url.hashCode();
        }

        if (this.banner != null) {
            code = 31 * code + this.banner.hashCode();
        }

        return code;
    }
}
