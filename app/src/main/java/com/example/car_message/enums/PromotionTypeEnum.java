package com.example.car_message.enums;

public enum PromotionTypeEnum {

  SINGLE("1", "单品"),
  FULL_CUT("2", "满减"),
  FULL_SEND("3", "满赠"),
  FULL_JJSEND("4", "加价购"),
  FULL_CUT_SEND("5", "满减送"),
  SUIT("6", "套装"),
  BUY_SEND("7", "买赠"),
  NO_MAPPING("0","");
  private String id;
  private String promotionType;

  PromotionTypeEnum(String i, String promotionType) {
    this.id = i;
    this.promotionType = promotionType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPromotionType() {
    return promotionType;
  }

  public void setPromotionType(String promotionType) {
    this.promotionType = promotionType;
  }

  /**
   * 根据字符串取枚举值
   * @param promotionType  字符串
   * @return  枚举
   */
  public static PromotionTypeEnum getPromotionName(String promotionType){
    if("SINGLE".equals(promotionType)){
      return SINGLE;
    }else if("FULL_CUT".equals(promotionType)){
      return FULL_CUT;
    }else if("FULL_SEND".equals(promotionType)){
      return FULL_SEND;
    }else if("FULL_JJSEND".equals(promotionType)){
      return FULL_JJSEND;
    }else if("FULL_CUT_SEND".equals(promotionType)){
      return FULL_CUT_SEND;
    }else if("SUIT".equals(promotionType)){
      return SUIT;
    }else if("BUY_SEND".equals(promotionType)){
      return BUY_SEND;
    }else {
      return NO_MAPPING;
    }

  }


}
