package com.example.car_message.callback;

public class ReLoadingCallback {

  /**
   * 重新加载监听
   */
  public interface OnReloadingListener{
    void onReloading(String type);   //1，加载失败,刷新，2，没有数据，提交需求清单
  }

  public OnReloadingListener mOnReloadingListener;

  public void setOnReloadingListener(OnReloadingListener mOnReloadingListener){
    this.mOnReloadingListener =  mOnReloadingListener;
  }


}
