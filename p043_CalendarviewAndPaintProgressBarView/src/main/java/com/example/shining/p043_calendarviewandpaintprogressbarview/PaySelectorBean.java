package com.example.shining.p043_calendarviewandpaintprogressbarview;

import java.io.Serializable;

public class PaySelectorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean accountIsFlag;
    private String accountBalanceContent;
    private boolean zhenweiIsFlag;
    private boolean zhenweiIsVis;
    private String zhenweiBalanceContent;
    private double paymentAmount;
    private String paymentOrderNo;
    private String zfbPayUrl;
    private String pollingNoZfb;
    private String wxPayUrl;
    private String pollingNoWx;
    private String countDown;
    private String errCode;
    private String errMsg;

    public PaySelectorBean() {
    }

    public PaySelectorBean(boolean accountIsFlag, String accountBalanceContent, boolean zhenweiIsFlag, boolean zhenweiIsVis, String zhenweiBalanceContent, double paymentAmount, String paymentOrderNo, String zfbPayUrl, String pollingNoZfb, String wxPayUrl, String pollingNoWx, String countDown, String errCode, String errMsg) {
        this.accountIsFlag = accountIsFlag;
        this.accountBalanceContent = accountBalanceContent;
        this.zhenweiIsFlag = zhenweiIsFlag;
        this.zhenweiIsVis = zhenweiIsVis;
        this.zhenweiBalanceContent = zhenweiBalanceContent;
        this.paymentAmount = paymentAmount;
        this.paymentOrderNo = paymentOrderNo;
        this.zfbPayUrl = zfbPayUrl;
        this.pollingNoZfb = pollingNoZfb;
        this.wxPayUrl = wxPayUrl;
        this.pollingNoWx = pollingNoWx;
        this.countDown = countDown;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public boolean isAccountIsFlag() {
        return accountIsFlag;
    }

    public void setAccountIsFlag(boolean accountIsFlag) {
        this.accountIsFlag = accountIsFlag;
    }

    public boolean isZhenweiIsVis() {
        return zhenweiIsVis;
    }

    public void setZhenweiIsVis(boolean zhenweiIsVis) {
        this.zhenweiIsVis = zhenweiIsVis;
    }

    public String getAccountBalanceContent() {
        return accountBalanceContent;
    }

    public void setAccountBalanceContent(String accountBalanceContent) {
        this.accountBalanceContent = accountBalanceContent;
    }

    public boolean isZhenweiIsFlag() {
        return zhenweiIsFlag;
    }

    public void setZhenweiIsFlag(boolean zhenweiIsFlag) {
        this.zhenweiIsFlag = zhenweiIsFlag;
    }

    public String getZhenweiBalanceContent() {
        return zhenweiBalanceContent;
    }

    public void setZhenweiBalanceContent(String zhenweiBalanceContent) {
        this.zhenweiBalanceContent = zhenweiBalanceContent;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentOrderNo() {
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(String paymentOrderNo) {
        this.paymentOrderNo = paymentOrderNo;
    }

    public String getZfbPayUrl() {
        return zfbPayUrl;
    }

    public void setZfbPayUrl(String zfbPayUrl) {
        this.zfbPayUrl = zfbPayUrl;
    }

    public String getPollingNoZfb() {
        return pollingNoZfb;
    }

    public void setPollingNoZfb(String pollingNoZfb) {
        this.pollingNoZfb = pollingNoZfb;
    }

    public String getWxPayUrl() {
        return wxPayUrl;
    }

    public void setWxPayUrl(String wxPayUrl) {
        this.wxPayUrl = wxPayUrl;
    }

    public String getPollingNoWx() {
        return pollingNoWx;
    }

    public void setPollingNoWx(String pollingNoWx) {
        this.pollingNoWx = pollingNoWx;
    }

    public String getCountDown() {
        return countDown;
    }

    public void setCountDown(String countDown) {
        this.countDown = countDown;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
