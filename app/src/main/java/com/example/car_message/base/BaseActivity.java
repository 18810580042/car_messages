package com.example.car_message.base;

import android.annotation.SuppressLint;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.callback.ReLoadingCallback;
import com.example.car_message.dialog.LoadingDialog;
import com.example.car_message.enums.ErrCode;
import com.example.car_message.utils.ContextIsAliveUtils;
import com.example.car_message.utils.NetUtil;
import com.example.car_message.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


/**
 * activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener, ReLoadingCallback.OnReloadingListener {

  public View mTitleView;
  private ReLoadingCallback callback;
  private View noNetWorkOrAnmoalyView;
  private TextView tvHint;
  private ImageView ivHint;

  private LoadingDialog loadingDialog;
  private TextView tvReLoad;


  @SuppressLint("InflateParams")
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setStatusBar();
    //锁定竖屏
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    callback = new ReLoadingCallback();
    initListener();
    initErrView();
    setContentView(setContentViewFilter(getLayoutId()));
    initView(savedInstanceState);
    initData();
    loadData();

  }

  private void initListener() {
    callback.setOnReloadingListener(this);
  }

  /**
   * 加载页面布局
   *
   * @param resId 页面资源文件
   * @return view
   */
  private View setContentViewFilter(int resId) {
    LayoutInflater inflater = LayoutInflater.from(this);
    RelativeLayout relativeLayout = new RelativeLayout(this);
    relativeLayout.setLayoutParams(
        new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
    mTitleView = addCommonTitlebar(relativeLayout);
    if (mTitleView == null) {
      return inflater.inflate(resId, null, false);
    }
    View contentView = inflater.inflate(resId, relativeLayout, false);
    relativeLayout.addView(mTitleView);
    RelativeLayout.LayoutParams contentViewParams = (RelativeLayout.LayoutParams) contentView
        .getLayoutParams();
    contentViewParams.addRule(RelativeLayout.BELOW, R.id.tablayout);
    relativeLayout.addView(contentView);
    return relativeLayout;
  }

  @Override
  public void onClick(View view) {

  }

  /**
   * 添加标题栏
   *
   * @param root 标题
   * @return 标题栏
   */
  public View addCommonTitlebar(RelativeLayout root) {
    return null;
  }

  //加载布局
  protected abstract int getLayoutId();

  //初始化view
  protected abstract void initView(Bundle savedInstanceState);

  //初始化数据
  public abstract void initData();

  //加载数据
  public abstract void loadData();



  /**
   * 实例化错误页面
   */
  private void initErrView() {
    noNetWorkOrAnmoalyView = LayoutInflater.from(this)
        .inflate(R.layout.layout_no_network, null);
    //重新加载点击事件
    tvReLoad = noNetWorkOrAnmoalyView.findViewById(R.id.tv_reLoad);
    //提示语
    tvHint = noNetWorkOrAnmoalyView.findViewById(R.id.tv_noNetWork);
    //提示图标
    ivHint = noNetWorkOrAnmoalyView.findViewById(R.id.iv_noNetWork);
  }

  /**
   * 显示页面异常
   *
   * @param errCode 错误信息
   * @param view 要替换的布局
   */
  public void showResult(Context context, ErrCode errCode, RelativeLayout view) {
    if (view != null) {
      view.removeView(noNetWorkOrAnmoalyView);
      //判断是否有网络，如果没网络提示网络异常，其他提示对应异常
      if (ContextIsAliveUtils.isContextExisted(context) && !NetUtil.checkNet(context)) {
        tvReLoad.setText("重新加载");
        tvReLoad.setVisibility(View.VISIBLE);
        tvHint.setText(ErrCode.NO_NETWORK.getErrMsg());
        ivHint.setImageResource(ErrCode.NO_NETWORK.getRes());
        view.addView(noNetWorkOrAnmoalyView);
        tvReLoad.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View view) {
            callback.mOnReloadingListener.onReloading("1");
          }
        });
      } else {
        if (errCode == ErrCode.LOAD_ERROR) {
          tvReLoad.setText("重新加载");
          tvReLoad.setVisibility(View.VISIBLE);
          tvHint.setText(errCode.getErrMsg());
          ivHint.setImageResource(errCode.getRes());
          view.addView(noNetWorkOrAnmoalyView);
          tvReLoad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
              callback.mOnReloadingListener.onReloading("1");
            }
          });
        } else if(errCode == ErrCode.SUBMIT_DEMAND){
          tvReLoad.setText("提交需求清单");
          tvReLoad.setVisibility(View.VISIBLE);
          tvHint.setText(errCode.getErrMsg());
          ivHint.setImageResource(errCode.getRes());
          view.addView(noNetWorkOrAnmoalyView);
          tvReLoad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
              callback.mOnReloadingListener.onReloading("2");
            }
          });
        }else if (errCode == ErrCode.NO_ORDER_DATA || errCode == ErrCode.NO_PRODUCT_DATA
            || errCode == ErrCode.NO_MESG_DATA || errCode == ErrCode.NO_FLOOR_DATA
            || errCode == ErrCode.NO_SCAN_DATA) {
          tvReLoad.setVisibility(View.GONE);
          tvHint.setText(errCode.getErrMsg());
          ivHint.setImageResource(errCode.getRes());
          view.addView(noNetWorkOrAnmoalyView);
        } else {
          view.removeView(noNetWorkOrAnmoalyView);
        }
      }

    }

  }


  /**
   * 显示加载对话框
   */
  public void showProgress() {
    try {
      if(ContextIsAliveUtils.isContextExisted(this)){
        if (loadingDialog == null) {
          loadingDialog = new LoadingDialog(this,true);
        }
        loadingDialog.show();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }


  /**
   * 隐藏加载对话框
   */
  public void disProgress() {
    try {
      if (loadingDialog != null) {
        loadingDialog.dismiss();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  protected void setStatusBar() {
    ActionBar actionBar = getSupportActionBar();
    actionBar.hide();
    //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
    StatusBarUtil.setTransparent(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (loadingDialog != null) {
      loadingDialog.dismiss();
      loadingDialog = null;
    }
  }


}
