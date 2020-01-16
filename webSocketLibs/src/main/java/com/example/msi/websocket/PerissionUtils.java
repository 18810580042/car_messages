package com.example.msi.websocket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;


/**
 * Created by 郭金龙
 * on 2018/4/12 10:09
 */

public class PerissionUtils {
    //检查系统是否关闭app应用的通知权限
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {
       if(context!=null&&context.getApplicationContext()!=null){
           NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context.getApplicationContext());
           return managerCompat.areNotificationsEnabled();
       }
       return true;
    }


    public static void goToSet(Context mContext){
        Intent localIntent = new Intent();
        //直接跳转到应用通知设置的代码：
        localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",mContext.getPackageName(),null);
        localIntent.setData(uri);
        mContext.startActivity(localIntent);
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
//            localIntent.putExtra("app_package", mContext.getPackageName());
//            localIntent.putExtra("app_uid", mContext.getApplicationContext().getApplicationInfo().uid);
//        } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
//            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            localIntent.addCategory(Intent.CATEGORY_DEFAULT);
//            localIntent.setData(Uri.parse("package:" + mContext.getPackageName()));
//        } else {
//            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
//            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//            localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
//        }
    }


}
