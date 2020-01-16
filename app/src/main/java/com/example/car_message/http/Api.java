package com.example.car_message.http;

public class Api {

    //测试新环境
    public static String URL_ROOT = "http://cl.knysedu.com";
    //请求基础地址拼接成app接口地址
    public static final String URL_SERVER = URL_ROOT + "/api/";
    //新登录接口
    public static final String NewLogin = URL_SERVER + "signIn";
    //设置新密码
    public static final String messageLogin = URL_SERVER + "signInSms";
    //登录
    public static final String newLogin = URL_ROOT + "/login";
}
