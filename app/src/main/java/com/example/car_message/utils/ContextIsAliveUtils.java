package com.example.car_message.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class ContextIsAliveUtils {

  public static boolean isContextExisted(Context mContext){
    if(mContext!=null){
      if(mContext instanceof Activity){
        if(!((Activity)mContext).isFinishing()){
          return true;
        }
      }else if(mContext instanceof Application){
        return true;
      }
    }
    return false;
  }


}
