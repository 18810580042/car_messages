//package com.example.car_message.enums;
//
//import android.view.View;
//import android.widget.ImageView;
//
//
//public enum DiscountTypeEnum {
//  //单品
//  SINGLE("1", R.drawable.iv_lable_single),
//  //满减
//  CUT("2", R.drawable.iv_lable_cut),
//  //满赠
//  SEND("3", R.drawable.iv_lable_send),
//  //加价购
//  CHANGE_PRICE("4", R.drawable.iv_lable_change_price),
//  //满减赠
//  CUT_SEND("5", R.drawable.iv_lable_cut_send),
//  //套装
//  COMBO("6", R.drawable.iv_lable_combo),
//  //买赠
//  SALE_SEND("7", R.drawable.iv_lable_sale_send),
//  //秒杀
//  SECKILL("8", R.drawable.iv_lable_seckill);
//
//
//  DiscountTypeEnum(String type, int picPath) {
//    this.type = type;
//    this.picPath = picPath;
//  }
//
//  private String type;  //类型
//  private int picPath;  //图片地址
//
//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
//
//  public int getPicPath() {
//    return picPath;
//  }
//
//  public void setPicPath(int picPath) {
//    this.picPath = picPath;
//  }
//
//
//  /**
//   * 根据商品类型，显示商品标签
//   *
//   * @param type 类型
//   * @param iv 图片控件
//   */
//  public static void getDiscountPicPathByType(String type, ImageView iv) {
//    if (SINGLE.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(SINGLE.getPicPath());
//    } else if (CUT.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(CUT.getPicPath());
//    } else if (SEND.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(SEND.getPicPath());
//    } else if (CHANGE_PRICE.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(CHANGE_PRICE.getPicPath());
//    } else if (CUT_SEND.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(CUT_SEND.getPicPath());
//    } else if (COMBO.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(COMBO.getPicPath());
//    } else if (SALE_SEND.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(SALE_SEND.getPicPath());
//    } else if (SECKILL.getType().equals(type)) {
//      iv.setVisibility(View.VISIBLE);
//      iv.setImageResource(SECKILL.getPicPath());
//    } else {
//      iv.setVisibility(View.GONE);
//    }
//  }
//}
