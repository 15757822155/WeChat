package com.hdsh.wechat.entity;

/**
 * Created by Administrator on 2017-04-11 0011.
 */

public class RobotInfo {

    /**
     * code : 100000
     * text : 说谁呢？说得真亲昵～
     */

    private int code;
    private String text;
    private boolean type;

    public RobotInfo(String text, boolean type) {
        this.text = text;
        this.type = type;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
