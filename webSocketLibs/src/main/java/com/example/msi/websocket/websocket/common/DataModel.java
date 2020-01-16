package com.example.msi.websocket.websocket.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class DataModel implements Serializable{

    public DataModel(DataBean data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    /**
     * data : {"myMessage":[{"userID":"tttttttttt","userName":"","date":null,"message":"",
     * "messageCount":"","avatarURL":""},{"userID":"tttttttttt","userName":"",
     * "date":null,"message":"","messageCount":"","avatarURL":""}]}
     * message : 成功
     * success : true
     */

    private DataBean data;
    private String message;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        private List<MyMessageBean> myMessage;

        public List<MyMessageBean> getMyMessage() {
            return myMessage;
        }

        public void setMyMessage(List<MyMessageBean> myMessage) {
            this.myMessage = myMessage;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "myMessage=" + myMessage +
                    '}';
        }

        public static class MyMessageBean implements Serializable{
            public MyMessageBean(String userID, String userName, Object date, String message,
                                 String messageCount, String avatarURL,String messageType) {
                this.userID = userID;
                this.userName = userName;
                this.date = date;
                this.message = message;
                this.messageCount = messageCount;
                this.avatarURL = avatarURL;
                this.messageType=messageType;
            }

            /**
             * userID : tttttttttt
             * userName :
             * date : null
             * message :
             * messageCount :
             * avatarURL :
             */
            private String messageType;
            private String userID;
            private String userName;
            private Object date;
            private String message;
            private String messageCount;
            private String avatarURL;
            private String onLine;
            private String messageEnum;

            public String getMessageEnum() {
                return messageEnum;
            }

            public void setMessageEnum(String messageEnum) {
                this.messageEnum = messageEnum;
            }

            public String getOnLine() {
                return onLine;
            }

            public void setOnLine(String onLine) {
                this.onLine = onLine;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getUserID() {
                return userID;
            }

            public void setUserID(String userID) {
                this.userID = userID;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public Object getDate() {
                return date;
            }

            public void setDate(Object date) {
                this.date = date;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMessageCount() {
                return messageCount;
            }

            public void setMessageCount(String messageCount) {
                this.messageCount = messageCount;
            }

            public String getAvatarURL() {
                return avatarURL;
            }

            public void setAvatarURL(String avatarURL) {
                this.avatarURL = avatarURL;
            }

            @Override
            public String toString() {
                return "MyMessageBean{" +
                        "messageType='" + messageType + '\'' +
                        ", userID='" + userID + '\'' +
                        ", userName='" + userName + '\'' +
                        ", date=" + date +
                        ", message='" + message + '\'' +
                        ", messageCount='" + messageCount + '\'' +
                        ", avatarURL='" + avatarURL + '\'' +
                        ", onLine='" + onLine + '\'' +
                        '}';
            }
        }

    }

    @Override
    public String toString() {
        return "DataModel{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
