//package com.example.car_message.enums;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public enum JsToNativeEnums {
//
//  // 商品列表 参数 diid dbsid
//  ACTION_PRODUCT_LIST("PRODUCT_LIST", NewWebViewDrugListActivity.class),
//
//  //打开新的webVIew
//  ACTION_WEBVIEW("标题", NativeWebViewActivity.class),
//  //打开优惠券详情
//  ACTION_COUPON_INFO("COUPON_INFO", NewCouponActivity.class),
//
//  //活动列表
//  ACTION_ACT_LIST("ACT_LIST", NewCouponActivity.class),
//
//  //活动详情
//  ACTION_ACT_INFO("ACT_INFO", NativeWebViewActivity.class),
//
//  //促销分类 参数 groupId
//  ACTION_CLASSIFIED_PROMOTION("ACTIONCLASSIFIEDPROMOTION", ColumnActivity.class),
//  //打开优惠券中心
//  ACTION_COUPON_LIST("COUPON_LIST", NewCouponActivity.class),
//
//  //资质认证
//  ACTION_APPLY_QUALIFICATION("APPLY_QUALIFICATION", NativeWebViewActivity.class),
//  //首营认证
//  ACTION_APPLY_CAMP("APPLY_CAMP", FirstCampDetailActivity.class),
//  //自动登录
//  ACTION_AUTO_LOGIN("AUTO_LOGIN", null),
//  // 保存公司信息成功回调
//  ACTION_SAVE_COMPANY_CALLBACK("SUCCESS_SAVE_COMPANY", NativeWebViewActivity.class),
//  // 保存公司信息成功回调
//  ACTION_SAVE_QUALIFICATIONS_CALLBACK("SUCCESS_SAVE_QUALIFICATIONS", NativeWebViewActivity.class),
//  // 返回首页
//  ACTION_TO_HOME_PAGE("TO_HOME_PAGE", MainActivity.class),
//  //加入购物车
//  ACTION_ADD_TO_CART("ADD_TO_CART", null),
//  //拨打电话
//  ACTION_AWAKEN_CALL("AWAKEN_CALL", null),
//  //去登录
//  ACTION_FORWARD_LOGIN("FORWARD_LOGIN", NewLoginActivity.class),
//  //提交需求单
//  ACTION_SUBMIT_DEMAND("OPEN_PUR_PLAN", SubmitDemandActivity.class),
//  //放大图片
//  ACTION_OPEN_SLIDE_IMAGE("OPEN_SLIDE_IMAGE", ImageDetailActivity.class),
//  //跳转资质页面FORWARD_QUALIFY
//  ACTION_FORWARD_QUALIFY("FORWARD_QUALIFY", NativeWebViewActivity.class),
//  //跳转秒杀
//  ACTION_OPEN_SECKILLLIST("OPEN_SECKILLLIST", NewWebSpikeActivity.class),
//  //刷新当前页面
//  ACTION_REFRESH_CURRENT_PAGE("REFRESH_CURRENT_PAGE", null),
//  //商品列表没有商品
//  ACTION_ADD_CART("ADD_CART", null),
//  ACTION_UPDATE_DRUG_LIST("CLEAR_CATE",null),
//
//  //降价商品
//  ACTION_OPEN_DEPRECIATE_LIST("OPEN_DEPRECIATE_LIST",NewWebViewDrugListActivity.class),
//
//  /**
//   * -----------------商品详情---------------
//   */
//  //商品详情跳转客服
//  FORWARD_SERVICE("FORWARD_SERVICE", ChatActivity.class),
//  //商品详情跳转购物车
//  FORWARD_CART("FORWARD_CART", CartActivity.class),
//  //详情跳转首营
//  FORWARD_APPLY_CAMP("FORWARD_APPLY_CAMP", EnterpriseDetailsActivity.class),
//  //详情跳转企业详情
//  FORWARD_STORE_BY_GOODS("FORWARD_STORE_BY_GOODS", EnterpriseDetailsActivity.class),
//  //进入店铺
//  ACTION_OPEN_SHOP("OPEN_SHOP",StoreActivity.class),
//  //下载资质
//  ACTION_DOWNLOAD_IMAGES("DOWNLOAD_IMAGES",null),
//
//  /**
//   * 众筹
//   */
//  //去支付
//  ACTION_TO_PROJECT_PAY_PAGE("TO_PROJECT_PAY_PAGE",NewPayActivity.class),
//  //关闭页面
//  ACTION_DESTROY_PAGE("DESTROY_PAGE",null),
//
//  //下载资质
//  OPEN_BROWSER("OPEN_BROWSER",null),
//  ;
//  private String action;
//  private Class activity;
//
//  JsToNativeEnums(String action, Class activity) {
//    this.action = action;
//    this.activity = activity;
//  }
//
//  public String getAction() {
//    return action;
//  }
//
//  public void setAction(String action) {
//    this.action = action;
//  }
//
//  public Class getActivity() {
//    return activity;
//  }
//
//  public void setActivity(Class activity) {
//    this.activity = activity;
//  }
//
//
//  /**
//   * js和本地交互打开对应界面
//   *
//   * @param data action
//   * @param mContext mContext
//   */
//  private static final String openNative = "openNavtivePage";  //跳转本地标识
//  private static final String openWebView = "openWebViewPage"; //跳转webView界面标识
//
//  public static void jsOrNativeInteraction(final JsAndNativenTeractionBean jsAndNativenTeractionBean, final BaseActivity mContext,
//      JsAndNativeInteractionCallBack callBack, X5WebView webView) {
//   if(ClickUtils.isFastClick()){
//     if (openNative.equals(jsAndNativenTeractionBean.getAction())) {
//       if (ACTION_PRODUCT_LIST.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //商品列表
//         Intent intent = new Intent(mContext, ACTION_PRODUCT_LIST.getActivity());
//         intent.putExtra("keyWord", jsAndNativenTeractionBean.getData().getParams().getKeyword());
//         intent.putExtra("storeId", jsAndNativenTeractionBean.getData().getParams().getShopId());
//         intent.putExtra("jsUrl", jsAndNativenTeractionBean.getData().getParams().getUrl());
//         //用于区分是
//         intent.putExtra("fromWhere", "home");
//         mContext.startActivity(intent);
//       }  else if (ACTION_CLASSIFIED_PROMOTION.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         if ("COUPON".equals(jsAndNativenTeractionBean.getData().getParams().getGroupId())) {
//           //领取中心
//           Intent intent = new Intent(mContext, ACTION_COUPON_LIST.getActivity());
//           mContext.startActivity(intent);
//         } else {
//            if ("3".equals(jsAndNativenTeractionBean.getData().getParams().getType())) {
//             //领取中心
//             Intent intent = new Intent(mContext, ACTION_COUPON_LIST.getActivity());
//             mContext.startActivity(intent);
//           } else {
//             //促销分组
//             Intent intent = new Intent(mContext, ACTION_CLASSIFIED_PROMOTION.getActivity());
//             intent
//                 .putExtra("tittle", jsAndNativenTeractionBean.getData().getParams().getGroupName());
//             intent
//                 .putExtra("groupId", jsAndNativenTeractionBean.getData().getParams().getGroupId());
//             mContext.startActivity(intent);
//           }
//         }
//       } else if (ACTION_SAVE_COMPANY_CALLBACK.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //提交资质成功回调
//         if (callBack != null) {
//           callBack.jsAndNativeInteractionCallBack(
//               jsAndNativenTeractionBean.getData().getParams().isSuccess()
//               , jsAndNativenTeractionBean.getData().getParams().getUrl());
//         }
//       } else if (ACTION_APPLY_QUALIFICATION.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //资质
//         Intent intent = new Intent(mContext, ACTION_APPLY_QUALIFICATION.getActivity());
//         if("1".equals(jsAndNativenTeractionBean.getData().getParams().getIsView())){
//           intent.putExtra("jsUrl", JsAndNativeInfo.lookQualificaitonUrl);
//         }else {
//           intent.putExtra("jsUrl", JsAndNativeInfo.applyQualification);
//         }
//
//         intent.putExtra("H5title", jsAndNativenTeractionBean.getData().getTitle());
//         intent.putExtra("isOpenHeadRefresh", false);
//         mContext.startActivity(intent);
//       } else if (ACTION_SAVE_QUALIFICATIONS_CALLBACK.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //提交资质成功回调
//         if (callBack != null) {
//           mContext.sendBroadcast(new Intent("update_webView"));
//           callBack.jsAndNativeInteractionCallBack(
//               jsAndNativenTeractionBean.getData().getParams().isSuccess()
//               , jsAndNativenTeractionBean.getData().getParams().getUrl());
//         }
//       } else if (ACTION_APPLY_CAMP.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //首营
//         Intent intent = new Intent(mContext, ACTION_APPLY_CAMP.getActivity());
//         mContext.startActivity(intent);
//       } else if (ACTION_AUTO_LOGIN.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //保存用户名
//         SharedPreferences.Editor editor = KnysClApplication.getInstance().instanceSp().edit();
//         editor.clear();
//         editor.putString("username", jsAndNativenTeractionBean.getData().getParams().getUsername());
//         editor.putString("password", "");
//         editor.commit();
//         //发送注册广播
//         Intent intent = new Intent("login");
//         intent.putExtra("type", "register");
//         mContext.sendBroadcast(intent);
//         RegisterCancleHintDialog registerSuccessDialog = new RegisterCancleHintDialog(mContext,
//             mContext.getResources().getString(R.string.register_success),
//             mContext.getResources().getString(R.string.register_hint),
//             mContext.getResources().getString(R.string.to_home),
//             mContext.getResources().getString(R.string.upload_qualification));
//         registerSuccessDialog.setDialogClickBtnListener(new DialogClickBtnListener() {
//           @Override
//           public void setLeftBtnClickListener() {
//             LoginUtils.Login(jsAndNativenTeractionBean.getData().getParams().getUsername(),
//                 jsAndNativenTeractionBean.getData().getParams().getPassword(), mContext, "login","");
//           }
//
//           @Override
//           public void setRightBtnClickListener() {
//             LoginUtils.Login(jsAndNativenTeractionBean.getData().getParams().getUsername(),
//                 jsAndNativenTeractionBean.getData().getParams().getPassword(), mContext,
//                 "register","");
//           }
//
//         });
//         registerSuccessDialog.showDialog();
//
//       } else if (ACTION_TO_HOME_PAGE.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //去首页
//         Intent intent = new Intent(mContext, ACTION_TO_HOME_PAGE.getActivity());
//         mContext.startActivity(intent);
//       } else if (ACTION_ADD_TO_CART.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //加入购物车
//         HttpMoudel
//             .loadDrugDetail(mContext, jsAndNativenTeractionBean.getData().getParams().getDiid()
//                 , jsAndNativenTeractionBean.getData().getParams().getDbsid(),
//                 jsAndNativenTeractionBean.getData().getParams().getStoreId(),
//                 jsAndNativenTeractionBean.getData().getParams().getStoreDbsids().get(0), webView);
//       } else if (ACTION_AWAKEN_CALL.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //拨打客服电话
//         CallPhoneUtils
//             .setOnCallService(jsAndNativenTeractionBean.getData().getParams().getPhone(), mContext);
//       } else if (ACTION_FORWARD_LOGIN.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         RegisterCancleHintDialog registerSuccessDialog = new RegisterCancleHintDialog(mContext,
//             jsAndNativenTeractionBean.getData().getParams().getTitle(),
//             jsAndNativenTeractionBean.getData().getParams().getMessage(),
//             "",
//             "确定");
//         registerSuccessDialog.setDialogClickBtnListener(new DialogClickBtnListener() {
//           @Override
//           public void setLeftBtnClickListener() {
//
//           }
//
//           @Override
//           public void setRightBtnClickListener() {
//             CookieSyncManager.createInstance(mContext);
//             CookieManager.getInstance().removeAllCookie();
//             HttpMoudel.Logout(mContext);
//             Intent intent = new Intent(mContext, ACTION_FORWARD_LOGIN.getActivity());
//             XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(mContext);
//             if((clickedResult!=null||VariateUtils.isMessageWakeUpApp)&&mContext.getClass().getName().contains("NativeWebViewActivity")){
//               intent.putExtra("activity","NativeWebViewActivity");
//               mContext.startActivityForResult(intent,1);
//             }else {
//               mContext.sendBroadcast(new Intent("loginOut"));
//               mContext.startActivity(intent);
//               mContext.finish();
//             }
//           }
//
//         });
//         registerSuccessDialog.showDialog();
//       } else if (ACTION_SUBMIT_DEMAND.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //提交需求单
//         Intent intent = new Intent(mContext, ACTION_SUBMIT_DEMAND.getActivity());
//         mContext.startActivity(intent);
//       } else if (ACTION_OPEN_SLIDE_IMAGE.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //放大图片
//         Intent intent = new Intent(mContext, ACTION_OPEN_SLIDE_IMAGE.getActivity());
//         ArrayList<String> imgList = new ArrayList<>();
//         imgList.add(jsAndNativenTeractionBean.getData().getParams().getImageUrl());
//         intent.putExtra("pos", "0");
//         intent.putStringArrayListExtra("screenshot_samples", imgList);
//         mContext.startActivity(intent);
//       } else if (ACTION_FORWARD_QUALIFY.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //跳转资质弹窗
//         RegisterCancleHintDialog registerSuccessDialog = new RegisterCancleHintDialog(mContext,
//             jsAndNativenTeractionBean.getData().getParams().getTitle(),
//             jsAndNativenTeractionBean.getData().getParams().getMessage(),
//             "",
//             "确定");
//         registerSuccessDialog.setDialogClickBtnListener(new DialogClickBtnListener() {
//           @Override
//           public void setLeftBtnClickListener() {
//
//           }
//
//           @Override
//           public void setRightBtnClickListener() {
//             Intent intent = new Intent(mContext, ACTION_FORWARD_QUALIFY.getActivity());
//             intent.putExtra("jsUrl", jsAndNativenTeractionBean.getData().getParams().getUrl());
//             intent.putExtra("isOpenHeadRefresh", false);
//             mContext.startActivity(intent);
//           }
//
//         });
//         registerSuccessDialog.showDialog();
//       } else if (ACTION_OPEN_SECKILLLIST.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //打开秒杀
//         Intent intent = new Intent(mContext, ACTION_OPEN_SECKILLLIST.getActivity());
//         intent.putExtra("jsUrl", jsAndNativenTeractionBean.getData().getParams().getUrl());
//         intent.putExtra("isOpenHeadRefresh", true);
//         mContext.startActivity(intent);
//       } else if (ACTION_REFRESH_CURRENT_PAGE.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //刷新当前页面
//         callBack.jsAndNativeInteractionCallBack(true,
//             jsAndNativenTeractionBean.getData().getParams().getUrl());
//       } else if (ACTION_ADD_CART.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //刷新购物车
//         mContext.sendBroadcast(new Intent("update_cart"));
//         mContext.sendBroadcast(new Intent("update_cart_num"));
//       } else if (FORWARD_SERVICE.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //商品详情跳转客服
//         if (KnysClApplication.listUserInfo != null) {
//           if (KnysClApplication.listUserInfo.getData().getAgentList() != null
//               && KnysClApplication.listUserInfo.getData().getAgentList().size() > 0) {
//             if (KnysClApplication.listUserInfo.getData().getAgentList().get(0).getUserName()
//                 == null) {
//               ToastUtils.showToast(mContext, "暂无分配客服");
//             } else {
//               Intent intent = new Intent(mContext, FORWARD_SERVICE.getActivity());
//               Bundle bundle = new Bundle();
//               bundle.putString("skuId",
//                   jsAndNativenTeractionBean.getData().getParams().getBatchNumber());
//               bundle.putString("stockNum",
//                   String.valueOf(jsAndNativenTeractionBean.getData().getParams().getMinStep()));
//               bundle.putString("salePrice",
//                   String.valueOf(
//                       jsAndNativenTeractionBean.getData().getParams().getPrice().doubleValue()));
//               bundle.putString("diid", jsAndNativenTeractionBean.getData().getParams().getDiid());
//               bundle.putString("storeGoodsId",
//                   jsAndNativenTeractionBean.getData().getParams().getDbsid());
//               bundle.putString("specificationName",
//                   jsAndNativenTeractionBean.getData().getParams().getSpecifications());
//               bundle.putString("commonName",
//                   jsAndNativenTeractionBean.getData().getParams().getDiname());
//               bundle.putString("imgPath", jsAndNativenTeractionBean.getData().getParams().getImg());
//               bundle.putString("companyName",
//                   jsAndNativenTeractionBean.getData().getParams().getCompanyName());
//               bundle.putString("manufacturer",
//                   jsAndNativenTeractionBean.getData().getParams().getManufacturer());
//               bundle.putString("storeDbsid",
//                   jsAndNativenTeractionBean.getData().getParams().getStoreDbsid());
//               intent.putExtras(bundle);
//               mContext.startActivity(intent, bundle);
//             }
//           } else {
//             ToastUtils.showToast(mContext, "暂无分配客服");
//           }
//         } else {
//           ToastUtils.showToast(mContext, "暂无分配客服");
//         }
//       } else if (FORWARD_CART.getAction().equals(jsAndNativenTeractionBean.getData().getName())) {
//         //商品详情跳转购物车
//         Intent intent = new Intent(mContext, FORWARD_CART.getActivity());
//         mContext.startActivity(intent);
//       } else if (FORWARD_APPLY_CAMP.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //跳转首营
//         Intent intent = new Intent(mContext, FORWARD_APPLY_CAMP.getActivity());
//         intent.putExtra("type", "0");
//         intent.putExtra("companyId",
//             jsAndNativenTeractionBean.getData().getParams().getStoreCompanyId() + "");
//         intent.putExtra("storeId", jsAndNativenTeractionBean.getData().getParams().getStoreId());
//         mContext.startActivity(intent);
//       } else if (FORWARD_STORE_BY_GOODS.getAction()
//           .equals(jsAndNativenTeractionBean.getData().getName())) {
//         //进入企业详情
//         Intent in = new Intent(mContext,
//             FORWARD_STORE_BY_GOODS.getActivity());
//         in.putExtra("companyId",
//             jsAndNativenTeractionBean.getData().getParams().getStoreCompanyId());
//         mContext.startActivity(in);
//       }else if(OPEN_BROWSER.getAction().equals(jsAndNativenTeractionBean.getData().getName())){
//         //打开浏览器
//         Intent intent = new Intent(Intent.ACTION_VIEW);
//         intent.addCategory(Intent.CATEGORY_BROWSABLE);
//         intent.setData(Uri.parse(jsAndNativenTeractionBean.getData().getParams().getUrl()));
//         mContext.startActivity(intent);
//       }else if(ACTION_UPDATE_DRUG_LIST.getAction().equals(jsAndNativenTeractionBean.getData().getName())){
//         //去选分类刷新回调
//         callBack.updateDrugListCallBack();
//       }else if(ACTION_TO_PROJECT_PAY_PAGE.getAction().equals(jsAndNativenTeractionBean.getData().getName())){
//         //打开支付众筹
//         Intent intent = new Intent(mContext,ACTION_TO_PROJECT_PAY_PAGE.getActivity());
//         intent.putExtra("orderId",jsAndNativenTeractionBean.getData().getParams().getOrderId());
//         intent.putExtra("orderAmount",jsAndNativenTeractionBean.getData().getParams().getAmount());
//         if(jsAndNativenTeractionBean.getData().getParams().isClose_page()){
//           intent.putExtra("type","submitCrowdFunding");
//         }else {
//           intent.putExtra("type","crowdFundingOrder");
//         }
//
//         mContext.startActivity(intent);
//         if(jsAndNativenTeractionBean.getData().getParams().isClose_page()){
//           mContext.finish();
//         }
//       }else if(ACTION_DESTROY_PAGE.getAction().equals(jsAndNativenTeractionBean.getData().getName())){
//         //关闭当前页面
//         mContext.finish();
//       }else if(ACTION_OPEN_SHOP.getAction().equals(jsAndNativenTeractionBean.getData().getName())){
//         //打开店铺
//         Intent intent = new Intent(mContext,ACTION_OPEN_SHOP.getActivity());
//         intent.putExtra("jsUrl",jsAndNativenTeractionBean.getData().getParams().getUrl());
//         intent.putExtra("storeId",jsAndNativenTeractionBean.getData().getParams().getShopId());
//         mContext.startActivity(intent);
//       }else if(ACTION_DOWNLOAD_IMAGES.getAction().contains(jsAndNativenTeractionBean.getData().getName())){
//         if(jsAndNativenTeractionBean.getData().getParams().getImages()!=null&&
//             jsAndNativenTeractionBean.getData().getParams().getImages().size()>0){
//           final List<Boolean> list = new ArrayList<>();
//           for (ImageBean imageBean : jsAndNativenTeractionBean.getData().getParams().getImages()) {
//             if(imageBean!=null&&!TextUtils.isEmpty(imageBean.getImgUrl())){
//               DownLoadUtils.Companion.InitDownload(TextUtils.textIsEmpty(imageBean.getTitle())
//                   , TextUtils.ImgIsUtils(imageBean.getImgUrl(), mContext), mContext,
//                   new DownloadCallBack() {
//                     @Override
//                     public void downloadCallBackListener(String name, boolean success) {
//                       list.add(success);
//                       if(list.size() == jsAndNativenTeractionBean.getData().getParams().getImages().size()){
//                         for (boolean isSuccess : list){
//                           if(isSuccess){
//                             ToastUtils.showToastCenter(mContext,"下载成功");
//                             list.clear();
//                             return;
//                           }
//                         }
//                         list.clear();
//                         ToastUtils.showToastCenter(mContext,"下载失败");
//                       }
//                     }
//                   });
//             }
//           }
//         }else {
//           ToastUtils.showToastCenter(mContext,"没有查询到图片");
//         }
//       }else if(ACTION_OPEN_DEPRECIATE_LIST.getAction().equals(jsAndNativenTeractionBean.getData().getName())){
//         //打开降价
//         Intent intent = new Intent(mContext, ACTION_OPEN_DEPRECIATE_LIST.getActivity());
//         intent.putExtra("jsUrl", "index/depreciate");
//         intent.putExtra("fromWhere","main");
//         mContext.startActivity(intent);
//       }
//     } else if (openWebView.equals(jsAndNativenTeractionBean.getAction())) {
//       //根据返回连接打开新的webView页面
//       Intent intent = new Intent(mContext, ACTION_WEBVIEW.getActivity());
//       intent.putExtra("H5title", jsAndNativenTeractionBean.getData().getTitle());
//       intent.putExtra("jsUrl", jsAndNativenTeractionBean.getData().getUrl());
//       mContext.startActivity(intent);
//     }
//   }
//
//
//  }
//
//  //registerHandler: AppCallJavascriptBack  注册方法，js调取本地方法
//  //callHandler:callJavascriptHandler       回调方法，本地调取js方法
//
//}
