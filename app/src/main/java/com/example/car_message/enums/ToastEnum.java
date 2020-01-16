package com.example.car_message.enums;


import com.example.car_message.R;

public enum  ToastEnum {

//  TOAST_FAILED("申请失败\n请重新填写", R.drawable.iv_apply_failed),
//  TOAST_SUCCESS("申请成功\n请耐心等待补货", R.drawable.iv_apply_success),
  TOAST_REMIND("当前商品已经申\n请成功,请勿重复操作", R.drawable.submit_demand_btn_bg),
  ;


  private String message;
  private int ivPic;

  ToastEnum(String message, int ivPic) {
    this.message = message;
    this.ivPic = ivPic;
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getIvPic() {
    return ivPic;
  }

  public void setIvPic(int ivPic) {
    this.ivPic = ivPic;
  }
}
