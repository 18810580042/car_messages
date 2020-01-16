package com.example.msi.websocket.websocket.common;

/**
 * Created by Administrator on 2018/5/18.
 */

public class UrlsUtils {
    //websocketIM
//    public static final String LOCAL = "ws://192.168.1.12:89/im";
//    public static final String LOCAL = "ws://192.168.1.111:89/im";
//    public static final String LOCAL = "ws://192.168.0.40:89/im";
 //   public static final String LOCAL = "ws://118.89.237.254:89/im";

    public static final String LOCAL = "ws://119.90.43.75:89/im";
    /**
     * HOST
     * UPLOADIMAGE 图片上传
     * SHOWHISTORY 历史消息
     * SHOWLINK    消息列表
     * SYNCHRlINK 同步消息列表
     */
//    public static final String HOST = "http://192.168.1.12:8088/";
//    public static final String HOST = "http://192.168.0.40:8088/";
//   public static final String HOST = "http://im.knysedu.com/";
    public static final String HOST = "http://im.cnknys.com/";
//    public static final String HOST = "http://119.90.43.75:89/im/";
    public static final String UPLOADIMAGE = HOST + "uploadImage";
    public static final String SHOWHISTORY = HOST + "showIMHistory";
    public static final String SHOWLINK = HOST + "showLink";
    public static final String SYNCHRlINK = HOST + "synchrsLink";
}
