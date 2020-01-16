package com.example.msi.websocket.websocket.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 郭金龙
 * on 2018/4/12 15:13
 */

public class MessageBean implements Serializable {

    /**
     * code : 200
     * message : SUCCESS
     * success : true
     * data : [{"id":"5c9af0d4dec9eb26684c069a","cmd":"CHAT","time":1553658068871,"senderId":"d82ab84ad9c945e69e3675c16c7c0886","receiverId":"24e9ee506c3d4fe1ba4f8b6c63c67067","sender":"王石春","receiver":"荣盛","messageType":"TEXT","productId":null,"content":"55555","messageid":null,"sysType":"KNYS","resultStatus":"UNSENT"},{"id":"5c9af0d2dec9eb26684c0699","cmd":"CHAT","time":1553658066366,"senderId":"d82ab84ad9c945e69e3675c16c7c0886","receiverId":"24e9ee506c3d4fe1ba4f8b6c63c67067","sender":"王石春","receiver":"荣盛","messageType":"TEXT","productId":null,"content":"11111111","messageid":null,"sysType":"KNYS","resultStatus":"UNSENT"}]
     * attachData : null
     */

    private int code;
    private String message;
    private boolean success;
    private Object attachData;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
        this.attachData = attachData;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{

        /**
         * id : 5c9af0d4dec9eb26684c069a
         * cmd : CHAT
         * time : 1553658068871
         * senderId : d82ab84ad9c945e69e3675c16c7c0886
         * receiverId : 24e9ee506c3d4fe1ba4f8b6c63c67067
         * sender : 王石春
         * receiver : 荣盛
         * messageType : TEXT
         * productId : null
         * content : 55555
         * messageid : null
         * sysType : KNYS
         * resultStatus : UNSENT
         */

        private String id;
        private String cmd;
        private long time;
        private String senderId;
        private String receiverId;
        private String sender;
        private String receiver;
        private String messageEnum;
        private Object productId;
        private String content;
        private String messageid;
        private String sysType;
        private String resultStatus;
        private String failcode;
        private transient int reqCount;
        private String storeId;
        private String messageType;

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageEnum() {
            return messageEnum;
        }

        public void setMessageEnum(String messageEnum) {
            this.messageEnum = messageEnum;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public int getReqCount() {
            return reqCount;
        }

        public void setReqCount(int reqCount) {
            this.reqCount = reqCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMessageid() {
            return messageid;
        }

        public void setMessageid(String messageid) {
            this.messageid = messageid;
        }

        public String getSysType() {
            return sysType;
        }

        public void setSysType(String sysType) {
            this.sysType = sysType;
        }

        public String getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(String resultStatus) {
            this.resultStatus = resultStatus;
        }

        public void setFailcode(String failcode) {
            this.failcode = failcode;
        }

        public String getFailcode() {
            return failcode;
        }
    }
}
