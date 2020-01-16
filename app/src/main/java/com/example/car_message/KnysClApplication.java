package com.example.car_message;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
//import android.os.StrictMode;
//import android.os.StrictMode.VmPolicy.Builder;
//import android.support.annotation.RequiresApi;
//import android.support.multidex.MultiDex;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.NotificationCompat;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import androidx.annotation.RequiresApi;
//import cn.finalteam.galleryfinal.FunctionConfig;
//import com.cnknys.knysclas.activity.GuideActivity;
//import com.cnknys.knysclas.activity.SplashActivity;
//import com.cnknys.knysclas.base.BaseActivity;
//import com.cnknys.knysclas.bean.ChoseStoreBean.ChoseStoreData;
//import com.cnknys.knysclas.bean.GenerateOrderBean;
//import com.cnknys.knysclas.bean.NewVersionBean;
//import com.cnknys.knysclas.callback.CommontCallback;
//import com.cnknys.knysclas.sdk.chat.activity.ChatActivity;
//import com.cnknys.knysclas.sdk.chat.activity.SystemType;
//import com.cnknys.knysclas.sdk.chat.model.MessageCon;
//import com.cnknys.knysclas.sdk.chat.model.imbean.ListUserInfo;
//import com.cnknys.knysclas.http.HttpMoudel;
//import com.cnknys.knysclas.utils.ContextIsAliveUtils;
//import com.cnknys.knysclas.utils.PackageInfoUtils;
//import com.cnknys.knysclas.utils.VersionCompareUtils;
//import com.cnknys.knysclas.view.RudenessScreenHelper;
import com.example.msi.websocket.callback.ForegroundCallbacks;
import com.example.msi.websocket.callback.MessageListener;
import com.example.msi.websocket.websocket.WsManager;
import com.example.msi.websocket.websocket.common.ICallback;
import com.example.msi.websocket.websocket.common.MessageBean;
import com.example.msi.websocket.websocket.request.Action;
//import com.orhanobut.logger.Logger;
//import com.tencent.bugly.crashreport.CrashReport;
//import com.tencent.smtt.sdk.QbSdk;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
//import util.UpdateAppUtils;

/**
 * Created by KNYS on 2016/11/18.
 */
public class KnysClApplication extends Application {

  private static KnysClApplication instance;
  private static Application context;
 // private FunctionConfig functionConfig;
  int NOTIFY_ID = 1;
  private String orderCode;
  public static WsManager wsManager;
 // public static ListUserInfo listUserInfo;
//  public BaseActivity activity;
  private int mCount;
  private static String[] PERMISSIONS_STORAGE = {
      permission.READ_EXTERNAL_STORAGE,
      permission.WRITE_EXTERNAL_STORAGE,
      permission.CAMERA};
  private static final int REQUEST_EXTERNAL_STORAGE = 1;
  AlertDialog dialog;
//  public static List<ChoseStoreData> data;
//  public static List<GenerateOrderBean.DataBean.CouponlistBean> list;
//  public static List<GenerateOrderBean.DataBean.CouponlistBean> nolist;

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @Override
  public void onCreate() {
      //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
//
//    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//      @Override
//      public void onViewInitFinished(boolean arg0) {
//        //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//        Log.d("app", " onViewInitFinished is " + arg0);
//      }
//
//      @Override
//      public void onCoreInitFinished() {
//      }
//    };
//    //x5内核初始化接口
//    QbSdk.initX5Environment(getApplicationContext(),  cb);
//
//    MultiDex.install(this);
//    super.onCreate();
//    new RudenessScreenHelper(this, 750).activate();
//    //    CrashHandler.getInstance().init(this);
//    context = KnysClApplication.getInstance();
//    CrashReport.initCrashReport(getApplicationContext(), "2a44d521ee", false);
//    instance = this;
//    regain();
//    Builder builder = new Builder();
//    StrictMode.setVmPolicy(builder.build());
//    builder.detectFileUriExposure();
//
//    //配置功能
//    functionConfig = new FunctionConfig.Builder()
//        .setEnableCamera(true)
//        .setEnableEdit(true)
//        .setEnableCrop(true)
//        .setEnableRotate(true)
//        .setCropSquare(true)
//        .setEnablePreview(true)
//        .build();
//
//    wsManager = WsManager.getInstance(instance);
//    setMessageListener();
//    initAppStatusListener();
      super.onCreate();
  }

