package com.example.car_message.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import com.example.car_message.activity.LoginActivity;


public class ActivityUtils {

  /**
   * 判断处于前台的是不是聊天界面，如果不是，会弹窗
   */
  public static boolean isTopActivity(Context mContext) {
    boolean isTop = false;
    ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
    if (cn.getClassName().contains(LoginActivity.class.getName())) {
      isTop = true;
    }
    return isTop;
  }


  /**
   * 打开h5商品列表
   *
   * @param mContext 上下文
   * @param diid diid
   * @param storeDbsid dbsid
   */
//  public static void startH5DrugDetail(Context mContext, String diid, String storeDbsid) {
//    Intent intent = new Intent(mContext, NativeWebViewActivity.class);
//    intent.putExtra("jsUrl", "/goods/view/" + diid + "?storeDbsid=" + storeDbsid);
//    intent.putExtra("isOpenHeadRefresh", true);
//    mContext.startActivity(intent);
//  }


  /**
   * 判断处于前台的是不是聊天界面，如果不是，会弹窗
   */
//  public static boolean isCanReturnActivity(Context mContext) {
//    boolean isTop = false;
//    ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
//    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//    if (cn.getClassName().contains(NativeWebViewActivity.class.getName()) || cn.getClassName()
//        .contains(NewCouponActivity.class.getName())
//        || cn.getClassName().contains(NewOrderDetailActivity.class.getName()) || cn.getClassName()
//        .contains(OrderMessageActivity.class.getName())) {
//      isTop = true;
//    }
//    return isTop;
//  }


  /**
   * 判断处于前台的是不是聊天界面，如果不是，会弹窗
   */
//  public static boolean isHaveMainActivity(Context mContext) {
//    boolean isTop = false;
//    List<Activity> mlist = KnysClApplication.getInstance().getActivityList();
//    if (mlist != null && mlist.size() > 0) {
//      for (Activity a : mlist) {
//        if (a.getClass().getName().contains("MainActivity")) {
//          isTop = true;
//        }
//      }
//    }
//    return isTop;
//  }

  public static boolean thisActivityIsTop(Activity mContext) {
    boolean isTop = false;
    ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
    if (cn.getClassName().contains(mContext.getClass().getName())) {
      isTop = true;
    }
    return isTop;
  }

//  public static void messageWakeUpApp(SplashActivity mContext, Intent intent) {
//    Uri uri = intent.getData();
//    uri.getScheme();//获取scheme
//    uri.getHost();//获取host
//    uri.getAuthority();//获取authority
//    String params = uri.getQueryParameter("params");
//    //标题转UTF-8码
//    if (!android.text.TextUtils.isEmpty(params)) {
//      try {
//        params = URLDecoder.decode(params, "UTF-8");
//        PushMessageBean pushMessageBean = GsonUtils.JsonToBean(params, PushMessageBean.class);
//        if (pushMessageBean != null && pushMessageBean.getAction() != null && !""
//            .equals(pushMessageBean.getAction())) {
//          //打开h5
//          if ("1".equals(pushMessageBean.getAction())) {
//            VariateUtils.isMessageWakeUpApp = true;
//            Intent intent1 = new Intent(mContext, NativeWebViewActivity.class);
//            if (!android.text.TextUtils.isEmpty(pushMessageBean.getUrl())) {
//              intent1.putExtra("jsUrl", pushMessageBean.getUrl());
//            }
//            mContext.startActivity(intent1);
//            mContext.finish();
//            return;
//            //打开优惠券
//          } else if ("2".equals(pushMessageBean.getAction())) {
//            VariateUtils.isMessageWakeUpApp = true;
//            Intent intent1 = new Intent(mContext, NewCouponActivity.class);
//            mContext.startActivity(intent1);
//            mContext.finish();
//            return;
//            //打开订单详情
//          } else if ("3".equals(pushMessageBean.getAction())) {
//            VariateUtils.isMessageWakeUpApp = true;
//            Intent intent1 = new Intent(mContext, NewOrderDetailActivity.class);
//            if (!android.text.TextUtils.isEmpty(pushMessageBean.getOrderCode())) {
//              intent1.putExtra("orderCode", pushMessageBean.getOrderCode());
//            }
//            mContext.startActivity(intent1);
//            mContext.finish();
//            return;
//          } else if ("4".equals(pushMessageBean.getAction())) {
//            //打开订单消息
//            VariateUtils.isMessageWakeUpApp = true;
//            Intent intent1 = new Intent(mContext, OrderMessageActivity.class);
//            mContext.startActivity(intent1);
//            mContext.finish();
//            return;
//          }
//        }
//      } catch (UnsupportedEncodingException e) {
//        e.printStackTrace();
//      }
//      Intent intent2 = new Intent(mContext, NewLoginActivity.class);
//      mContext.startActivity(intent2);
//      mContext.finish();
//    }
//  }
}
