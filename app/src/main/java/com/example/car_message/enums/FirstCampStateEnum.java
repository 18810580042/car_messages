//package com.example.car_message.enums;
//
//import android.graphics.Color;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//public enum FirstCampStateEnum {
//  SUCCESS("2", "首营建立成功",R.drawable.iv_first_camp_success,"#3dd3a7"),
//  FAILED("-1", "首营审核失败",R.drawable.iv_first_camp_failed,"#e93424"),
//  WAIT("1", "首营审核中",R.drawable.iv_first_camp_wait,"#ed9d47");
//
//  private String state;
//  private String stateName;
//  private int imgPath;
//  private String textColor;
//
//  FirstCampStateEnum(String state, String stateName, int imgPath, String textColor) {
//    this.state = state;
//    this.stateName = stateName;
//    this.imgPath = imgPath;
//    this.textColor = textColor;
//  }
//
//  public String getState() {
//    return state;
//  }
//
//  public void setState(String state) {
//    this.state = state;
//  }
//
//  public String getStateName() {
//    return stateName;
//  }
//
//  public void setStateName(String stateName) {
//    this.stateName = stateName;
//  }
//
//  public int getImgPath() {
//    return imgPath;
//  }
//
//  public void setImgPath(int imgPath) {
//    this.imgPath = imgPath;
//  }
//
//  public String getTextColor() {
//    return textColor;
//  }
//
//  public void setTextColor(String textColor) {
//    this.textColor = textColor;
//  }
//
//  /**
//   * 设置资质状态
//   * @param tv  tv
//   * @param iv iv
//   * @param state state
//   */
//  public static void setQualificationState(TextView tv, ImageView iv, String state){
//    if(SUCCESS.getState().equals(state)){
//      tv.setText(SUCCESS.getStateName());
//      tv.setTextColor(Color.parseColor(SUCCESS.getTextColor()));
//      iv.setImageResource(SUCCESS.getImgPath());
//    }else if(FAILED.getState().equals(state)){
//      tv.setText(FAILED.getStateName());
//      tv.setTextColor(Color.parseColor(FAILED.getTextColor()));
//      iv.setImageResource(FAILED.getImgPath());
//    }else{
//      tv.setText(WAIT.getStateName());
//      tv.setTextColor(Color.parseColor(WAIT.getTextColor()));
//      iv.setImageResource(WAIT.getImgPath());
//    }
//
//  }
//}
