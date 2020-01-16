package com.example.msi.websocket;

/**
 * Created by 郭金龙
 * on 2018/4/16 11:16
 */

public class messag {

    /**
     * cmd : REVERT
     * content : 登录成功
     * messageType : null
     * messageid :
     * productId :
     * receiver :
     * receiverId :
     * resultCode : UNSENT
     * sender :
     * senderId : 789
     * time : 1523848464075
     */

    private String cmd;
    private String content;
    private Object messageType;
    private String messageid;
    private String productId;
    private String receiver;
    private String receiverId;
    private String resultCode;
    private String sender;
    private String senderId;
    private long time;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getMessageType() {
        return messageType;
    }

    public void setMessageType(Object messageType) {
        this.messageType = messageType;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
