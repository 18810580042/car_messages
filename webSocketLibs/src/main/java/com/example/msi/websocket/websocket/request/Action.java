package com.example.msi.websocket.websocket.request;

/**
 * Created by zly on 2017/6/12.
 */

public enum Action {
    LOGIN("LOGIN"),   //登录
    CHAT("CHAT"),     //聊天
    REVERT("REVERT"),  //回执消息类型
    HEARTBEAT("HEARTBEAT"),  //心跳
    ENDCHAT("ENDCHAT"),//关闭聊天窗口
    SYSTEM("SYSTEM"),
    LOGOUT("LOGOUT");
    private String action;

    Action(String type) {
        this.action = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
