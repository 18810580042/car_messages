package com.example.car_message.utils;

/**
 * @time 2017/6/19 10:55.
 */

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import android.text.TextUtils;
import android.util.Log;

//import com.example.car_message.KnysClApplication;
import com.example.car_message.KnysClApplication;
import com.example.car_message.activity.LoginActivity;
import com.example.car_message.base.Result;
import com.example.car_message.base.ResultList;
import com.example.car_message.callback.CommontCallback;
import com.example.car_message.callback.CommontListCallback;
import com.example.car_message.callback.DataCallback;
import com.example.car_message.callback.FileDownUpCallback;
import com.example.car_message.callback.ResultCallback;
import com.example.car_message.callback.ResultListCallback;
import com.example.car_message.callback.StrCallback;
import com.example.car_message.http.Api;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.lzy.okhttputils.request.BaseRequest;
import com.lzy.okhttputils.request.PostRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListUtil;
import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Response;


public class OkHttpRequestUtils {

  public static void init(Application application) {
    OkHttpUtils.init(application);
    try {
      OkHttpUtils.getInstance()
          .debug("OkHttpUtils")
          .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
          .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)     //全局的读取超时时间
          .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的写入超时时间
          .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE) //缓存模式
          .setCacheTime(1 * 24 * 60 * 60 * 1000) //全局缓存有效期
          .setCookieStore(new PersistentCookieStore()
              .setSaveCookieInterceptor(new PersistentCookieStore.SaveCookieInterceptor() {

                @Override
                public boolean removeFillter(HttpUrl url, Cookie cookie) {
                  String urltemp = url.toString();
                  if (!urltemp.contains("j_spring_cas_security_check") && !urltemp
                      .contains("/mobile/member/member.jhtml") && cookie.name()
                      .contains("JSESSIONID")) {
                    return true;
                  }
                  return false;
                }
              }))
      ;

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Get请求
   *
   * @param tag tag
   * @param url url
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  public static <T> void doCommentGetRequest(String tag, String url, DataCallback callback,
                                             Class<T> clazz) {
    if (NetUtil.checkNet(KnysClApplication.getInstance())) {
      if (url == null) {
        LogUtil.e(tag, "对不起，都是开发的锅,检查urls.json");
        return;
      }
      BaseRequest baseRequest;
      baseRequest = OkHttpUtils.get(url);
      requestConfig(baseRequest, callback, clazz);
    } else {
      callback.onError("检测不到网络，请检查网络设置");
    }

  }

