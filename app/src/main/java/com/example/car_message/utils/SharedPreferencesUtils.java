package com.example.car_message.utils;

import android.content.SharedPreferences;


public class SharedPreferencesUtils {

  /**
   * 退出登录时，删除sp记录
   */
  public static void clearSp(){
//    SharedPreferences sp = KnysClApplication.getInstance().instanceSp();
//    String userNameStr = sp.getString("username", "");
//    String loginLogo = sp.getString("loginLogo","");
//    String imgsever = sp.getString("imgsever","");
//    String mallId = sp.getString("mallId","");
//    SharedPreferences.Editor editor = KnysClApplication.getInstance().instanceSp().edit();
//    editor.clear();
//    editor.apply();
//    editor.putString("username", userNameStr);
//    editor.putString("mallId", mallId);
//    editor.putString("loginLogo",loginLogo);
//    editor.putString("imgsever",imgsever);
//    editor.commit();
  }


  /**
   * 清除购物车选中的记录
   */
  public static void clearCartSelectedKeys(){
 //   KnysClApplication.getInstance().instanceSp().edit().putString("selectedCartItemKeys", "").apply();
  }

}