  public static KnysClApplication getInstance() {
    if (null == instance) {
      instance = new KnysClApplication();
    }
    return instance;
  }

  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

  public static List<Activity> mList = new LinkedList<Activity>();


  // add Activity
  public void addActivity(Activity activity) {
    mList.add(activity);
  }

  public int getCount() {
    return mList.size();
  }

  public List<Activity> getActivityList(){
    return mList;
  }

  public void removeActiivty(Activity activity){
    mList.remove(activity);
  }

  public void clearList(){
    mList.clear();
  }

  /**
   * 设置消息推送监听
   */
//  private void setMessageListener() {
//    wsManager.getListener(new MessageListener() {
//      @Override
//      public void messageListener(final MessageBean.DataBean message) {
//        String content = message.getContent();
//        Log.i("content", content);
//        Intent intent1 = new Intent("dataModel");
//        intent1.putExtra("dataModel", message);
//        sendBroadcast(intent1);
//        if (!message.getCmd().equals("REVERT")) {
//          if (!isTopActivity()) {
//            if (message.getCmd().equals(MessageCon.REVERT) || message.getCmd()
//                .equals(MessageCon.CHAT)) {
//              Intent openintent = new Intent(context, ChatActivity.class);
//              String content1 = message.getContent();
//              String cmd = message.getCmd();
//              String messageType = message.getMessageType();
//              long time = message.getTime();
//              String senderId = message.getSenderId();
//              String receiverId = message.getReceiverId();
//              openintent.putExtra("content", content1);
//              openintent.putExtra("cmd", cmd);
//              openintent.putExtra("messageType", messageType);
//              openintent.putExtra("time", time + "");
//              openintent.putExtra("senderId", senderId);
//              openintent.putExtra("receiverId", receiverId);
//              openintent.putExtra("userId", receiverId);
//              openintent.putExtra("userID", senderId);
//              openintent
//                  .putExtra("userName", listUserInfo.getData().getAgentList().get(0).getUserName());
//              Log.i("AppID---",
//                  senderId + "---" + receiverId);//0001---b0fb84d3be79485ca435de3a625c6272
//              Intent intent2 = new Intent("dataModel1");
//              intent2.putExtra("dataModel1", message);
//              sendBroadcast(intent1);
//              PendingIntent pendingIntent = PendingIntent.getActivity(context, 11,
//                  openintent, PendingIntent.FLAG_UPDATE_CURRENT);
//              NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
//              mBuilder.setSmallIcon(android.R.drawable.ic_lock_power_off);
//              mBuilder.setPriority(2);
//              mBuilder.setContentTitle("提示");
//              mBuilder.setAutoCancel(true);
//              mBuilder.setContentIntent(pendingIntent);
//              try {
//                String result = new String(messageType.getBytes("iso-8859-1"), "UTF-8");
//                String resultback = new String("VIEW".getBytes("iso-8859-1"), "UTF-8");
//                String goodId = new String("GOODS".getBytes("iso-8859-1"), "UTF-8");
//                if (result.equals(resultback)) {
//                  mBuilder.setContentText("[图片]");
//                } else if (result.equals(goodId)) {
//                  mBuilder.setContentText("[商品类型]");
//                } else {
//                  mBuilder.setContentText(message.getContent());
//                }
//              } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//              }
//              mBuilder.setFullScreenIntent(pendingIntent, false);
//              NotificationManager mNotifyManager = (NotificationManager) context.getSystemService
//                  (Context.NOTIFICATION_SERVICE);
//              int id = NOTIFY_ID++;
//              mNotifyManager.notify("1234", id, mBuilder.build());
//              try {
//                Thread.sleep(3000);
//              } catch (InterruptedException e) {
//                e.printStackTrace();
//              }
//              mNotifyManager.cancel(id);
//            }
//          } else {
//            Intent intent = new Intent("message");
//            intent.putExtra("message", message);
//            sendBroadcast(intent);
//          }
//        }
////        else{
////          Intent intent = new Intent("message");
////          intent.putExtra("message", message);
////          sendBroadcast(intent);
////        }
//      }
//    });
//  }
//
//  /**
//   * 初始化应用前后台状态监听
//   */
//  private void initAppStatusListener() {
//    ForegroundCallbacks.init(this).addListener(new ForegroundCallbacks.Listener() {
//      @Override
//      public void onBecameForeground() {
//        Logger.t("WsManager").d("应用回到前台调用重连方法");
//        wsManager.reconnect();
//      }
//
//      @Override
//      public void onBecameBackground() {
//
//      }
//    });
//  }
//
//
//  public static Context getContext() {
//    return context;
//  }
//
//  /**
//   * 判断处于前台的是不是聊天界面，如果不是，会弹窗
//   */
//  private boolean isTopActivity() {
//    boolean isTop = false;
//    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//    if (cn.getClassName().contains(ChatActivity.class.getName())) {
//      isTop = true;
//    }
//    return isTop;
//  }
//
//  /**
//   * 判断处于前台的是不是过度动画界面，如果不是，会弹窗
//   */
//  private boolean isTopSplashActivity() {
//    boolean isTop = false;
//    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//    if (cn.getClassName().contains(SplashActivity.class.getName())) {
//      isTop = true;
//    }
//    return isTop;
//  }
//
//  private boolean isTopGuideActivity() {
//    boolean isTop = false;
//    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//    if (cn.getClassName().contains(GuideActivity.class.getName())) {
//      isTop = true;
//    }
//    return isTop;
//  }
//
//  /**
//   * 程序终止的时候执行
//   */
//  @Override
//  public void onTerminate() {
//    super.onTerminate();
//    KnysClApplication.wsManager.sendChatReqRev(Action.LOGOUT,
//        KnysClApplication.listUserInfo.getData().getUserInfo().getUserId(), "", "",
//        "", "", SystemType.GK, new ICallback() {
//          @Override
//          public void onSuccess(Object o) {
//
//          }
//
//          @Override
//          public void onFail(String msg) {
//
//          }
//        });
//    KnysClApplication.wsManager.disconnect();
//  }
//
//  public void regain() {
//    registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//      @Override
//      public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//
//      }
//
//      @Override
//      public void onActivityStarted(Activity activity1) {
//        mCount++;
//        //如果mCount==1，说明是从后台到前台
//        if (mCount == 1) {
//          //执行app跳转到前台的逻辑
//          if (!isTopSplashActivity() && !isTopGuideActivity()&&ContextIsAliveUtils.isContextExisted(activity)) {
//            verifyStoragePermissions(activity);
//            checkVerison();
//          }
//        }
//      }
//
//      @Override
//      public void onActivityResumed(Activity activity) {
//
//      }
//
//      @Override
//      public void onActivityPaused(Activity activity) {
//
//      }
//
//      @Override
//      public void onActivityStopped(Activity activity) {
//        mCount--;
//        //如果mCount==0，说明是前台到后台
//        if (mCount == 0) {
//          //执行应用切换到后台的逻辑
//          if (instance != null && dialog != null&&ContextIsAliveUtils.isContextExisted(activity)) {
//            dialog.dismiss();
//          }
//        }
//      }
//
//      @Override
//      public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//      }
//
//      @Override
//      public void onActivityDestroyed(Activity activity) {
//
//      }
//    });
//  }
//
//  /**
//   * 申请存储权限
//   *
//   * @param activity 上下文
//   */
//  public static void verifyStoragePermissions(AppCompatActivity activity) {
//    if(ContextIsAliveUtils.isContextExisted(activity)){
//      // Check if we have write permission
//      int permission = ActivityCompat.checkSelfPermission(activity,
//          Manifest.permission.WRITE_EXTERNAL_STORAGE);
//      if (permission != PackageManager.PERMISSION_GRANTED) {
//        // We don't have permission so prompt the user
//        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
//            REQUEST_EXTERNAL_STORAGE);
//      }
//    }
//  }
//
//  /**
//   * 检测版本号
//   */
//  private void checkVerison() {
//    try {
//      final String versionName = PackageInfoUtils
//          .getPackageVersion(KnysClApplication.getInstance());
//      HttpMoudel
//          .LoadNewVersion(SplashActivity.class.getName(), new CommontCallback<NewVersionBean>() {
//            @Override
//            public void onSuccess(final NewVersionBean result) {
//              if (!VersionCompareUtils
//                  .Compare(versionName, result.getVersion())) {
//                final String apkFile;
//                if (result.getDownload() != null && !"".equals(result.getDownload())) {
//                  apkFile = result.getDownload();
//                } else {
//                  apkFile =
//                      "http://img.cnknys.com/app/" + result.getId() + "/" + result.getVersion()
//                          + "/"
//                          + "KnysCl.apk";
//                }
//                if (result.isRequired()) {
//                  if (ContextIsAliveUtils.isContextExisted(activity)) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(
//                        activity);
//                    builder.setCancelable(false);
//                    builder.setTitle("提示");
//                    builder.setMessage("是否更新");
//                    builder.setNegativeButton("确定", new OnClickListener() {
//                      @Override
//                      public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//
//                        UpdateAppUtils.from(activity)
//                            .isNecessary(result.isRequired())
//                            .serverVersionName(result.getVersion())
//                            .apkPath(apkFile)
//                            .updateInfo(result.getChangelog())
//                            .update();
//
//                      }
//                    });
//                    dialog = builder.create();
//                    dialog.show();
//                  }
//                } else {
//                  if (ContextIsAliveUtils.isContextExisted(activity)) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(
//                        activity);
//                    builder.setTitle("提示");
//                    builder.setMessage("是否更新");
//                    builder.setNegativeButton("确定", new OnClickListener() {
//                      @Override
//                      public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//
//                        UpdateAppUtils.from(activity)
//                            .isNecessary(result.isRequired())
//                            .serverVersionName(result.getVersion())
//                            .apkPath(apkFile)
//                            .updateInfo(result.getChangelog())
//                            .update();
//
//                      }
//                    });
//                    builder.setPositiveButton("取消", new OnClickListener() {
//                      @Override
//                      public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                      }
//                    });
//                    dialog = builder.create();
//                    if (instance != null) {
//                      dialog.show();
//                    }
//                  }
//                }
//              }
//            }
//
//            @Override
//            public void onBefore() {
//
//            }
//
//            @Override
//            public void onError(String errorMsg) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//          });
//    } catch (Exception e) {
//      Log.e("更新", e.toString());
//    }
//  }
//
//  /**
//   * 获取sp
//   */
  private SharedPreferences sp;
//
  /**
   * sp单例
   *
   * @return sp
   */
  public SharedPreferences instanceSp() {
    if (sp == null) {
      sp = KnysClApplication.getInstance().getSharedPreferences("login", Context.MODE_PRIVATE);
    }
    return sp;
  }
//
//  /**
//   * 适配字体
//   * @param newConfig
//   */
//  @Override
//  public void onConfigurationChanged(Configuration newConfig) {
//    if (newConfig.fontScale != 1) {
//      getResources();
//    }
//    super.onConfigurationChanged(newConfig);
//  }
//  /**
//   * 适配字体
//   */
//  @Override
//  public Resources getResources() {
//    Resources res = super.getResources();
//    if (res.getConfiguration().fontScale != 1) {
//      Configuration newConfig = new Configuration();
//      newConfig.setToDefaults();
//      res.updateConfiguration(newConfig, res.getDisplayMetrics());
//    }
//    return res;
//  }
}
