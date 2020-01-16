package com.example.car_message.enums;


import com.example.car_message.R;

public enum ErrCode {

  NO_NETWORK("检测不到网络，请检查网络设置", R.drawable.net_error),
  LOAD_SUCCESS("",R.mipmap.ic_launcher),
  LOAD_ERROR("页面出现异常了，稍后再试下呢",R.drawable.load_failed),
  NO_ORDER_DATA("您还没有相关订单",R.drawable.iv_no_oder_data),
  NO_CLASS_DATA("没有查询到分类",R.drawable.load_no_data),
  NO_PRODUCT_DATA("没有相关商品",R.drawable.load_no_data),
  NO_MESG_DATA("没有相关消息",R.drawable.load_no_data ),
  SUBMIT_DEMAND("抱歉,未找到相关商品,您还可以",R.drawable.iv_submit),
  NO_SCAN_DATA("您好，未搜索到该商品，请尝试手动输入该商品名称",R.drawable.iv_no_scan_data),
  NO_FIRST_CAMP_LIST("暂无数据",R.drawable.iv_no_first_camp),
  NO_FLOOR_DATA("",R.drawable.iv_no_floor);
  private String errMsg;
  private int res;

  ErrCode(String errMsg,int res) {
    this.errMsg = errMsg;
    this.res = res;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }

  public int getRes() {
    return res;
  }

  public void setRes(int res) {
    this.res = res;
  }
}