  /**
   * Get请求
   *
   * @param tag tag
   * @param url url
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  public static <T> void doCommentGetRequest(String tag, String url, HashMap params,DataCallback callback,
      Class<T> clazz) {
    if (NetUtil.checkNet(KnysClApplication.getInstance())) {
      if (url == null) {
        LogUtil.e(tag, "对不起，都是开发的锅,检查urls.json");
        return;
      }
      StringBuilder mUrl = new StringBuilder(url);
      if(params!=null){
        Set set = params.keySet();
        for (Object aSet : set) {
          if(aSet!=null&&params.get(aSet)!=null&&!"".equals(params.get(aSet))){
            if (mUrl.indexOf("?") == -1) {
              mUrl.append("?");
            } else {
              mUrl.append("&");
            }
            mUrl.append(((String)aSet)).append("=").append((params.get(aSet)));
          }
        }
      }

      BaseRequest baseRequest;
      baseRequest = OkHttpUtils.get(mUrl.toString());
      requestConfig(baseRequest, callback, clazz);
    } else {
      callback.onError("检测不到网络，请检查网络设置");
    }

  }


  /**
   * 正常的post请求
   *
   * @param tag tag
   * @param url url
   * @param params params
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  public static <T> void doCommentRequest(String tag, String url, Map<String, String> params,
      DataCallback callback, Class<T> clazz) {
    if (NetUtil.checkNet(KnysClApplication.getInstance())) {
      if (url == null) {
        LogUtil.e("OkHttpUtils", "对不起，都是开发的锅,检查urls.json");
        return;
      }
      BaseRequest baseRequest;

      if(params!=null){
        //如果参数里没有公司ID,加上
        if (!params.containsKey("companyId")) {
          params.put("companyId",
              KnysClApplication.getInstance().instanceSp().getString("companyId", ""));
        }
        if (!params.containsKey("token")) {
          params
              .put("token", KnysClApplication.getInstance().instanceSp().getString("token", ""));
        }

        //如果参数里没有商城id
        if (!params.containsKey("mallId")) {
          params.put("mallId",
              KnysClApplication.getInstance().instanceSp().getString("mallId", ""));
        }
        //传个服务器一个标志，手机类型和软件版本号
        params.put("deviceInfo",
            "android-" + PackageInfoUtils.getPackageVersion(KnysClApplication.getInstance()));
        params.put("sign_type", "HMACSHA256");
      }

      baseRequest = OkHttpUtils.post(url);
      if (!TextUtils.isEmpty(tag)) {
        baseRequest.tag(tag);
      }
      if (params != null) {
        baseRequest.params(params);
      }
      requestConfig(baseRequest, callback, clazz);
    } else {
      callback.onError("检测不到网络，请检查网络设置");
    }
  }


  /**
   * 登录post请求  不需要签名
   *
   * @param tag tag
   * @param url url
   * @param params params
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  public static <T> void setPassWordBySign(String tag, String url, Map<String, String> params,
      DataCallback callback, Class<T> clazz) {
    if (NetUtil.checkNet(KnysClApplication.getInstance())) {
      if (url == null) {
        LogUtil.e("OkHttpUtils", "对不起，都是开发的锅,检查urls.json");
        return;
      }
      BaseRequest baseRequest;

      baseRequest = OkHttpUtils.post(url);
      if (!TextUtils.isEmpty(tag)) {
        baseRequest.tag(tag);
      }

      if (params != null) {
        try {
          //传个服务器一个标志，手机类型和软件版本号
          params.put("deviceInfo",
              "android-" + PackageInfoUtils.getPackageVersion(KnysClApplication.getInstance()));
          params.put("sign_type", "HMACSHA256");
          //循环去掉里面的null
          Set set = params.keySet();
          for (Object aSet : set) {
            if (params.get((String) aSet) == null) {
              params.put((String) aSet, "");
            }
          }
          String signString = SignUtil.generateSignature(params,
              KnysClApplication.getInstance().instanceSp().getString("serect", ""),
              SignConstants.SignType.HMACSHA256);
          params.put("sign", signString);
          baseRequest.params(params);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      requestConfig(baseRequest, callback, clazz);
    } else {
      callback.onError("检测不到网络，请检查网络设置");
    }
  }

  /**
   * post请求  需要签名
   *
   * @param tag tag
   * @param url url
   * @param params params
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  public static <T> void doCommentRequestBySign(String tag, String url, Map<String, String> params,
      DataCallback callback, Class<T> clazz) {
    if (NetUtil.checkNet(KnysClApplication.getInstance())) {
      if (url == null) {
        LogUtil.e("OkHttpUtils", "对不起，都是开发的锅,检查urls.json");
        return;
      }
      BaseRequest baseRequest;

      baseRequest = OkHttpUtils.post(url);
      if (!TextUtils.isEmpty(tag)) {
        baseRequest.tag(tag);
      }

      if (params != null) {
        try {
          //如果参数里没有公司ID,加上
          if (!params.containsKey("companyId")) {
            params.put("companyId",
                KnysClApplication.getInstance().instanceSp().getString("companyId", ""));
          }
          if (!params.containsKey("token")) {
            params
                .put("token", KnysClApplication.getInstance().instanceSp().getString("token", ""));
          }

          //如果参数里没有商城id
          if (!params.containsKey("mallId")) {
            params.put("mallId",
                KnysClApplication.getInstance().instanceSp().getString("mallId", ""));
          }
          //传个服务器一个标志，手机类型和软件版本号
          params.put("deviceInfo",
              "android-" + PackageInfoUtils.getPackageVersion(KnysClApplication.getInstance()));
          params.put("sign_type", "HMACSHA256");
          //循环去掉里面的null
          Set set = params.keySet();
          for (Object aSet : set) {
            if (params.get((String) aSet) == null) {
              params.put((String) aSet, "");
            }

          }
          String signString = SignUtil.generateSignature(params,
              KnysClApplication.getInstance().instanceSp().getString("serect", ""),
              SignConstants.SignType.HMACSHA256);
          params.put("sign", signString);
          baseRequest.params(params);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      requestConfig(baseRequest, callback, clazz);
    } else {
      callback.onError("检测不到网络，请检查网络设置");
    }
  }


  /**
   * 上传文件
   *
   * @param tag tag
   * @param url url
   * @param name name
   * @param files files
   * @param params params
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  public static <T> void uploadFileRequest(String tag, String url, String name, List<File> files,
      Map<String, String> params, DataCallback callback, Class<T> clazz) {
    if (NetUtil.checkNet(KnysClApplication.getInstance())) {
      if (url == null) {
        LogUtil.e("OkHttpUtils", "对不起，都是开发的锅,检查urls.json");
        return;
      }
      BaseRequest baseRequest;

      baseRequest = OkHttpUtils.post(url);
      if (files != null) {
        PostRequest postRequest = (PostRequest) baseRequest;
        baseRequest = postRequest.addFileParams(name, files);
        baseRequest.setForceMultiPartBody();
      }

      if (!TextUtils.isEmpty(tag)) {
        baseRequest.tag(tag);
      }
      if (params != null) {
        try {
          //如果参数里没有公司ID,加上
          if (!params.containsKey("companyId")) {
            params.put("companyId",
                KnysClApplication.getInstance().instanceSp().getString("companyId", ""));
          }
          params.put("token", KnysClApplication.getInstance().instanceSp().getString("token", ""));
          params.put("mallId",
              KnysClApplication.getInstance().instanceSp().getString("mallId", ""));
          params.put("sign_type", "HMACSHA256");
          String signString = SignUtil.generateSignature(params,
              KnysClApplication.getInstance().instanceSp().getString("serect", ""),
              SignConstants.SignType.HMACSHA256);
          params.put("sign", signString);
          baseRequest.params(params);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      requestConfig(baseRequest, callback, clazz);
    } else {
      callback.onError("检测不到网络，请检查网络设置");
    }
  }


  /**
   * 发起请求
   *
   * @param request request
   * @param callback callback
   * @param clazz clazz
   * @param <T> T
   */
  private static <T> void requestConfig(final BaseRequest request, final DataCallback callback,
      final Class<T> clazz) {
    request.headers("Cookie", KnysClApplication.getInstance().instanceSp().getString("cookie", ""));
    AbsCallback responseCallback;
    if (callback instanceof FileDownUpCallback) {//暂时只支持上传
      responseCallback = new StringCallback() {
        @Override
        public void onSuccess(String s, Call call, Response response) {
          if (clazz != null) {//上传
            handleCallback(s, callback, clazz, false);
          } else {
            //TODO 下载不能用StringCallback  ---应该使用FileCallback
          }
        }

        @Override
        public void onBefore(BaseRequest request) {
          super.onBefore(request);
          callback.onBefore();
        }

        @Override
        public void onAfter(@Nullable String s, @Nullable Exception e) {
          super.onAfter(s, e);
          callback.onComplete();
        }


        @Override
        public void upProgress(long currentSize, long totalSize, float progress,
            long networkSpeed) {
          super.upProgress(currentSize, totalSize, progress, networkSpeed);
          if (callback instanceof FileDownUpCallback) {
            ((FileDownUpCallback) callback)
                .upProgress(currentSize, totalSize, progress, networkSpeed);
          }
        }

        @Override
        public void onError(Call call, Response response, Exception e) {
          super.onError(call, response, e);
          Log.e("error", e.toString());
          callback.onError("网络异常");
        }
      };
    } else {
      responseCallback = new StringCallback() {
        @Override
        public void onBefore(BaseRequest request) {
          super.onBefore(request);
          callback.onBefore();
        }

        @Override
        public void onSuccess(String s, Call call, Response response) {
          if (response != null && response.request() != null && response.request().url() != null) {
            String url = response.request().url().toString();
            if (url.contains(Api.NewLogin) || url.contains(Api.messageLogin)
                || url.contains(Api.newLogin)) {
              Headers headers1 = response.headers();
              List<String> cookies = headers1.values("Set-Cookie");
              if (cookies.size() > 0) {
                String session = cookies.get(0);
                String cookie = session.substring(0, session.indexOf(";"));
                SharedPreferences.Editor editor = KnysClApplication.getInstance().instanceSp()
                    .edit();
                editor.putString("cookie", cookie);
                editor.apply();
              }
            }
          }

          handleCallback(s, callback, clazz, false);
        }

        @Override
        public void onCacheSuccess(String s, Call call) {
          super.onCacheSuccess(s, call);
          handleCallback(s, callback, clazz, true);
        }

        @Override
        public void onAfter(@Nullable String s, @Nullable Exception e) {
          super.onAfter(s, e);
          callback.onComplete();
        }

        @Override
        public void onError(Call call, Response response, Exception e) {
          super.onError(call, response, e);
          Log.e("error", e.toString());
          callback.onError("网络异常");


        }

      };
    }
    request.execute(responseCallback);
  }


