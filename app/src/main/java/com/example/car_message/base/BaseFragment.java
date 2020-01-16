package com.example.car_message.base;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.car_message.R;
import com.example.car_message.callback.ReLoadingCallback;
import com.example.car_message.dialog.LoadingDialog;
import com.example.car_message.enums.ErrCode;
import com.example.car_message.utils.ContextIsAliveUtils;
import com.example.car_message.utils.NetUtil;

import androidx.annotation.Nullable;


public abstract class BaseFragment extends Fragment implements
    ReLoadingCallback.OnReloadingListener {

  private boolean isFirst = true;
  private LoadingDialog loadingDialog;

  private ReLoadingCallback callback;
  private View noNetWorkOrAnmoalyView;
  private TextView tvHint;
  private ImageView ivHint;
  private TextView tvReLoad;

  protected boolean isInit = false;
  protected boolean isLoad = false;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    initErrView();
    return inflater.inflate(getLayoutId(), container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    isFirst = true;
    callback = new ReLoadingCallback();
    initListener();
    init(view,savedInstanceState);
    isInit = true;
    isCanLoadData();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);
  }

  private void initListener() {
    callback.setOnReloadingListener(this);
  }

  //当页面可见时
  @Override
  public void onResume() {
    super.onResume();
    if (isFirst) {
      loadData();
      isFirst = false;
    }
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    //Fragment处于隐藏状态
    if (hidden) {
      onHidden();
    } else {
      //Fragment处于显示状态
      onShow();
    }
  }

  //加载布局
  protected abstract int getLayoutId();

  //初始化数据
  protected abstract void init(View view,Bundle savedInstanceState);

  //加载数据
  protected abstract void loadData();

  //当前Fragment可见时调用
  protected void onShow() {
    //能在该方法中刷新数据
  }

  //当前Fragment隐藏时调用，可以做数据保存
  protected void onHidden() {
  }

  public abstract void setParams(Bundle bundle);


  /**
   * 试图销毁时，fragment是否初始化的状态转变为false
   */
  @Override
  public void onDestroyView() {
    super.onDestroyView();
    isInit = false;
    isLoad = false;
  }


  /**
   * 显示对话框
   */
  public void showProgress() {
    try {
      if (ContextIsAliveUtils.isContextExisted(getActivity())) {
        if (loadingDialog == null) {
          loadingDialog = new LoadingDialog(getActivity(), true);
        }
        loadingDialog.show();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * 隐藏对话框
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


  @Override
  public void onDestroy() {
    super.onDestroy();
    if (loadingDialog != null) {
      loadingDialog.dismiss();
      loadingDialog = null;
    }
  }

  /**
   * 实例化错误页面
   */
  private void initErrView() {
    noNetWorkOrAnmoalyView = LayoutInflater.from(getActivity())
        .inflate(R.layout.layout_no_network, null);
    //提示语
    tvHint = noNetWorkOrAnmoalyView.findViewById(R.id.tv_noNetWork);
    //提示图标
    ivHint = noNetWorkOrAnmoalyView.findViewById(R.id.iv_noNetWork);
    //重新加载
    tvReLoad = noNetWorkOrAnmoalyView.findViewById(R.id.tv_reLoad);
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
        } else if (errCode == ErrCode.SUBMIT_DEMAND) {
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
        } else if (errCode == ErrCode.NO_ORDER_DATA || errCode == ErrCode.NO_PRODUCT_DATA
            || errCode == ErrCode.NO_MESG_DATA || errCode == ErrCode.NO_FLOOR_DATA ||
            errCode == ErrCode.NO_FIRST_CAMP_LIST) {
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
   * 试图是否对用户可见
   */

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    isCanLoadData();
  }

  /**
   * 是否可以加载数据 可以加载数据的条件 1.试图已经初始化 2.试图对用户可见
   */
  private void isCanLoadData() {
    if (!isInit) {
      return;
    }

    if (getUserVisibleHint()) {
      lazyLoad();
      isLoad = true;
    } else {
      if (isLoad) {
        stopLoad();
      }
    }
  }


  protected abstract void lazyLoad();

  protected void stopLoad() {

  }


}