  /**
   * @param response 网络返回
   * @param callback 回调
   * @param clazz clazz
   */
  private static <T> void handleCallback(String response, DataCallback callback, Class<T> clazz,
      boolean isCache) {
    if (response.contains("\"code\":401") && !ActivityUtils
        .isTopActivity(KnysClApplication.getInstance())) { //&&!ActivityUtils.isCanReturnActivity(KnysClApplication.getInstance())
      Intent in = new Intent(KnysClApplication.getInstance(), LoginActivity.class);
      in.putExtra("notoken", "1");
      in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      KnysClApplication.getInstance().startActivity(in);
    } else {
      if (callback instanceof ResultListCallback) {
        ResultListCallback callback2 = (ResultListCallback) callback;
        ResultList<T> resultList = GsonUtils.JsonToResultList(response, clazz);
        if (resultList != null && resultList.isStatus() && resultList.getData() != null) {
          callback2.onSuccess(resultList.getData());
        } else {
          if (!isCache) {
            String errorMsg = "获取数据出错";
            if (resultList != null && resultList.getMsg() != null) {
              errorMsg = resultList.getMsg();
            }
            callback2.onError(errorMsg);
          }
        }
      } else if (callback instanceof ResultCallback) {
        ResultCallback callback2 = (ResultCallback) callback;
        Result<T> result = GsonUtils.JsonToResult(response, clazz);
        if (result != null && result.isStatus() && result.getData() != null) {
          callback2.onSuccess(result.getData());
        } else {
          if (!isCache) {
            String errorMsg = "获取数据出错";
            if (result != null && result.getMsg() != null) {
              errorMsg = result.getMsg();
            }
            callback2.onError(errorMsg);
          }
        }
      } else if (callback instanceof CommontCallback) {
        CommontCallback callback2 = (CommontCallback) callback;
        T t = GsonUtils.JsonToBean(response, clazz);
        if (t != null) {
          callback2.onSuccess(t);
        } else {
          if (!isCache) {
            callback2.onError("获取数据出错");
          }
        }

      } else if (callback instanceof CommontListCallback) {
        CommontListCallback callback2 = (CommontListCallback) callback;
        List<T> list = GsonUtils.JsonToList(response, clazz);
        if (list != null) {
          callback2.onSuccess(list);
        } else {
          if (!isCache) {
            callback2.onError("获取数据出错");
          }
        }
      } else if (callback instanceof FileDownUpCallback) {
        FileDownUpCallback callback2 = (FileDownUpCallback) callback;
        T t = GsonUtils.JsonToBean(response, clazz);
        if (t != null) {
          callback2.onSuccess(null, t);
        } else {
          if (!isCache) {
            callback2.onError("获取数据出错");
          }
        }
      } else {
        StrCallback callback2 = (StrCallback) callback;
        callback2.onSuccess(response);
      }
    }
  }

}

